/**
 *
 */
$().ready(function () {
	var str = "";
	var count = 0;
	var navHtml ="";
	loadFuncData('0',funcArr);
	$("#nav-list").append(navHtml);
	var aaa = "";
	function loadFuncData(parentId,jsonStr){
		var list = loadFuncByParentId(parentId,jsonStr);
		if(list.length>0){
			if(count!=0){
				$("#nav-li"+(count-1)+" a").attr("class","dropdown-toggle");
				navHtml = navHtml+"<ul class=submenu>";	
			}
			for(var i = 0;i<list.length;i++){
				var func = list[i];
				var subFunc = loadFuncByParentId(func.id,jsonStr);
				if(subFunc.length>0){
					if(parentId == "0"){
						navHtml = navHtml+"<li id=nav-li"+count+"><a  href=# class=dropdown-toggle><i class="+func.styleClass+"></i><span class=menu-text>"+func.name;
						navHtml = navHtml+"</span><b class='arrow icon-angle-down'></b></a>";
					}else{
						navHtml = navHtml+"<li id=nav-li"+count+"><a  href=# class=dropdown-toggle><i class=icon-double-angle-right></i><span class=menu-text>"+func.name;
						navHtml = navHtml+"</span><b class='arrow icon-angle-down'></b></a>";
					}
					
				}else{
					
					if(func.id == "97" || func.name =="控制台"){
						navHtml = navHtml+"<li id=nav-li"+count+" class=active><a  href="+func.url+"><i class="+func.styleClass+" style='margin-right:5px'></i><span class=menu-text>"+func.name;
						navHtml = navHtml+"</span></a>";
					}else{
						navHtml = navHtml+"<li id=nav-li"+count+"><a  href="+func.url+"><i class="+func.styleClass+" style='margin-right:5px'></i>"+func.name;
						navHtml = navHtml+"</a>";
					}
					
				}
				
				count++;
				var listsub = loadFuncData(func.id,jsonStr);
				navHtml = navHtml+"</li>";
			}
			if(count!=0){
				navHtml = navHtml+"</ul>";
			}
		}
	}
	
	function loadFuncCls(parentId,jsonStr){
		var list = loadFuncByParentId(parentId,jsonStr);
		if(list.length>0){
			if(count!=0){
				$("#nav-li"+(count-1)+" a").attr("class","dropdown-toggle");
			}
			for(var i = 0;i<list.length;i++){
				var func = list[i];
				count++;
				var listsub = loadFuncData(func.id,jsonStr);
			}
			if(count!=0){
			}
		}
	}
	
	function loadFuncByParentId(parentId,jsonStr){
		var arr = new Array();
		var num = 0;
		for(var i =0 ;i<jsonStr.length;i++){
			if(jsonStr[i].parentId==parentId){
				arr[num] = jsonStr[i];
				num++;
			}
		}
		return arr;
	}
	
//	function loadFuncByParentId(parentId,jsonStr){
//		var arr = "[";
//		for(var i =0 ;i<jsonStr.length;i++){
//			if(jsonStr[i].parentId==parentId){
//				arr = arr + "{'id':'"+jsonStr[i].id+"','name':'"+jsonStr[i].name+"','parentId':'"+jsonStr[i].parentId+"'},";
//			}
//		}
//		if(arr!="["){
//			arr = arr.substring(0,arr.length-1);
//		}else{
//			
//		}
//		arr = arr + "]";
//		var data = eval("(" + arr + ")");
//		return data;
//	}
});