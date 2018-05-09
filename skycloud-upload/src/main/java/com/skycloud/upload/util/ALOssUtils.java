package com.skycloud.upload.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.skycloud.upload.configuration.OssConfiguration;
import com.skycloud.upload.dto.OssDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * @author sky
 **/
@Slf4j
@Component
public class ALOssUtils {

    @Resource
    private OssConfiguration oc;

    /**
     * 上传文件到阿里OSS
     *
     * @param ossPath
     * @param key
     * @param in
     * @return
     */
    public String uploadOss(String ossPath, String key, InputStream in) {

        String accessId = oc.getClientId();

        String accessKey = oc.getClientSecret();

        String bucketName = oc.getBucketName();

        String aliyunEndpoint = oc.getAliyunEndpoint();

        String path = oc.getFolderName() + ossPath + "/" + JodaUtils.currentDateToInt() + "/";

        OSSClient client = new OSSClient(aliyunEndpoint, accessId, accessKey);

        try {
            path = path + key;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }

            byte[] bytes = out.toByteArray();

            ObjectMetadata meta = new ObjectMetadata();

            // 设置上传文件长度
            meta.setContentLength(bytes.length);
            // 设置上传MD5校验
            String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(bytes));

            meta.setContentMD5(md5);


            client.putObject(bucketName, path, new ByteArrayInputStream(bytes), meta);


        } catch (Exception e) {
            path = null;
            log.error("上传文件到OSS出现异常:{}", e);
        } finally {
            if (client != null) {
                client.shutdown();
            }

        }
        return path;
    }

    /**
     * 获取OSS访问URL
     *
     * @param path
     * @return
     */
    public URL getOssURL(String path) {
        URL url = null;

        if (!StringUtils.isEmpty(path)) {

            path = this.clearURI(path);

            String aliyunId = oc.getAliyunAccessKeyId();

            String aliyunSecret = oc.getAliyunAccessKeySecret();

            String ossEndpoint = oc.getAliyunEndpoint();

            String bucketName = oc.getBucketName();

            try {

                OSSClient ossClient = new OSSClient(ossEndpoint, aliyunId, aliyunSecret);

                Date expires = new java.util.Date(new java.util.Date().getTime() + 3600 * 100);

                url = ossClient.generatePresignedUrl(bucketName, path, expires);

            } catch (Exception e) {
                log.error("根据路径生成OSS有效路径出现异常:{}", e);
            }
        }
        return url;
    }


    /**
     * 根据OSS系统path,删除文件
     *
     * @param path
     * @return
     */
    public void deleteOssURL(String path) {
        if (!StringUtils.isEmpty(path)) {
            String aliyunId = oc.getAliyunAccessKeyId();

            String aliyunSecret = oc.getAliyunAccessKeySecret();

            String ossEndpoint = oc.getAliyunEndpoint();

            String bucketName = oc.getBucketName();

            path = clearURI(path);

            try {

                OSSClient ossClient = new OSSClient(ossEndpoint, aliyunId, aliyunSecret);

                ossClient.deleteObject(bucketName, path);

            } catch (Exception e) {
                log.error("根据路径生成OSS有效路径出现异常:{}", e);
            }
        }
    }

    /**
     * 下载流文件
     *
     * @param path
     * @return
     */
    public OssDTO downLoadOss(String path) {
        String aliyunId = oc.getAliyunAccessKeyId();

        String aliyunSecret = oc.getAliyunAccessKeySecret();

        String ossEndpoint = oc.getAliyunEndpoint();

        String bucketName = oc.getBucketName();

        OSSClient ossClient = null;

        InputStream in = null;

        OssDTO od = null;
        try {
            od = new OssDTO();
            ossClient = new OSSClient(ossEndpoint, aliyunId, aliyunSecret);

            OSSObject ossObject = ossClient.getObject(bucketName, path);

            in = ossObject.getObjectContent();

            od.setIn(in);

            od.setOc(ossClient);
        } catch (Exception e) {
            log.error("从OSS获取文件流出现异常:{}", e);
        }
        return od;
    }

    /**
     * 下载刘文件到浏览器
     *
     * @param request
     * @param response
     * @param path
     * @param header
     * @throws Exception
     */
    public void fileDownload(HttpServletRequest request, HttpServletResponse response,
                                String path, String header) throws Exception {
        BufferedInputStream bis = null;

        BufferedOutputStream bos = null;

        OutputStream fos = null;

        InputStream fis = null;

        OssDTO od = null;

        try {

            od = this.downLoadOss(path);

            fis = od.getIn();
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            response.setHeader("Content-disposition", header);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        } catch (Exception e) {
            log.error("从OSS下载文件出现错误:{}", e);
        } finally {
            od.getOc().shutdown();
        }
    }

    /**
     * 清除阿里云访问前缀
     *
     * @param path
     * @return
     */
    private String clearURI(String path) {

        String ossEndpoint = oc.getAliyunEndpoint();

        String bucketName = oc.getBucketName();

        path = path.replace(bucketName + ".", "").replace(ossEndpoint, "");

        if (path.indexOf("?") > -1) {
            path = path.substring(0, path.indexOf("?"));
        }
        return path;
    }


}
