/*
 * Copyright (c) 2014, 2016, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.storage.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since V1.4
 * 
 */
public class UtilTools {

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
	 * 默认是utf-8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String converStr(String str) {
		return converStr(str, "UTF-8");
	}

	/**
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String converStr(String str, String encode) {
		if (str == null || str.equals("null")) {
			return "";
		}
		try {
			byte[] tmpbyte = str.getBytes("ISO8859_1");
			if (encode != null) {
				// 如果指定编码方式
				str = new String(tmpbyte, encode);
			} else {
				// 用系统默认的编码
				str = new String(tmpbyte);
			}
			return str;
		} catch (Exception e) {
		}
		return str;
	}

	// 循环遍历目录结构中的文件并添加至tar的输出流
	public static void addFiles(String rootPath, ZipOutputStream tout,
			String folderPath) {
		File srcPath = new File(rootPath + folderPath);
		int length = srcPath.listFiles().length;
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		File[] files = srcPath.listFiles();
		try {
			for (int i = 0; i < length; i++) {
				if (files[i].isFile()) {
					System.out.println("file:" + files[i].getName());
					String filename = srcPath.getPath() + File.separator
							+ files[i].getName();
					// 打开需压缩文件作为文件输入流
					FileInputStream fin = new FileInputStream(filename); // filename是文件全路径
					ZipEntry tarEn = new ZipEntry(files[i].getName()); // 此处必须使用new
					// TarEntry(File
					// file);
					// tarEn.setName(folderPath + File.separator
					// + files[i].getName()); // 此处需重置名称，默认是带全路径的，否则打包后会带全路径
					tout.putNextEntry(tarEn);
					int num;
					while ((num = fin.read(buf)) != -1) {
						tout.write(buf, 0, num);
					}
					tout.closeEntry();
					fin.close();
				} else {
					System.out.println(files[i].getPath());
					addFiles(rootPath, tout, folderPath + File.separator
							+ files[i].getName());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	// 生成tar并压缩成tar.gz
	public static void WriteToTarGzip(String rootPath, String folderPath,
			String targzipFilePath) {
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		try {
			targzipFilePath = rootPath + targzipFilePath;
			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(targzipFilePath);
			// 建立tar压缩输出流
			ZipOutputStream tout = new ZipOutputStream(fout);
			addFiles(rootPath, tout, folderPath);
			tout.close();
			fout.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		// File tarfile = new File(targzipFilePath);
		// tarfile.delete();
	}

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefile(String delpath) throws Exception {
		try {

			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();
			}

		} catch (FileNotFoundException e) {
		}
		return true;
	}

	public static String getCode() {
		String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String str2[] = str.split(",");// 将字符串以,分割
		Random rand = new Random();// 创建Random类的对象rand
		int index = 0;
		String randStr = "";// 创建内容为空字符串对象randStr
		for (int i = 0; i < 5; ++i) {
			index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
			randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
		}
		return randStr;// 输出所求的验证码的值
	}
}
