/*
 * Copyright (c) 2014, 2016, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.storage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户涉及到的所有控制action
 * 
 * @author 云计算应用与开发项目组
 * @since V1.4
 * 
 */
@Controller
public class UserController extends BaseController {

	/**
	 * 登录
	 * 
	 * @param request
	 *            :请求
	 */
	@RequestMapping("/getlogin")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/main");
		return view;
	}

}
