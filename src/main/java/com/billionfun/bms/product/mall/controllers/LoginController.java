package com.billionfun.bms.product.mall.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.billionfun.bms.product.mall.common.Contants;
import com.billionfun.bms.product.mall.common.exception.ErrorCode;
import com.billionfun.bms.product.mall.common.utils.MD5Util;
import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.vo.SysUserVO;

/**
 * 
 * @ClassName: DefaultController
 * @Description: TODO
 * @author AmiTuofu
 * @date 2015年12月21日 上午12:04:50
 * 
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	/**
	 * 
	 * @Title: 登录
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping({ "/login" })
	public Object login() {

		return "login";
	}

	/**
	 * 
	 * @Title: 首页
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(ModelMap modelMap) {
		modelMap.put("user", getCurrentUser());

		return "index";
	}

	/**
	 * 
	 * @Title: 内嵌框架
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/iframe")
	public String iframe(ModelMap modelMap) {

		return "iframe";
	}

	/**
	 * 
	 * @Title: 登出
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/logout")
	public String logout(ModelMap modelMap) {
		session.removeAttribute(Contants.SESSION_USER);
		return "login";
	}

	/**
	 * 
	 * @Title: 注册
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/register")
	public String register(@RequestParam(value = "email") String email,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "mobile", required = false) String mobile,
			ModelMap modelMap) {
		if (userService.isExsit(username, email, mobile)) {
			modelMap.put("retCode", ErrorCode.USER_EXIST_ERROR.getErrCode());
			modelMap.put("retMsg", ErrorCode.USER_EXIST_ERROR.getErrMsg());
			return "";
		}
		SysUser user = new SysUser();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(MD5Util.encode(password, username));
		userService.save(user);
		modelMap.put("retCode", "1");
		modelMap.put("retMsg", "注册成功");
		return "";
	}

	/**
	 * 
	 * @Title: 忘记密码
	 * @Description: TODO
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/forpwd")
	public String forPwd(ModelMap modelMap,
			@RequestParam(value = "email") String email) {
		SysUserVO vo = new SysUserVO();
		vo.setEmail(email);
		userService.resetPWD(vo);
		modelMap.put("retCode", "1");
		modelMap.put("retMsg", "重置密码成功");
		return "";
	}
}
