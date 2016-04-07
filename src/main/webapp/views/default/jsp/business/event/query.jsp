<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>日历管理</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/views/default/assets/css/fullcalendar.min.css" />
<link rel="stylesheet" href="${ctx}/views/default/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/views/default/assets/css/bootstrap-datetimepicker.min.css" />
<%-- <link rel="stylesheet" href="${ctx}/views/default/assets/css/fullcalendar.print.css" /> --%>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				Full Calendar <small> <i class="icon-double-angle-right"></i>
					with draggable and editable events
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-sm-9">
						<div class="space"></div>

						<div id="calendar"></div>
					</div>

					<div class="col-sm-3">
						<div class="widget-box transparent">
							<div class="widget-header">
								<h4>Draggable events</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main no-padding">
									<div id="external-events">
										<div class="external-event " data-class="label-grey" style="background-color:#c2c2c2;">
											<i class="icon-move"></i> 已完成
										</div>

										<div class="external-event "
											data-class="label-success" style="background-color:#42d692;">
											<i class="icon-move"></i> My Event 2
										</div>

										<div class="external-event "
											data-class="label-danger" style="background-color:#ff7537;">
											<i class="icon-move"></i> 工作
										</div>

										<div class="external-event "
											data-class="label-purple" style="background-color:#9a9cff;">
											<i class="icon-move"></i> 家庭
										</div>

										<div class="external-event "
											data-class="label-yellow" style="background-color:#fad165;">
											<i class="icon-move"></i> 个人
										</div>

										<div class="external-event " style="background-color:#f83a22;" data-class="label-pink">
											<i class="icon-move"></i> 非常紧急
										</div>

										<div class="external-event " style="background-color:#4986e7;" data-class="label-info">
											<i class="icon-move"></i> My Event 7
										</div>

										<label> <input type="checkbox"
											class="ace ace-checkbox" id="drop-remove" /> <span
											class="lbl"> Remove after drop</span>
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	<script src="${ctx}/views/default/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${ctx}/views/default/assets/js/bootstrap-colorpicker.min.js"></script>
	<!-- inline scripts related to this page -->
	<script src="${ctx}/views/default/js/business/event/query.js"></script>-->
</body>
</html>
