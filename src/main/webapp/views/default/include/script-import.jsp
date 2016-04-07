<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<!-- basic scripts -->
<!--[if !IE]> -->
<%-- <script src="${ctx}/views/default/assets/js/jquery-2.0.3.min.js"></script> --%>
<%-- <script src="${ctx}/views/default/assets/js/jquery-ui-1.10.3.full.min.js"></script> --%>
<!--[if IE]-->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='${ctx}/views/default/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="${ctx}/views/default/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/views/default/assets/js/typeahead-bs2.min.js"></script>
<!-- ace scripts -->
<script src="${ctx}/views/default/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/views/default/assets/js/ace.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${ctx}/views/default/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/views/default/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/views/default/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
<script src="${ctx}/views/default/js/common.js"></script>

<!-- Calendar -->
