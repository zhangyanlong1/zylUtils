package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class FileUtils {
	
	
	
	/**
	 * @Title: readFile
	 * @Description: 读取文件内容
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 * @return: String
	 */
	public static String readFile(File file, String charset) throws IOException {
		// 创建输出流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		// 定义缓冲对象
		StringBuffer sb = new StringBuffer();
		// 定义读取每行的结果
		String content = null;
		// 循环读取
		while ((content = br.readLine()) != null) {
			// 加入缓冲对象
			sb.append(content);
		}
		// 关闭流
		br.close();
		// 返回结果
		return sb.toString();

	}

	/**
	 * 获取文件的扩展名
	 * @param fileName
	 * @return
	 */
	
	public static String getSuffixName(String fileName) {
		
		int dotPos = fileName.lastIndexOf(".");
		if(dotPos<0) {
			return "";
		}
		return fileName.substring(dotPos);
		
	}
	
	
	/**
	 * 删除文件
	 */
	public static void delFile(String  fileName) {
		File file = new File(fileName);
		String fileSeperator =  getProperty("file.separator");
		System.out.println(fileSeperator);
		
		if(!file.exists()) 
			return  ;
		
		if(file.isDirectory()) {
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				String childFileName = fileName+ fileSeperator +list[i];
				delFile(childFileName);
			}
		}
		
	}


	/**
	 * 根据属性key 获取系统环境变量值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		
		Properties properties = System.getProperties();
		/*//获取环境变量
		//System.getenv();
		
		//ArrayList<String> arrayList = new ArrayList<String>();
		//
		//arrayList.forEach(x->{System.out.println(x);});
		 Set<Object> keySet = properties.keySet();
		for (Object hKey: keySet) {
			System.out.println("hKey is " + hKey);
			System.out.println("hValue is " + properties.getProperty((String) hKey));
		}*/
		return properties.getProperty(key);
	} 
	
	/**
	 * 获取系统的环境变量
	 * @param key
	 * @return
	 */
	public static String getEnv(String key) {
		
		Map<String, String> getenv = System.getenv();
		/*getenv.forEach((hKey,hValue)->{
			System.out.println("key is " + hKey );
			System.out.println("vlaue is " + hValue );
		});*/
		return getenv.get(key);
	}

	/**
	 * 获取文件的大小
	 * @param fileName
	 * @return
	 */
	public static long getSize(String fileName) {
		
		File file = new File(fileName);
		if(!file.exists() || ! file.isFile())
			return 0;
		return file.length();
		
	}
	
	/**
	 * 读取文件
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String read(String fileName) throws IOException {
		
		//用于存储文件内容
		StringBuilder sb = new StringBuilder();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			sb.append(ln);
		}
		
		closeStream(br,fis);
		
		return sb.toString();
	}
	
	
	/**
	 * 按行读取文件
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static List<String> readByLines(String fileName) throws IOException {
		
		//用于存储文件内容
		List<String> lines = new ArrayList();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			//sb.append(ln);
			lines.add(ln);
		}
		
		closeStream(br,fis);
		
		return lines;
	}
	
	
	
	/**
	 *  关闭流
	 * @param stream
	 * @throws IOException 
	 */
	public static void closeStream(Closeable ... stream) throws IOException {
		
		for (int i = 0; i < stream.length; i++) {
			stream[i].close();
		}
	}
	
	/**
	 * 复制文件
	 * @param srcFile  源文件
	 * @param dstFile  目标文件
	 * @throws IOException 
	 */
	public synchronized static void copy(String srcFileName ,String dstFileName) throws IOException {
		// 源文件
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos  = new FileOutputStream(dstFile);
		byte b[]=new byte[1024];
		
		while(fis.read(b)>0) {
			fos.write(b);
		}
		closeStream(fos,fis);
	}
	
	/**
	 * @Title: writeFile
	 * @Description: 按照指定的编码把内容写入指定的文件中
	 * @param path
	 * @param content
	 * @param charset
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 判断父目录是否存在
		if (!file.getParentFile().exists()) {
			// 创建父目录
			file.getParentFile().mkdirs();
		}
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		if(content!=null) {
			bw.write(content);
			
		}
		bw.flush();
		bw.close();
	}
	
	
	/*
	* 方法1：给定一个文件名，返回该文件名的扩展名，例如“aaa.jpg”，返回“.jpg”(10分)
	*/
	public static String getExtendName(String fileName){
		int i = fileName.lastIndexOf(".");
		String substring = fileName.substring(i);
		return substring;
	}
	
	
	

}
