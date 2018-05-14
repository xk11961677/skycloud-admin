package com.skycloud.auth.server.entity;

import com.skycloud.auth.server.common.util.SpringUtils;
import com.skycloud.auth.server.common.entity.BasicEntity;
import com.skycloud.auth.server.service.AuthClientServiceService;

import java.util.Date;


/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public class AuthClientServiceEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	private String serviceId;
	/**
	 *
	 */
	private String clientId;
	/**
	 *
	 */
	private String description;
	/**
	 *
	 */
	private Date crtTime;
	/**
	 *
	 */
	private String crtUser;
	/**
	 *
	 */
	private String crtName;
	/**
	 *
	 */
	private String crtHost;
	/**
	 *
	 */
	private String attr1;
	/**
	 *
	 */
	private String attr2;
	/**
	 *
	 */
	private String attr3;
	/**
	 *
	 */
	private String attr4;
	/**
	 *
	 */
	private String attr5;
	/**
	 *
	 */
	private String attr6;
	/**
	 *
	 */
	private String attr7;
	/**
	 *
	 */
	private String attr8;
	
	/**
	 *获取
	 */
	public Integer getId(){
		return this.id;
	}
	
	/**
	 *设置
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
	 *获取
	 */
	public String getServiceId(){
		return this.serviceId;
	}
	
	/**
	 *设置
	 */
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	
	/**
	 *获取
	 */
	public String getClientId(){
		return this.clientId;
	}
	
	/**
	 *设置
	 */
	public void setClientId(String clientId){
		this.clientId = clientId;
	}
	
	/**
	 *获取
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 *设置
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 *获取
	 */
	public Date getCrtTime(){
		return this.crtTime;
	}
	
	/**
	 *设置
	 */
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	/**
	 *获取
	 */
	public String getCrtUser(){
		return this.crtUser;
	}
	
	/**
	 *设置
	 */
	public void setCrtUser(String crtUser){
		this.crtUser = crtUser;
	}
	
	/**
	 *获取
	 */
	public String getCrtName(){
		return this.crtName;
	}
	
	/**
	 *设置
	 */
	public void setCrtName(String crtName){
		this.crtName = crtName;
	}
	
	/**
	 *获取
	 */
	public String getCrtHost(){
		return this.crtHost;
	}
	
	/**
	 *设置
	 */
	public void setCrtHost(String crtHost){
		this.crtHost = crtHost;
	}
	
	/**
	 *获取
	 */
	public String getAttr1(){
		return this.attr1;
	}
	
	/**
	 *设置
	 */
	public void setAttr1(String attr1){
		this.attr1 = attr1;
	}
	
	/**
	 *获取
	 */
	public String getAttr2(){
		return this.attr2;
	}
	
	/**
	 *设置
	 */
	public void setAttr2(String attr2){
		this.attr2 = attr2;
	}
	
	/**
	 *获取
	 */
	public String getAttr3(){
		return this.attr3;
	}
	
	/**
	 *设置
	 */
	public void setAttr3(String attr3){
		this.attr3 = attr3;
	}
	
	/**
	 *获取
	 */
	public String getAttr4(){
		return this.attr4;
	}
	
	/**
	 *设置
	 */
	public void setAttr4(String attr4){
		this.attr4 = attr4;
	}
	
	/**
	 *获取
	 */
	public String getAttr5(){
		return this.attr5;
	}
	
	/**
	 *设置
	 */
	public void setAttr5(String attr5){
		this.attr5 = attr5;
	}
	
	/**
	 *获取
	 */
	public String getAttr6(){
		return this.attr6;
	}
	
	/**
	 *设置
	 */
	public void setAttr6(String attr6){
		this.attr6 = attr6;
	}
	
	/**
	 *获取
	 */
	public String getAttr7(){
		return this.attr7;
	}
	
	/**
	 *设置
	 */
	public void setAttr7(String attr7){
		this.attr7 = attr7;
	}
	
	/**
	 *获取
	 */
	public String getAttr8(){
		return this.attr8;
	}
	
	/**
	 *设置
	 */
	public void setAttr8(String attr8){
		this.attr8 = attr8;
	}
	
	/**
	 * 获取主键字段
	 */
	@Override
    public String primaryKey() {
    	if(id==null){
    		throw new IllegalArgumentException("主键为空!");
    	}
    	return id.toString();
    }
    
	/**
	 * 获取实体类名称
	 */
	@Override
    public String className() {
        return AuthClientServiceEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public AuthClientServiceService service() {
        return (AuthClientServiceService) SpringUtils.getBean("authClientServiceServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

