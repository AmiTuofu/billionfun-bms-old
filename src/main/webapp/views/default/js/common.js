$.extend($.jgrid,{
	defaults : {
		recordtext: "View {0} - {1} of {2}",
		emptyrecords: "No records to view",
		loadtext: "Loading...",
		pgtext : "Page {0} of {1}",
	    jsonReader:{
	        root:"list",
	        page: "page",
	        total: "total",
	        records: "records",
		     order: "order",
		     sort: "sort",
		     search:"search",
		     repeatitems: false
	   },
	   prmNames:{
			page:"page",
			rows:"rows",
			total: "total",
	        records: "records",
	        order: "order",
	        sort: "sort",
	        search:"search"
	   },
	   loadComplete : function() {//当从服务器返回响应时执行，xhr：XMLHttpRequest 对象
			var table = this;
			pagerIcons();
	   },
	   search: true,
	   mtype:"POST",
	   datatype: "json",
	   height: "100%",
	   viewrecords : true,//定义是否要显示总记录数
	   rowNum:10,//在grid上显示记录条数，这个参数是要被传递到后台
	   rowList:[10,20,30],//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	   altRows: true,//设置表格 zebra-striped 值
		//toppager: true,
	   multiselect: true,//定义是否可以多选
	//   multikey: "ctrlKey",
       multiboxonly: true,//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
       autowidth: true//如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
	},
	nav : {
		edittext: "",
		edittitle: "Edit selected row",
		addtext:"",
		addtitle: "Add new row",
		deltext: "",
		deltitle: "Delete selected row",
		searchtext: "",
		searchtitle: "Find records",
		refreshtext: "",
		refreshtitle: "Reload Grid",
		alertcap: "Warning",
		alerttext: "Please, select row",
		viewtext: "",
		viewtitle: "View selected row",
		edit: true,
		editicon : 'icon-pencil blue',
		add: true,
		addicon : 'icon-plus-sign purple',
		del: true,
		delicon : 'icon-trash red',
		search: true,
		searchicon : 'icon-search orange',
		refresh: true,
		refreshicon : 'icon-refresh green',
		view: true,
		viewicon : 'icon-zoom-in grey',
		
		afterShowSearch: function(e){
			alert("asdfs");
//			var form = $(e[0]);
//			form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
//			style_search_form(form);
		},
	},
	
});	
function pagerIcons(){
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}
function beforeAdd(form,grid_id,add_url){
	$("#"+grid_id).jqGrid('setGridParam',{ 
		editurl: add_url,
    }); 	
	editFormStyle(form);
}

function beforeEdit(form,grid_id,edit_url){
	$("#"+grid_id).jqGrid('setGridParam',{ 
		editurl: edit_url,
    }); 
	editFormStyle(form);
}

function beforeDel(form,grid_id,del_url){
	$("#"+grid_id).jqGrid('setGridParam',{ 
		editurl: del_url,
    }); 
	deleteFormStyle(form);
}

function beforeSearch(form,grid_id,search_url){
	searchFormStyle(form);
}

function beforeView(form,grid_id,view_url){
	viewFormStyle(form);
}

function review(grid_id,review_url,id){
	var ids="";
	if(!empty(id)){
		ids = id;
	}else{
		ids=$('#'+grid_id).jqGrid('getGridParam','selarrrow');
	}
	$.ajax({
        type: "POST",
        url: review_url,
        data: {id:ids},
        async: false,
        beforeSend:function(XMLHttpRequest){

        },
        success: function (data) {
        	$('#'+grid_id).trigger("reloadGrid");
        },
        error: function (e) {
                alert("error");
        }
    });
}

function editFormStyle(e) {
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
	form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
		.end().find('input[name=stock]')
			  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
	buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
	buttons.eq(1).prepend('<i class="icon-remove"></i>')
	
	buttons = form.next().find('.navButton a');
	buttons.find('.ui-icon').remove();
	buttons.eq(0).append('<i class="icon-chevron-left"></i>');
	buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
}
function deleteFormStyle(e) {
	var form = $(e[0]);
	if(form.data('styled')){
		return false;
	}
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
	buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
	buttons.eq(1).prepend('<i class="icon-remove"></i>');
	form.data('styled', true);
}
function searchFormStyle(e) {
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable')
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
	buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
}
function viewFormStyle(e) {
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
}
function searchFiltersStyle(e) {
	var form = $(e[0]);
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function empty(obj){
	if(obj!=null&&obj!=""){
		return false;
	}else{
		return true;
	}
}
function getColorPicker(id,name){
	var ele_html = "";
	ele_html = ele_html +'<select id="'+id+'" name="'+name+'" class="hide">';
	ele_html = ele_html +'<option value="#ac725e">#ac725e</option>';
	ele_html = ele_html +'<option value="#d06b64">#d06b64</option>';
	ele_html = ele_html +'<option value="#f83a22">#f83a22</option>';
	ele_html = ele_html +'<option value="#fa573c">#fa573c</option>';
	ele_html = ele_html +'<option value="#ff7537">#ff7537</option>';
	ele_html = ele_html +'<option value="#ffad46" selected="">#ffad46</option>';
	ele_html = ele_html +'<option value="#42d692">#42d692</option>';
	ele_html = ele_html +'<option value="#16a765">#16a765</option>';
	ele_html = ele_html +'<option value="#7bd148">#7bd148</option>';
	ele_html = ele_html +'<option value="#b3dc6c">#b3dc6c</option>';
	ele_html = ele_html +'<option value="#fbe983">#fbe983</option>';
	ele_html = ele_html +'<option value="#fad165">#fad165</option>';
	ele_html = ele_html +'<option value="#92e1c0">#92e1c0</option>';
	ele_html = ele_html +'<option value="#9fe1e7">#9fe1e7</option>';
	ele_html = ele_html +'<option value="#9fc6e7">#9fc6e7</option>';
	ele_html = ele_html +'<option value="#4986e7">#4986e7</option>';
	ele_html = ele_html +'<option value="#9a9cff">#9a9cff</option>';
	ele_html = ele_html +'<option value="#b99aff">#b99aff</option>';
	ele_html = ele_html +'<option value="#c2c2c2">#c2c2c2</option>';
	ele_html = ele_html +'<option value="#cabdbf">#cabdbf</option>';
	ele_html = ele_html +'<option value="#cca6ac">#cca6ac</option>';
	ele_html = ele_html +'<option value="#f691b2">#f691b2</option>';
	ele_html = ele_html +'<option value="#cd74e6">#cd74e6</option>';
	ele_html = ele_html +'<option value="#a47ae2">#a47ae2</option>';
	ele_html = ele_html +'<option value="#555">#555</option>';
	ele_html = ele_html +'</select>';
	return ele_html;
}
$.fn.getHexBackgroundColor = function() {
	var rgb = $(this).css('background-color');
	rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	function hex(x) {
		return ("0" + parseInt(x).toString(16)).slice(-2);
	}
	rgb= "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	return rgb; 
}
var dictionarys = [];
function getDictionary(){
	if(dictionarys.length==0){
		$.ajax({
	        type: "POST",
	        url: ctx + "/system/dictionary/getall.json",
	        data: {},
	        async: false,
	        beforeSend:function(XMLHttpRequest){
	
	        },
	        success: function (data) {
	        	dictionarys = data.list;
	        },
	        error: function (e) {
	                alert("error");
	        }
	    });
	}
	return dictionarys;
}
function getUserDicTypes(){
	dictionarys = getDictionary();
	var userdtype_arr = [];
	$.each(dictionarys, function(i, item) {
		if(item.typeId=="1"){
			userdtype_arr.push(item.id+":"+item.name);
		}
    });
	return userdtype_arr;
}
function getDicValue(code,dic_arr){
	var temp = "";
	$.each(dic_arr, function(i, item) {
		var item_arr = item.split(":");
		if(item_arr[0]==code){
			temp = item_arr[1];
		}
    });
	return temp ;
}

/*jQuery.extend(jQuery.validator.messages, {
  required: "必填字段",
  remote: "请修正该字段",
  email: "请输入正确格式的电子邮件",
  url: "请输入合法的网址",
  date: "请输入合法的日期",
  dateISO: "请输入合法的日期 (ISO).",
  number: "请输入合法的数字",
  digits: "只能输入整数",
  creditcard: "请输入合法的信用卡号",
  equalTo: "请再次输入相同的值",
  accept: "请输入拥有合法后缀名的字符串",
  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
  min: jQuery.validator.format("请输入一个最小为{0} 的值")
});*/
