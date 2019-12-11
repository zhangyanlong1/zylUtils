package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author DELL
 *
 */
public class StringUtils {
	

	/**
	 * 随机字符串源
	 */
	static char charArray[] = new char[36];
	static {
		for(int i =0;i<10;i++) {
			charArray[i] = (char)('0' + i);
		}
		for (int i = 0; i < 26; i++) {
			charArray[10+i] = (char) ('A'+i);
		}
	}
	
	
	/**
	 * 邮箱验证
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		
		String pattern = "^\\w+@\\w+\\.[a-zA-Z]{2,3}$";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	/**
	 * 测试空字符串
	 * @param str
	 * @return
	 */
	public static  boolean isBlank(String str) {
		return str==null||str.trim().equals("");
	}
	
	/**
	 * 测试  是否有值
	 */
	public static boolean haveValue(String str) {
		return null!=str || !"".equals(str.trim());
	}
	
	/**
	 *判断是否是一个手机号
	 * @return 
	 */
	public static boolean isMobile(String str) {
		String regex ="^1\\d{10}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
		
	}
	
	/**
	 * 长度为n的随机用英文字符串
	 * 
	 */
	public static String  getRandomStr(int n) {
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <n; i++) {
			
			int nextInt = random.nextInt(26);
			char randomChar =(char) ('A'+nextInt);
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	/**
	 * 长度为n的随机用英文字符串
	 * 
	 */
	public static String  getRandomNumStr(int n) {
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <n; i++) {
			
			int index = random.nextInt(36);
			char randomChar =charArray[index];
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	
	//生成随机汉字
	public static String getGb2312(int n) throws UnsupportedEncodingException {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(getGb2312());
		}
		return sb.toString();
	}
	
	
	//获取一个随机中文字
	public static String getGb2312() throws UnsupportedEncodingException {
		
		byte word[] = new byte[2];
		
		Random random = new Random();
		
		word[0]=(byte) (0xA1+16+ random.nextInt(39));
		word[1]=(byte) (0xA1+random.nextInt(94));
		return new String(word,"GBK");
		
	}
}
