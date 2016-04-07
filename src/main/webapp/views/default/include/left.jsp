<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
</script>

<div class="sidebar-shortcuts" id="sidebar-shortcuts">
	<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
		<button class="btn btn-success">
			<i class="icon-signal"></i>
		</button>

		<button class="btn btn-info">
			<i class="icon-pencil"></i>
		</button>

		<button class="btn btn-warning">
			<i class="icon-group"></i>
		</button>

		<button class="btn btn-danger">
			<i class="icon-cogs"></i>
		</button>
	</div>

	<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
		<span class="btn btn-success"></span>

		<span class="btn btn-info"></span>

		<span class="btn btn-warning"></span>

		<span class="btn btn-danger"></span>
	</div>
</div><!-- #sidebar-shortcuts -->

<ul class="nav nav-list" id="nav-list">
	
</ul><!-- /.nav-list -->

<div class="sidebar-collapse" id="sidebar-collapse">
	<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>

<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
</script>
<script type="text/javascript">
	var funcArr =[
	<c:forEach var="func" items="${SESSION_USER.listFuncs}">
	{'id':'${func.id}','name':'${func.name}','parentId':'${func.parentId}','styleClass':'${func.styleClass}','url':'${func.url}'},
	</c:forEach>];
</script>
<script src="${ctx}/views/default/js/left.js"></script>