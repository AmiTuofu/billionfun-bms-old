package com.billionfun.bms.product.mall.common.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Util {
	/**
	 * 
	 * @Title: encode 
	 * @Description: TODO 将密码进行加密
	 * @param @param rawPass 密码
	 * @param @param salt 密钥
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public final static String encode(String rawPass, String salt) {   
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
						
        return md5.encodePassword(rawPass, salt);
    } 
	
	/**
	 * 
	 * @Title: valid 
	 * @Description: TODO
	 * @param @param encPass
	 * @param @param rawPass
	 * @param @param salt
	 * @param @return 
	 * @return boolean
	 * @throws
	 */
	public final static boolean valid(String encPass, String rawPass, String salt) {   
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
				
        return md5.isPasswordValid(encPass, rawPass, salt);
    } 
	
	
	public static void main(String[] args){
		//1353e154e737b532853783c8b726cdb1
		//c2d4f75bf8a4d3041fdba404adbe5bac
		//7f9458a43a113bcceba1b6bb1945ac18
		System.out.println(encode("111111", "zhuyi"));
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		md5.setEncodeHashAsBase64(false);
//		md5.
	}
}
