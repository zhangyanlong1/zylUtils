package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author DELL
 *
 */
public class StringUtils {
	
	 //随机生成名字
    public static String getName(){
        String name = "";
//        System.out.println(c);
        for (int i = 0; i <3 ; i++) {
            char c = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
            name+=c;
        }
        return name;
    }
    //随机生成性别
    public static String getSex(){
        Random r = new Random();
        int i = r.nextInt(2);
        if(i==0){
            return "男";
        }else{
            return "女";
        }

    }

    //随机生成生日
    public static String getBirthday(){
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1949, 2001);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    //随机生成邮箱
    public static String getMail(){
        String [] last = {"@qq.com", "@gmail.com", "@163.com", "@sina.com", "@hotmail.com", "@sohu.com"};
        StringBuffer sb = new StringBuffer();
        // 3~20长度，包含3及20
        int length = 3 + (int) (Math.random() * 9);
        String word = "";
        for (int i = 0; i < length; i++) {
            word += (char) randomChar();
        }
        sb.append(word);
        Random r = new Random();
        final int j = r.nextInt(6);
        sb.append(last[j]);
        return sb.toString();
    }
    private static byte randomChar() {
        // 0<= Math.random()< 1
        int flag = (int) (Math.random() * 2);// 0小写字母1大写字母
        byte resultBt;
        if (flag == 0) {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (65 + bt);
        } else {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (97 + bt);
        }
        return resultBt;
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    //随机生成电话号码133开头
    private static String[] telFirst = "133".split(",");
    public static String getPhone(){
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;

    }
	
	

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
 * 判断地址格式
 * @param str
 * @return
 */
	public static boolean isHttpUrl(String str){
		
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
               + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184               
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "[0-9a-z]*"  // 或单域名
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
      
        return  str.matches(regex);	
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
