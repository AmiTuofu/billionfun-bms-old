<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>商品管理管理</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	</head>
	<body>
						<div class="page-content">
						<div class="page-header">
							<h1>
								商品管理管理
								<small>
									<i class="icon-double-angle-right"></i>
									查看
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="goods-grid-table"></table>

								<div id="goods-grid-pager"></div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				
		<%@include file="/views/default/include/script-import.jsp"%>
		<!-- inline scripts related to this page -->
		<script src="${ctx}/views/default/js/bussiness/mall/goods/query.js"></script>
</body>
</html>
