/**
 * Jquery UI Dialog
 */
$( "#dialog-msg" ).html(getSucDialog("注册成功"));
$( "#dialog-msg" ).removeClass('hide').dialog({
	show:"blind",//打开时的效果
	hide:"clip",//关闭时的效果
	closeOnEscape:true, //false 是否采用esc键退出对 话框 如果true 采用esc
	resizable: false,
	modal: true,
	title: "提示信息",
	title_html: true,
	buttons: [
		{
			text: "确定",
			"class" : "btn btn-primary btn-xs",
			click: function() {
				$( this ).dialog( "close" ); 
				show_box('login-box');
				$("button[type=\"reset\"]").trigger("click");
			} 
		}
	]
});
function getSucDialog(suctxt){
	var suc_dia_html = "<div class=\"alert alert-block alert-success\">";
	suc_dia_html = suc_dia_html + "<p><strong><i class=\"icon-ok\"></i>"+suctxt+"</strong></p><div>";
	return suc_dia_html;
}
function getErrDialog(errtxt){
	var err_dia_html = "<div class=\"alert alert-danger\">";
	err_dia_html = err_dia_html + "<p><strong><i class=\"icon-remove\"></i>"+errtxt+"</strong></p><div>";
	return err_dia_html;
}    	