package com.billionfun.bms.product.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: 公共配置类 
 * @Description: TODO 
 * @author  AmiTuofu
 * @date  2016年3月15日 下午10:27:43 
 *
 */
@Component("configInfo")
public class ConfigInfo {
	@Value("${velocity.event.remind}")
	private String velocityEventRemind;
	
	@Value("${velocity.user.resetpwd}")
	private String velocityUserResetpwd;
	
	@Value("${velocity.welcome}")
	private String velocityWelcome;
//	@Value("${webdav.host}")
//	private String webdavHost;
//	@Value("${webdav.port}")
//	private String webdavPort;
//	@Value("${webdav.username}")
//	private String webdavUsername;
//	@Value("${webdav.password}")
//	private String webdavPassword;
//	@Value("${cloud.resource.url}")
//	private String cloudResourceUrl;
//	@Value("${ablecloud.url}")
//	private String ablecloudUrl;
//	public String getWebdavHost() {
//		return webdavHost;
//	}
//	public void setWebdavHost(String webdavHost) {
//		this.webdavHost = webdavHost;
//	}
//	public String getWebdavPort() {
//		return webdavPort;
//	}
//	public void setWebdavPort(String webdavPort) {
//		this.webdavPort = webdavPort;
//	}
//	public String getWebdavUsername() {
//		return webdavUsername;
//	}
//	public void setWebdavUsername(String webdavUsername) {
//		this.webdavUsername = webdavUsername;
//	}
//	public String getWebdavPassword() {
//		return webdavPassword;
//	}
//	public void setWebdavPassword(String webdavPassword) {
//		this.webdavPassword = webdavPassword;
//	}
//	public String getCloudResourceUrl() {
//		return cloudResourceUrl;
//	}
//	public void setCloudResourceUrl(String cloudResourceUrl) {
//		this.cloudResourceUrl = cloudResourceUrl;
//	}
//	public String getAblecloudUrl() {
//		return ablecloudUrl;
//	}
//	public void setAblecloudUrl(String ablecloudUrl) {
//		this.ablecloudUrl = ablecloudUrl;
//	}
//	

	public String getVelocityEventRemind() {
		return velocityEventRemind;
	}

	public void setVelocityEventRemind(String velocityEventRemind) {
		this.velocityEventRemind = velocityEventRemind;
	}

	public String getVelocityUserResetpwd() {
		return velocityUserResetpwd;
	}

	public void setVelocityUserResetpwd(String velocityUserResetpwd) {
		this.velocityUserResetpwd = velocityUserResetpwd;
	}

	public String getVelocityWelcome() {
		return velocityWelcome;
	}

	public void setVelocityWelcome(String velocityWelcome) {
		this.velocityWelcome = velocityWelcome;
	}
	
}
