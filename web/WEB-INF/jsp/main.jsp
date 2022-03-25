<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-cn"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-cn"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="zh-cn">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>先电云存储</title>
<link href="assets/stylesheets/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/stylesheets/style.css" rel="stylesheet"
	type="text/css" />
<link href="assets/stylesheets/disk.css" rel="stylesheet"
	type="text/css" />
<link href="assets/stylesheets/font-awesome.css" rel="stylesheet"
	type="text/css" />
<link href="assets/stylesheets/zTreeStyle.css" rel="stylesheet"
	type="text/css" />
</head>

<body>
	<div class="topbar">
		<div class="content">
			<div class="top_left">
				<a href="javascript:void(0)" class="topbar_menu title"
					draggable="false"> <i class="fa fa-cloud"></i>Cloud
				</a> <a class="topbar_menu this" target="_self" draggable="false"> <i
					class="font-icon menu-explorer"></i>文件管理
				</a>
			</div>
			<div class="top_right">
				<div class="menu_group">
					<a href="#" id="topbar_user" data-toggle="dropdown"
						class="topbar_menu" draggable="false"> <i
						class="font-icon icon-user"></i>管理员&nbsp;<b class="caret"></b></a>
					<ul
						class="dropdown-menu menu-topbar_user pull-right animated menuShow"
						role="menu" aria-labelledby="topbar_user">
						<li><a href="#" draggable="false"> <i
								class="font-icon fa fa-sign-out"></i>个人信息
						</a></li>
						<li><a href="#" draggable="false"> <i
								class="font-icon fa fa-sign-out"></i>退出
						</a></li>
					</ul>
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
	</div>
	<div class="frame-header">
		<div class="header-content">
			<div class="header-left">
				<div class="btn-group btn-group-sm">
					<button onclick="javascript:history.go(-1);"
						class="btn btn-default" id="history_back" title="后退" type="button">
						<i class="font-icon fa fa-arrow-left"></i>
					</button>
					<button onclick="javascript:history.go(1);" class="btn btn-default"
						id="history_next" title="前进" type="button">
						<i class="font-icon fa fa-arrow-right"></i>
					</button>
					<button onclick="javascript:location.reload();"
						class="btn btn-default" id="refresh" title="强制刷新" type="button">
						<i class="font-icon fa fa-refresh"></i>
					</button>
				</div>
			</div>
			<div class="header-middle">
				<a href="#">
					<button class="btn btn-default" id="home" title="我的文档">
						<i class="font-icon fa fa-home"></i>
					</button>
				</a>
				<div id="yarnball">
					<ul class="yarnball">
						<li class="yarnlet first"><a title="" href="#"> <span
								class="left-yarn"></span> <span class="address_ico groupSelf"></span>
								<span class="title_name">全部</span>
						</a></li>
					</ul>
				</div>
				<div id="yarnball_input" style="display: none;">
					<input type="text" name="path" value="" class="path" id="path">
				</div>
			</div>
			<div class="header-right">
				<input type="text" name="seach" id="navbarInput-01"> <a
					class="btn btn-default" id="searchfiles" title="搜索" type="button">
					<i class="font-icon fa fa-search"></i>
				</a>
			</div>
		</div>
	</div>

	<div class="main">
		<div class="main-left" style="z-index: 1;">
			<div class="main-left" style="z-index: 1;">
				<div class="list-group category" style="margin: 10px;">
					<div id="ahref">
						<a class="list-group-item active" id="r_active"> <span
							class="glyphicon glyphicon-home left-icon"></span>
							&nbsp;&nbsp;全部文件
						</a> <a class="list-group-item " id="type"> <span
							class="glyphicon glyphicon-file left-icon"></span>
							&nbsp;&nbsp;我的文档
						</a> <a class="list-group-item "> <span
							class="glyphicon glyphicon-picture left-icon"></span>
							&nbsp;&nbsp;我的图片
						</a> <a class="list-group-item " id="type"> <span
							class="glyphicon glyphicon-film left-icon"></span>
							&nbsp;&nbsp;我的视频
						</a> <a class="list-group-item " id="type"> <span
							class="glyphicon glyphicon-music left-icon"></span>
							&nbsp;&nbsp;我的音乐
						</a> <a class="list-group-item" id="type"> <span
							class="glyphicon glyphicon-inbox left-icon"></span>
							&nbsp;&nbsp;其他
						</a> <a class="list-group-item" id="type"> <span
							class="glyphicon glyphicon-share left-icon"></span>
							&nbsp;&nbsp;我的分享
						</a> <a class="list-group-item"> <span
							class="glyphicon glyphicon-trash left-icon"></span>
							&nbsp;&nbsp;垃圾箱
						</a>
					</div>
				</div>
				<div class="main-left-use">
					<div class="progress progress-u progress-xs">
						<div class="progress-bar progress-bar-blue" id="progress-bar"
							role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
					</div>
					<h3 class="heading-xs">
						容量： <span id="totle"></span> <span class="capacity pull-right">
							<a href="javascript:void(0)">扩容</a>
						</span>
					</h3>
				</div>
			</div>
		</div>

		<!--左侧导航区域代码-->
		<div class="main-right">
			<!--按钮组-->
			<div class="col-md-12" style="padding: 0">
				<div class="tools">
					<div class="tools-left">
						<div class="btn-group btn-group-sm kod_path_tool fl-left">
							<div id="result-uploader"
								class="right upload-filemain fl pull-left"></div>
							<button class="btn btn-default" type="button" id="newdir">
								<i class="font-icon fa  fa-folder-open-o"></i>新建文件夹
							</button>
							<button class="btn btn-default" style="display: none;"
								type="button" id="delete">
								<i class="font-icon glyphicon glyphicon-trash"
									style="color: #888"></i>删除
							</button>
							<a class="btn btn-default" type="button" id="share"
								style=" display: none;"> <img src="assets/images/share2.png"
								width=10px height=10px />分享
							</a> <a class="btn btn-default" type="button" style="display: none;"
								id="download"> <i
								class="font-icon glyphicon glyphicon-download-alt"
								style="color: #888"></i> 下载
							</a>
							<button class="btn btn-default" style="display: none;"
								type="button" id="rename">
								<i class="font-icon glyphicon glyphicon-pencil"
									style="color: #888"></i> 重命名
							</button>
							<a class='btn btn-default' style="display: none;" id="download">
								<span lass="glyphicon glyphicon-download-alt"><span>下载文件



								
							</a> <a class='btn btn-default' style="display: none;" id="copy">
								<span class="glyphicon glyphicon-file"></span>复制
							</a> <a class='btn btn-default' style="display: none;" id="move">
								<span class="glyphicon glyphicon-transfer"></span>移动
							</a> <a class='btn btn-default' style="display: none;" id="rename">
								<span class="glyphicon glyphicon-pencil"></span>重命名
							</a> <a class='btn btn-default' style="display: none"
								id="Dataanalysis"></a>
							<ul class="pull-left">
								<li class="dropdown"><a
									class="dropdown-toggle btn btn-default" data-toggle="dropdown"
									id="selectColumn" style="display: none"
									href="javascript:void(0);"> 数据分析</a>
									<ul class="dropdown-menu cloudanys"
										style="margin-top: 4px !important;">
										<li><a href="javascript:void(0);">词云分析</a></li>
										<li class="divider"></li>
										<li><a href="javascript:void(0);">柱状分析</a></li>
										<li class="divider"></li>
										<li><a href="javascript:void(0);">气泡分析</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>

			</div>
			<!--缩略图按钮-->
			<div class="col-md-12" style="padding: 0"></div>
			<!--文件列表-->
			<div class="col-md-12" style="padding: 0; top: 45px">
				<table class="table mb-0">
					<thead>
						<tr>
							<th class="table-checkbox"
								style="position: relative; left: 13px;"><label
								class="checkbox checkbox-position" for="checkbox1"> <span
									class="icons icon-span"> <span
										class="first-icon fui-checkbox-unchecked"></span> <span
										class="second-icon fui-checkbox-checked"></span>
								</span> <input name="chkAll" type="checkbox" id="operAll"
									value="checkbox" class="main-check" /></label></th>
							<th class="mainfile-name">文件名</th>
							<th class="hide table-fileposition mainfile-position">文件位置</th>
							<th class="mainfile-size">大小</th>
							<th class="mainchange-date">修改日期</th>
						</tr>
					</thead>
					<tbody id="tab">
						<tr>
							<td><label class="checkbox table-checkboxposition"
								for="checkbox1"> <span class="icons main-icons">
										<span class="first-icon fui-checkbox-unchecked"></span> <span
										class="second-icon fui-checkbox-checked"></span>
								</span> <input type="checkbox" name='check' class="main-tabinput">
							</label></td>
							<td><span style="display: block">cloud.doc</span>
								<div class="edit-name" style="display: none;">
									<input class="box" type="text" value="cloud.doc"> <a
										class="sure" href="javascript:void(0);"> <span
										class="glyphicon glyphicon-ok"></span></a> <a class="cancel ml-10"
										href="javascript:void(0);"> <span
										class="glyphicon glyphicon-remove"></span></a>
								</div></td>
							<td class="hide table-fileposition table-path">cloudskill/</td>
							<td>23 Bytes</td>
							<td>2017-01-11</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="assets/javascripts/jquery.min.js"></script>
	<script src="assets/javascripts/bootstrap.min.js"></script>
	<script src="assets/javascripts/fineuploader.js"></script>
	<script src="assets/javascripts/checkbox.js"></script>
	<script src="assets/javascripts/common.js"></script>
</body>
</html>
