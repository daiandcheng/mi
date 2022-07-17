package com.edu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 *   把一个任意长度的字符串，采用散列算法（哈希算法）
 *   转变成一个32位的字符串，并且这个过程是不可逆的。
 * @author daixianren
 *
 */
public class MD5Util {
	public static void main(String[] args) {
		String code = getMD5("123");
		System.out.println(code);
		code = getMD5("12");
		System.out.println(code);
		code = getMD5("12");
		System.out.println(code);
	}
	
	public static String getMD5(String str)  {
		 try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			 md.update(str.getBytes());
			 // 返回一个16进制的 用于生成的哈希值的字节数组
			 byte[] digest = md.digest() ;
			 StringBuilder sb = new StringBuilder() ;
			 for(byte b:digest) {
				String s = Integer.toHexString(b & 0xFF) ;
				if(s.length() == 1) {
					sb.append("0"+s) ;
					continue ;
				}
				sb.append(s) ;
			 }
			 return sb.toString() ;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
