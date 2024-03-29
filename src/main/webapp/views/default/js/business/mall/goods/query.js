$().ready(function () {
	$("#grid-table").jqGrid({
		url: ctx+"/bussiness/mall/goods/query.json",
//		postData:{"name":"1212312"},
		colNames:['','id','名称','IP','时间','级别','类','描述', '信息',],
		colModel:[
		    {name:'myac',index:'', width:80, fixed:true,search:false, sortable:false, resize:false,
			//name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
				formatter:'actions', 
				formatoptions:{ 
					keys:true,
					delOptions:{recreateForm: true, beforeShowForm:function(e){
						beforeDel(e,"goods-grid-table",ctx+"/bussiness/mall/goods/delete.json");
					}},
					editformbutton:true, editOptions:{recreateForm: true,closeAfterEdit:true, beforeShowForm:function(e){
						beforeEdit(e,"goods-grid-table",ctx+"/bussiness/mall/goods/modify.json");
					}}
				}
			},
			{name:'id',index:'id', width:10,search:true, sorttype:"int", editable: false},
			{name:'goodsName',index:'goodsName',width:30,search:true, editable:true},
			{name:'goodsIp',index:'goodsIp', width:30,editable: true},
			{name:'goodsDate',index:'goodsDate',width:40,search:true, editable:true},
			{name:'goodsLevel',index:'goodsLevel',width:50,search:true, editable:true},
			{name:'goodsClass',index:'goodsClass',width:50,search:true, editable:true},
			{name:'goodsDesc',index:'goodsDesc', width:70, editable: true,edittype:"textarea"},
			{name:'goodsMessage',index:'goodsMessage', width:150, editable: true,edittype:"textarea"}
		], 
		pager : "#grid-pager",//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		
		editurl: ctx+"/bussiness/mall/goods/modify.json",//nothing is saved定义对form编辑时的url
		caption: "日志查询",//表格名称
	});

	//navButtons
	$("#grid-table").jqGrid('navGrid',"#grid-pager",
		{
			
		},
		{
			//edit record form
			closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeEdit(e,"goods-grid-table",ctx+"/bussiness/mall/goods/modify.json");
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				beforeAdd(e,"goods-grid-table",ctx+"/bussiness/mall/goods/add.json");
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeDel(e,"goods-grid-table",ctx+"/bussiness/mall/goods/delete.json");
			},
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				beforeSearch(e,"goods-grid-table","");
			},
			afterRedraw: function(e){
				searchFiltersStyle(e);
			}
			,
			multipleSearch: true,
	//		multipleGroup:true,
			showQuery: true
			
		},
		{
			//view record form
			recreateForm: true,
			beforeShowForm: function(e){
				beforeView(e,"goods-grid-table","");
			}
		}
	);
});
