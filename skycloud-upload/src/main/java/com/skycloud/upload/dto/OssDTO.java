package com.skycloud.upload.dto;

import com.aliyun.oss.OSSClient;
import lombok.Data;

import java.io.InputStream;

/**
 * @author sky
 * @description
 * @create 2017-11-27 下午4:19
 **/
@Data
public class OssDTO {

    private InputStream in;

    private OSSClient oc;
}
