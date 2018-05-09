package com.skycloud.base.oauth.server.entity;

import com.skycloud.base.oauth.server.common.entity.BasicEntity;
import com.skycloud.base.oauth.server.common.util.SpringUtils;
import com.skycloud.base.oauth.server.service.AuthClientService;

import java.util.Date;


/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public class AuthClientEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *服务编码
	 */
	private String code;
	/**
	 *服务密钥
	 */
	private String secret;
	/**
	 *服务名
	 */
	private String name;
	/**
	 *是否锁定
	 */
	private String locked;
	/**
	 *描述
	 */
	private String description;
	/**
	 *创建时间
	 */
	private Date crtTime;
	/**
	 *创建人
	 */
	private String crtUser;
	/**
	 *创建人姓名
	 */
	private String crtName;
	/**
	 *创建主机
	 */
	private String crtHost;
	/**
	 *更新时间
	 */
	private Date updTime;
	/**
	 *更新人
	 */
	private String updUser;
	/**
	 *更新姓名
	 */
	private String updName;
	/**
	 *更新主机
	 */
	private String updHost;
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
	 *获取服务编码
	 */
	public String getCode(){
		return this.code;
	}
	
	/**
	 *设置服务编码
	 */
	public void setCode(String code){
		this.code = code;
	}
	
	/**
	 *获取服务密钥
	 */
	public String getSecret(){
		return this.secret;
	}
	
	/**
	 *设置服务密钥
	 */
	public void setSecret(String secret){
		this.secret = secret;
	}
	
	/**
	 *获取服务名
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 *设置服务名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *获取是否锁定
	 */
	public String getLocked(){
		return this.locked;
	}
	
	/**
	 *设置是否锁定
	 */
	public void setLocked(String locked){
		this.locked = locked;
	}
	
	/**
	 *获取描述
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 *设置描述
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 *获取创建时间
	 */
	public Date getCrtTime(){
		return this.crtTime;
	}
	
	/**
	 *设置创建时间
	 */
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	/**
	 *获取创建人
	 */
	public String getCrtUser(){
		return this.crtUser;
	}
	
	/**
	 *设置创建人
	 */
	public void setCrtUser(String crtUser){
		this.crtUser = crtUser;
	}
	
	/**
	 *获取创建人姓名
	 */
	public String getCrtName(){
		return this.crtName;
	}
	
	/**
	 *设置创建人姓名
	 */
	public void setCrtName(String crtName){
		this.crtName = crtName;
	}
	
	/**
	 *获取创建主机
	 */
	public String getCrtHost(){
		return this.crtHost;
	}
	
	/**
	 *设置创建主机
	 */
	public void setCrtHost(String crtHost){
		this.crtHost = crtHost;
	}
	
	/**
	 *获取更新时间
	 */
	public Date getUpdTime(){
		return this.updTime;
	}
	
	/**
	 *设置更新时间
	 */
	public void setUpdTime(Date updTime){
		this.updTime = updTime;
	}
	
	/**
	 *获取更新人
	 */
	public String getUpdUser(){
		return this.updUser;
	}
	
	/**
	 *设置更新人
	 */
	public void setUpdUser(String updUser){
		this.updUser = updUser;
	}
	
	/**
	 *获取更新姓名
	 */
	public String getUpdName(){
		return this.updName;
	}
	
	/**
	 *设置更新姓名
	 */
	public void setUpdName(String updName){
		this.updName = updName;
	}
	
	/**
	 *获取更新主机
	 */
	public String getUpdHost(){
		return this.updHost;
	}
	
	/**
	 *设置更新主机
	 */
	public void setUpdHost(String updHost){
		this.updHost = updHost;
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
        return AuthClientEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public AuthClientService service() {
        return (AuthClientService) SpringUtils.getBean("authClientServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

