package com.skycloud.upload.configuration;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;
import lombok.Setter;

/**
 * 上传配置
 *
 * @author sky
 */
@Slf4j
@ConfigurationProperties(prefix = "upload")
public class UploadConfiguration {

    /**
     * MIME类型
     */
    @Getter
    private Map<String, String> allMimeTypes = new HashMap<>();

    /**
     * 构造预告定义的MIME类型
     */
    public UploadConfiguration() {
        allMimeTypes.put("html", "text/html");
        allMimeTypes.put("htm", "text/html");
        allMimeTypes.put("jpg", "image/jpeg");
        allMimeTypes.put("jpeg", "image/jpeg");
        allMimeTypes.put("bmp", "image/bmp");
        allMimeTypes.put("gif", "image/gif");
        allMimeTypes.put("png", "image/png");
        allMimeTypes.put("doc", "application/msword");
        allMimeTypes.put("docx", "application/msword");
        allMimeTypes.put("xls", "application/msexcel");
        allMimeTypes.put("xlsx", "application/msexcel");
        allMimeTypes.put("mp3", "audio/mpeg");
    }

    /**
     * 广播地址
     */
    private static final String BROADCAST_ADD = new String(new char[]{'0', '.', '0', '.', '0', '.', '0'});

    /**
     * 最大上传文件大小(默认KB)
     */
    @Setter
    @Getter
    private int maxFileSize = 10 * 1024;

    /**
     * 日期在路径中的格式（默认为yy/MM/dd）
     */
    @Setter
    @Getter
    private String datePath = "yy/MM/dd";

    /**
     * 上传文件的访问域名（如192.168.1.11:img1,192.168.1.12:img2）
     */
    @Setter
    @Getter
    private String domain;

    /**
     * 域名后缀
     */
    @Setter
    @Getter
    private String domainPostfix;

    /**
     * 安全域名前缀
     */
    @Setter
    @Getter
    private String secDomainPostfix;

    /**
     * 安全域名列表，如192.168.1.11:img3,192.168.1.12:img4
     */
    @Setter
    @Getter
    private String secDomain;

    /**
     * 上传目录名，默认为(upload)
     */
    @Setter
    @Getter
    private String folder = "upload";

    /**
     * 安全目录名，默认为(sec)
     */
    @Setter
    @Getter
    private String secFolder = "sec";

    /**
     * 工作目录(如/home/workspace)
     */
    @Setter
    @Getter
    private String workspace;

    /**
     * 工作IP
     */
    @Setter
    @Getter
    private String ip;

    /**
     * 支持的文件类型
     */
    @Setter
    @Getter
    private String mimeTypes;

    @Resource
    private ApplicationContext context;

    /**
     * 环境
     */
    @Resource
    private Environment env;

    /**
     * 完成初始化
     */
    @PostConstruct
    public void init() {
        if (ip == null) {
            ip = findIp();//查找本机IP地址
        }
        String port = env.getProperty("server.port");
        int p = port == null ? 8080 : Integer.parseInt(port);
        if (domainPostfix == null) {// 默认域名采用本机
            domainPostfix = ip + (p == 80 ? "" : (":" + p))
                    + ((WebApplicationContext) context).getServletContext().getContextPath();
        }
        if (secDomainPostfix == null) {
            secDomainPostfix = domainPostfix;
        }
        domain = getDomain(domainPostfix, domain);
        secDomain = getDomain(secDomainPostfix, secDomain == null ? domain : secDomain);
        if (workspace == null) {// 默认工作空间为临时目录
            workspace = System.getProperty("java.io.tmpdir").replace('\\', '/').replace("//", "/");
        }
        folder = (workspace + "/" + folder).replace("//", "/");
        secFolder = (workspace + "/" + secFolder).replace("//", "/");
        initMimeTypes();
    }

    /**
     * 初始化mimeTypes
     */
    private void initMimeTypes() {
        if (mimeTypes != null) {
            for (String s : mimeTypes.split("[,;]")) {
                int k = s.indexOf(':');
                if (k != -1) {
                    allMimeTypes.put(s.substring(0, k).trim(), s.substring(k + 1).trim());
                }
            }
        }
    }

    /**
     * 获得真实的域名
     *
     * @param postfix
     * @param domain
     * @return
     */
    private String getDomain(String postfix, String domain) {
        if (domain != null) {
            for (String s : domain.split("[,]")) {
                int k = s.indexOf(':');
                if (k != -1 && ip.equals(s.substring(0, k).trim())) {
                    return "http://" + s.substring(k + 1) + "." + postfix;
                }
            }
        }
        return "http://" + postfix;
    }

    /**
     * 查询本机内网IP
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static String findIp() {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                String add = getLocalIp(netInterface);
                if (add != null) {
                    return add;
                }
            }
        } catch (java.net.SocketException e) {
            log.error("Find local ip error ", e);
        }
        return "localhost";
    }

    /**
     * 获得其中的内网IP
     *
     * @param netInterface
     * @return
     * @throws SocketException
     */
    private static String getLocalIp(NetworkInterface netInterface) throws SocketException {
        for (InterfaceAddress add : netInterface.getInterfaceAddresses()) {
            if (add.getBroadcast() != null && !BROADCAST_ADD.equals(add.getBroadcast().getHostAddress())) {
                String ip = add.getAddress().getHostAddress();
                if (ip.startsWith("10.") || ip.startsWith("172.") || ip.startsWith("192.")) {
                    return ip;
                }
            }
        }
        return null;
    }
}
