package com.skycloud.common.client;

import com.skycloud.common.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  <p>抽象客户端断路器</p>
 *  <p>从此类继承可在服务不可用时向管理员发短信或邮件</p>
 *
 *	@author sky
 *
 */
public abstract class AbstractClientFallback {

	/**
	 * 指定当前是邮件客户端
	 */
	protected static int EMAIL = 1;
	/**
	 * 指定当前是短信客户端
	 */
	protected static int SMS = 2;

	/**
	 * 邮件
	 */
	@Value("${hystrix.alert.email:}")
	private String email;
	/**
	 * 短信
	 */
	@Value("${hystrix.alert.sms:}")
	private String sms;

	/**
	 * 短信内容后缀
	 */
	@Value("${hystrix.alert.smsPostfix:}")
	private String smsPostfix;

	/**
	 * 告警间隔周期
	 */
	@Value("${hystrix.alert.period:600}")
	private int period;

	@Resource
	private MessageSource ms;

	@Autowired
	private ApplicationContext context;

	/**
	 * 前一次数据发送
	 */
	private Map<String, Long> lastSend = new ConcurrentHashMap<>();

	/**
	 * 类型
	 */
	private int type;

	/**
	 * 默认构造
	 */
	public AbstractClientFallback() {
	}

	/**
	 * 指定类型构造
	 * 
	 * @param type
	 *            EMAIL或SMS
	 */
	public AbstractClientFallback(int type) {
		this.type = type;
		// TODO 需要从配置服务器读取配置
	}

	protected void fallbackResult(Result result){
		result = Result.getFailureResult();
		switch (type) {
			case 1:// 当前是邮件客户端则只发短信
				sendSms();
				break;
			case 2:// 当前是短信客户端则只发邮件
				sendEmail();
				break;
			default:// 优先发短信通知
				if (!sendSms()) {
					sendEmail();
				}
		}
	}

	/**
	 * 创建断路结果
	 * 
	 * @return 断路结果
	 */
	protected Result createFallbackResult() {
		Result rs = new Result();
		fallbackResult(rs);
		return rs;
	}

	/**
	 * 判断当前的断路是否在指定周期内通知过
	 *
	 * @return
	 */
	private boolean isSend() {
		String name = getClass().getName();
		Long t = lastSend.get(name);
		if (t == null) {
			lastSend.put(name, System.currentTimeMillis());
			return false;
		}
		long time = System.currentTimeMillis();
		if (time - t > (period * 1000l)) {
			lastSend.put(name, time);
			return false;
		}
		return true;
	}

	/**
	 * 发送邮件
	 */
	private boolean sendEmail() {
		if (email.length() > 0) {
			if (isSend()) {
				return true;
			}
			/*Email e = new Email();
			e.setSubject("Service unavailable : ");
			e.setContent(getClass().getName());
			e.setTo(email);
			context.getBean(EmailClient.class).send(e);*/
			return true;
		}
		return false;
	}

	/**
	 * 发告警短信
	 *
	 * @return 是否已经发送
	 */
	private boolean sendSms() {
		if (sms.length() > 0) {
			if (isSend()) {
				return true;
			}
			/*SMSClient.SMS s = new SMSClient.SMS();
			s.setContent("");
			s.setTo(new String[]{sms});
			context.getBean(SMSClient.class).send(s);*/
			return true;
		}
		return false;
	}
}
