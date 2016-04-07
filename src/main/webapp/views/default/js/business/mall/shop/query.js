$().ready(function () {
	$("#shop-grid-table").jqGrid({
		url: ctx+"/business/mall/shop/query.json",
//		postData:{"name":"1212312"},
		colNames:['','id','店铺名称','省','市','区','姓名','手机','申请时间','状态','用户ID'],
		colModel:[
		    {name:'act',index:'act', width:80, fixed:true,search:false, sortable:false, resize:false,
			
			},
			{name:'id',index:'id', width:10,search:true, sorttype:"int", editable: false},
			{name:'name',index:'name',width:30,search:true, editable:true},
			{name:'province',index:'province', width:30,editable: true},
			{name:'city',index:'city',width:40,search:true, editable:true},
			{name:'district',index:'district',width:50,search:true, editable:true},
			{name:'realName',index:'realName',width:50,search:true, editable:true},
			{name:'mobile',index:'mobile', width:70, editable: true},
			{name:'createDate',index:'createDate', width:150, editable: false},
			{name:'status',index:'status', width:30,search:true, editable: true,edittype:"select",editoptions: {value:"1:有效;0:无效"},formatter:function(cellvalue, options, row){return cellvalue==1?"有效":"无效"}},
			{name:'userId',index:'userId', width:150, editable: false},
		], 
		pager : "#shop-grid-pager",//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		gridComplete: function(){ //列表生成后,给某一列绑定操作 例如删除操作
	            var ids = $("#shop-grid-table").jqGrid('getDataIDs');
	            for(var i=0;i < ids.length;i++){
	            	var review = "<a href='#' onclick=\"review('shop-grid-table','"+ctx+"/business/mall/shop/review.json','"+ids[i]+"');\">审核通过</a>"; 
	                $("#shop-grid-table").jqGrid('setRowData',ids[i],{act:review});
	            }
	    },
		editurl: ctx+"/business/mall/shop/modify.json",//nothing is saved定义对form编辑时的url
		caption: "店铺审核",//表格名称
	});

	//navButtons
	$("#shop-grid-table").jqGrid('navGrid',"#shop-grid-pager",
		{
			
		},
		{
			//edit record form
			closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeEdit(e,"shop-grid-table",ctx+"/business/mall/shop/modify.json");
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				beforeAdd(e,"shop-grid-table",ctx+"/business/mall/shop/add.json");
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				beforeDel(e,"shop-grid-table",ctx+"/business/mall/shop/delete.json");
			},
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				beforeSearch(e,"shop-grid-table","");
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
				beforeView(e,"shop-grid-table","");
			}
		}
	);
});
