//package com.zyk.cloud.common.client;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Locale;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.core.io.AbstractResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
///**
// * 文件客户端。 <br>
// * 上传文件等操作
// *
// *
// */
//public interface FileClient {
//	/**
//	 * 上传文件
//	 * @param file 文件
//	 * @return 上传结果
//	 */
//	UploadResult upload(File file,String type);
//
//	/**
//	 * 上传文件
//	 * @param file 文件
//	 * @param operator 当前用户
//	 * @return 上传结果
//	 */
//	UploadResult upload(File file,String type,String operator);
//
//	/**
//	 * 上传文件
//	 * @param file 文件流
//	 * @return 上传结果
//	 */
//	UploadResult upload(MultipartFile file,String type);
//
//	/**
//	 * 上传文件
//	 * @param file 文件流
//	 * @param operator 当前用户
//	 * @return 上传结果
//	 */
//	UploadResult upload(MultipartFile file,String type,String operator);
//
//	/**
//	 * 上传结果
//	 *
//	 * @author lj
//	 *
//	 */
//	@Data
//	@EqualsAndHashCode(callSuper = true)
//	static class UploadResult extends Result {
//		private String name;
//		private String url;
//	}
//
//	/**
//	 * 实现类
//	 *
//	 * @author lj
//	 *
//	 */
//	@Service
//	static class FileClientImpl implements FileClient {
//
//		/**
//		 * RestTemplate
//		 */
//		@Autowired
//		private RestTemplate template;
//
//		@Resource
//		private MessageSource ms;
//
//		@Override
//		public UploadResult upload(MultipartFile file, String type, String operator) {
//			return upload(new MultipartFileResource(file), type, operator);
//		}
//
//		/**
//		 * 上传
//		 *
//		 * @param file
//		 *            上传文件
//		 * @param type
//		 *            类型
//		 * @param operator
//		 *            操作员
//		 * @return 上传结果
//		 */
//		private UploadResult upload(AbstractResource file, String type, String operator) {
//			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<>();
//			mvm.add("file", file);
//			mvm.add("key", "app_pic");
//			if (operator != null) {
//				mvm.add("operator", operator);
//			}
//			try {
//				return template.postForObject("http://upload/file", mvm, UploadResult.class);
//			} catch (Exception e) {
//				UploadResult rs = new UploadResult();
//				rs.setReason(ms.getMessage(ClientConfiguration.SERVICE_UNAVAILABLE, new Object[0],
//						ClientConfiguration.SERVICE_UNAVAILABLE, Locale.getDefault()));
//				return rs;
//			}
//		}
//
//		@Override
//		public UploadResult upload(MultipartFile file, String type) {
//			return upload(file, type, null);
//		}
//
//		@Override
//		public UploadResult upload(File file, String type) {
//			return upload(new FileSystemResource(file), type, null);
//		}
//
//		@Override
//		public UploadResult upload(File file, String type, String operator) {
//			return upload(new FileSystemResource(file), type, null);
//		}
//
//		/**
//		 * 上传文件资源
//		 *
//		 * @author lj
//		 *
//		 */
//		private static class MultipartFileResource extends AbstractResource {
//
//			// 上传文件
//			private MultipartFile file;
//
//			/**
//			 * 构造
//			 *
//			 * @param file
//			 *            上传文件
//			 */
//			public MultipartFileResource(MultipartFile file) {
//				this.file = file;
//			}
//
//			@Override
//			public boolean exists() {
//				return !file.isEmpty();
//			}
//
//			@Override
//			public String getDescription() {
//				return file.getOriginalFilename();
//			}
//
//			@Override
//			public InputStream getInputStream() throws IOException {
//				return file.getInputStream();
//			}
//
//			@Override
//			public long contentLength() {
//				return file.getSize();
//
//			}
//
//			@Override
//			public String getFilename() {
//				return file.getOriginalFilename();
//
//			}
//
//		}
//
//	}
//}
