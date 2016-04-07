<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
   <title>首页</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style>
.top{
	height: 70px;
	vertical-align: 
}
</style>
</head>
<body>
	<div id="top" class="container-fluid" >
		<div class="row-fluid">
			<div class="col-md-3">
				<h1>Base Frame</h1>
			</div>
			<div class="col-md-2 col-md-offset-6 ">
				<h4 class="text-center top">hello,Admin</h4>
			</div>
			<div class="col-md-1">
				<h4 class="text-center top" ><a href="j_spring_security_logout">logout</a></h4>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-3">
				left
			</div>
			<div class="col-md-9">
				right
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-12">
				footer
			</div>
		</div>
	</div>
</body>
</html>