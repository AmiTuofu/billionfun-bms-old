<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>文件管理</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/views/default/assets/css/fullcalendar.min.css" />
<link rel="stylesheet" href="${ctx}/views/default/assets/css/datepicker.css" />
<%-- <link rel="stylesheet" href="${ctx}/views/default/assets/css/fullcalendar.print.css" /> --%>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				文件管理<small> <i class="icon-double-angle-right"></i>
					with draggable and editable events
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<iframe src="${src }" id="iframe1" style="width:100%;min-height:600px;_height:600px; "  frameborder="0" scrolling="auto"></iframe>
				</div>

				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->

	<%@include file="/views/default/include/script-import.jsp"%>
	<script src="${ctx}/views/default/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="${ctx}/views/default/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${ctx}/views/default/assets/js/fullcalendar.min.js"></script>
	<script src="${ctx}/views/default/assets/js/bootbox.min.js"></script>
	<script src="${ctx}/views/default/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/views/default/assets/js/bootstrap-colorpicker.min.js"></script>
	<!-- inline scripts related to this page -->

</body>
</html>
