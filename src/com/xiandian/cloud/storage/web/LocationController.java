/*
 * Copyright (c) 2014, 2016, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.storage.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 所有的跳转action
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.4
 * 
 */
@Controller
public class LocationController extends BaseController {

	/**
	 * 跳转到注册页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toregeister")
	public ModelAndView toregeister() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/regeister");
		return view;
	}
	
	@RequestMapping("/aaa")
	public ModelAndView aaa() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/aaa");
		return view;
	}
	
	/**
	 * 跳转到code页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/tocode")
	public ModelAndView tocode() {
		String url = "/code";
		ModelAndView view = new ModelAndView(url);
		return view;
	}
	
	
	/**
	 * 跳转到用户管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/grxx")
	public ModelAndView grxx() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/grxx");
		return view;
	}
	
	@RequestMapping("/downloadapk")
	public void downloadapk(HttpServletRequest request,HttpServletResponse response) {
		try {
			 String path = request.getSession().getServletContext().getRealPath("/upload/");
			 File file = new File(path+"/disk.apk");
			 if (!file.exists())
			 {
				 return;
			 }
			 
			// 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("disk.apk".getBytes("UTF-8"),"ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            //加上UTF-8文件的标识字符      
//            toClient.write(new byte []{(byte) 0xEF ,(byte) 0xBB ,(byte) 0xBF});
//            toClient.write(buffer);
            BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				toClient.write(buff, 0, bytesRead);
			}
            toClient.flush();
            toClient.close();
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
