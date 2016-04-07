$().ready(function () {
	var userDicTypes = getUserDicTypes();
	$("#dictionary-grid-table").jqGrid({
		url: ctx+"/business/dictionary/search.json",
//		postData:{"name":"1212312"},
		colNames:['','id','编码','名称','描述','父Id','类型','创建时间','状态',],
		colModel:[
		    {name:'myac',index:'', width:80, fixed:true,search:false, sortable:false, resize:false,
			//name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
				formatter:'actions', 
				formatoptions:{ 
					keys:true,
					delOptions:{recreateForm: true, beforeShowForm:function(e){
						beforeDel(e,"dictionary-grid-table",ctx+"/system/dictionary/delete.json");
					}},
					editformbutton:true, editOptions:{recreateForm: true,closeAfterEdit:true, beforeShowForm:function(e){
						beforeEdit(e,"dictionary-grid-table",ctx+"/system/dictionary/modify.json");
					}}
				}
			},
			{name:'id',index:'id', width:10,search:true, editable: false},
			{name:'code',index:'code',width:50,search:true, editable:true},
			{name:'name',index:'name', width:50,editable: true},
			{name:'description',index:'description',width:40,search:true, editable:true},
			{name:'parentId',index:'parentId',width:10,search:true, editable:true},
			{name:'typeId',index:'typeId',width:30,search:true, editable:true,edittype:"select",editoptions: {value:userDicTypes.join(";")},formatter:function(cellvalue, options, row){return getDicValue(cellvalue,userDicTypes)}},
			{name:'createDate',index:'createDate', width:70,search:true,editable: false,sorttype:"date",editrules:{required:true,date:true}},
			{name:'status',index:'status', width:30,search:true, editable: true,edittype:"select",editoptions: {value:"1:有效;0:无效"},formatter:function(cellvalue, options, row){return cellvalue==1?"有效":"无效"}},
		], 
		pager : "#dictionary-grid-pager",//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		
		editurl: ctx+"/business/dictionary/modify.json",//nothing is saved定义对form编辑时的url
		caption: "日志查询",//表格名称
	});

	//navButtons
	$("#dictionary-grid-table").jqGrid('navGrid',"#dictionary-grid-pager",
		{
			
		},
		{
			//edit record form
			closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeEdit(e,"dictionary-grid-table",ctx+"/business/dictionary/modify.json");
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				beforeAdd(e,"dictionary-grid-table",ctx+"/business/dictionary/add.json");
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeDel(e,"dictionary-grid-table",ctx+"/business/dictionary/delete.json");
			},
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				beforeSearch(e,"dictionary-grid-table","");
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
				beforeView(e,"dictionary-grid-table","");
			}
		}
	);
});
