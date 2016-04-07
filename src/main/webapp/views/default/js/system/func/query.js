$().ready(function () {
	
	$("#grid-table").jqGrid({
	    jsonReader:{
	         root:"list",
	         page: "page",
	         total: "total",
	         records: "records",
		     order: "order",
		     sort: "sort",
	//	     search:"_search",
		     repeatitems: false
	    },
	    search: true,
	    mtype:"POST",
		url: ctx+"/system/func/query.json",
		datatype: "json",
		prmNames:{
			page:"page",
			rows:"rows",
			total: "total",
	        records: "records",
	        order: "order",
	        sort: "sort",
	        search:"search"
		},
//		postData:{"name":"1212312"},
		height: "100%",
		colNames:['','id','名称','父Id','层级','是否为末节点','是否展开','样式', '链接',],
		colModel:[
		    {name:'myac',index:'', width:80, fixed:true,search:false, sortable:false, resize:false,
			//name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
				formatter:'actions', 
				formatoptions:{ 
					keys:true,
					delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
					editformbutton:true, editOptions:{recreateForm: true,closeAfterEdit:true, beforeShowForm:beforeEditCallback}
				}
			},
			{name:'id',index:'id', width:30,search:true, sorttype:"int", editable: true},
			{name:'name',index:'name',width:90,search:true, editable:true},
			{name:'parentId',index:'parentId', width:50,editable: true,editoptions:{size:"20",maxlength:"30"}},
			{name:'level',index:'level',width:20,search:true, editable:true},
			{name:'isLeaf',index:'isLeaf',width:40,search:true, editable:true,edittype:"checkbox",editoptions:{value:"true:false"}},
			{name:'expanded',index:'expanded',width:20,search:true, editable:true,edittype:"checkbox",editoptions:{value:"true:false"}},
			{name:'styleClass',index:'styleClass', width:70, editable: true,editoptions: {value:"Yes:No"}},
			{name:'url',index:'url', width:150, editable: true}
		], 

		viewrecords : true,//定义是否要显示总记录数
		rowNum:10,//在grid上显示记录条数，这个参数是要被传递到后台
		rowList:[10,20,30],//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		pager : "#grid-pager",//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		altRows: true,//设置表格 zebra-striped 值
		//toppager: true,
		
		multiselect: true,//定义是否可以多选
		//multikey: "ctrlKey",
        multiboxonly: true,//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
		loadComplete : function() {//当从服务器返回响应时执行，xhr：XMLHttpRequest 对象
			var table = this;
			pagerIcons();
		},
		editurl: ctx+"/system/func/modify.json",//nothing is saved定义对form编辑时的url
		caption: "功能菜单查询",//表格名称
		autowidth: true//如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth

	});


	//navButtons
	$("#grid-table").jqGrid('navGrid',"#grid-pager",
		{ 	//navbar options
			
		},
		{
			//edit record form
			closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeEditCallback(e);
				
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
				$("#grid-table").jqGrid('setGridParam',{ 
					editurl: ctx+"/system/func/add.json"
			    }); 
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeDeleteCallback(e);
			},
			onClick : function(e) {
				alert(1);
			}
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
				style_search_form(form);
			},
			afterRedraw: function(){
				style_search_filters($(this));
			}
			,
			multipleSearch: true,
			/**
			multipleGroup:true,
			showQuery: true
			*/
		},
		{
			//view record form
			recreateForm: true,
			beforeShowForm: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
			}
		}
	);

	
//	jQuery("#grid-table").jqGrid('inlineNav', "#grid-pager");
//	$("#grid-table").jqGrid('filterToolbar');
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
	
	function style_edit_form(form) {
		//enable datepicker on "sdate" field and switches for "stock" field
		form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
			.end().find('input[name=stock]')
				  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

		//update buttons classes
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
		buttons.eq(1).prepend('<i class="icon-remove"></i>')
		
		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').remove();
		buttons.eq(0).append('<i class="icon-chevron-left"></i>');
		buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
	}

	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
		buttons.eq(1).prepend('<i class="icon-remove"></i>')
	}
	
	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	}
	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
		buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
	}
	
	function beforeDeleteCallback(e) {
		$("#grid-table").jqGrid('setGridParam',{ 
			editurl: ctx+"/system/func/delete.json"
	    }); 
		var form = $(e[0]);
		if(form.data('styled')) return false;
		
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_delete_form(form);
		
		form.data('styled', true);
		
	}
	
	function beforeEditCallback(e) {
		$("#grid-table").jqGrid('setGridParam',{ 
			editurl: ctx+"/system/func/modify.json"
	    }); 
		var form = $(e[0]);
		
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_edit_form(form);

	}



	//it causes some flicker when reloading or navigating grid
	//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
	//or go back to default browser checkbox styles for the grid
	function styleCheckbox(table) {
	/**
		$(table).find('input:checkbox').addClass('ace')
		.wrap('<label />')
		.after('<span class="lbl align-top" />')


		$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		.find('input.cbox[type=checkbox]').addClass('ace')
		.wrap('<label />').after('<span class="lbl align-top" />');
	*/
	}
	

	//unlike navButtons icons, action icons in rows seem to be hard-coded
	//you can change them like this in here if you want
	function updateActionIcons(table) {
		/**
		var replacement = 
		{
			'ui-icon-pencil' : 'icon-pencil blue',
			'ui-icon-trash' : 'icon-trash red',
			'ui-icon-disk' : 'icon-ok green',
			'ui-icon-cancel' : 'icon-remove red'
		};
		$(table).find('.ui-pg-div span.ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
		*/
	}


	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
});