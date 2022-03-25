/*
 * Copyright (c) 2014, 2016, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.storage.sh.util;

import java.io.IOException;
import java.util.Properties;

import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.model.Account;

import com.xiandian.cloud.storage.sh.security.QEncodeUtil;


/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.4
 * 
 */
public class SwiftUtilTools {
	public static String COFING_FILE = "/config/xiandian.properties";
	public static Properties p;

	public static Properties getConfig() {
		if (p == null) {
			p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(COFING_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	/**
	 * 获取账户
	 * @param username:用户名
	 * @param password:密码
	 * @return
	 */
	public static Account getAccount(String username,String password) {
		AccountConfig config = new AccountConfig();
		config.setUsername(username);
		String inputstr = QEncodeUtil.aesDecrypt(password, QEncodeUtil.ydy);
		config.setPassword(inputstr);
		config.setAuthUrl("http://"+SwiftUtilTools.getConfig().getProperty("AUTHURL")+":5000/v2.0/tokens");
		config.setTenantName(username);
		System.out.println("authurl ------------ "+("http://"+SwiftUtilTools.getConfig().getProperty("AUTHURL")+":5000/v2.0/tokens"));
		Account account = new AccountFactory(config).createAccount();
		return account;
	}
	
	public static String replaceStr(String str) {
		str = str.replaceAll("%2F", "/");
		return str;
	}
	
	private static String imgeArray[] = { "bmp", "dib", "gif", "jfif", "jpe",
		"jpeg", "jpg", "png", "tif", "tiff", "ico" };

	private static String docArray[] = { "doc", "docx", "ppt", "pptx", "xls",
		"xlsx", "txt", "pdf", "csv", "xml", "sql" };

	private static String aviArray[] = { "avi", "wmv", "rmvb", "rm", "mp4",
		"mpg", "mkv", "swf", "flv", "asf", "mov" };

	private static String mp3Array[] = { "mp3", "wav", "mod", "mid", "cda",
		"ogg", "mod", "ra" };
	
	public static boolean isImage(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = imgeArray.length;
		for (int i = 0; i < len; i++) {
			if (imgeArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDoc(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = docArray.length;
		for (int i = 0; i < len; i++) {
			if (docArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMediea(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = aviArray.length;
		for (int i = 0; i < len; i++) {
			if (aviArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMp3(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = mp3Array.length;
		for (int i = 0; i < len; i++) {
			if (mp3Array[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static String convertFileSize(long filesize) 
	{ 
		String strUnit="Bytes"; 
		String strAfterComma=""; 
		int intDivisor=1; 
		if(filesize>=1024*1024) 
	
		{ 
		strUnit = "MB"; 
		intDivisor=1024*1024; 
		} 
		else if(filesize>=1024) 
		{ 
		strUnit = "KB"; 
		intDivisor=1024; 
		} 
		if(intDivisor==1) return filesize + " " + strUnit; 
		strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor ; 
		if(strAfterComma=="") strAfterComma=".0"; 
		return filesize / intDivisor + "." + strAfterComma + " " + strUnit; 
	} 
}
