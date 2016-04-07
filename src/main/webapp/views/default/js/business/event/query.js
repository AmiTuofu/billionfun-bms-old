$().ready(function(){
		/* initialize the external events
		-----------------------------------------------------------------*/
	
		$('#external-events div.external-event').each(function() {
	//		alert("111");
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
		});
		/* initialize the calendar
		-----------------------------------------------------------------*/
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var calendar = $('#calendar').fullCalendar({
			 buttonText: {
				prev: '<i class="icon-chevron-left"></i>',
				next: '<i class="icon-chevron-right"></i>',
				today:'返回今天',
				month:'月',
				week:'周',
				day:'日'
			},
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			events: function(start, end, callback) {
				var events =[];
				$.ajax({
		            type: "POST",
		            url: ctx+"/business/event/search.json",
		            data: {startDate: start, endDate: end},
		            beforeSend:function(XMLHttpRequest){
//		            	bootbox.alert("加载中...", function (result) {
		//
//		                });
		            },
		            success: function (data) {
		            	$.each(data.list, function(i, item) {
							events.push({
								id:$(this).attr('id'),
								name:$(this).attr('name'),
								categoryName:$(this).attr('categoryName'),
		                        title:"["+$(this).attr('categoryName')+"]"+$(this).attr('name'),
		                        start:$(this).attr('startDate'), // will be parsed
		                        end:$(this).attr('endDate'),
		                        repeats:$(this).attr('repeats'),
		                        remind:$(this).attr('remind'),
		                        repeatsEndDate:$(this).attr('repeatsEndDate'),
		                        place:$(this).attr('place'),
		                        className:$(this).attr('styleClass'),
		                        styleClass:$(this).attr('styleClass'),
		                        repeatsId:$(this).attr('repeatsId'),
		                        allDay:$(this).attr('allDay'),
		                        status:$(this).attr('status'),
		                        backgroundColor:$(this).attr('backgroundColor'),
		                        categoryId:$(this).attr('categoryId'),
		                    });
				        });
						callback(events);
						$(".fc-sat").css('backgroundColor','#b9dced');//这个是周六的TD
						$(".fc-sun").css('backgroundColor','#ddf0ed');//这个是周日的TD
						
	//					$(".fc-event").css('backgroundColor',backgroundColor);//这个是周日的TD
						
	                },
	                error: function (e) {
	                    alert("error");
	                }
		        });
				
			},
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				var $extraEventClass = $(this).attr('data-class');
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				// assign it the date that was reported
				copiedEventObject.start = date.Format("yyyy-MM-dd hh:mm:ss");
				copiedEventObject.end = date.Format("yyyy-MM-dd hh:mm:ss");
				copiedEventObject.repeatsEndDate = "";
				copiedEventObject.remind = "";
				copiedEventObject.place = "";
				copiedEventObject.backgroundColor = $(this).getHexBackgroundColor();
				copiedEventObject.allDay = allDay;
		//		copiedEventObject.styleClass = $extraEventClass;
				copiedEventObject.styleClass = "";
				var id = addEvent(copiedEventObject);
				
				calendar.fullCalendar('refetchEvents');
				copiedEventObject.id = id;
//				if($extraEventClass){
//					copiedEventObject['className'] = $extraEventClass;
//				}
//				// render the event on the calendar
//				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
//				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
//				// is the "remove after drop" checkbox checked?
//				if ($('#drop-remove').is(':checked')) {
//					// if so, remove the element from the "Draggable Events" list
//					$(this).remove();
//				}
				
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var form = $(getFormHtml(null));
				var div = bootbox.dialog({
					title:"新建事件",
					message: form,
					buttons: {
						"ok" : {
							"label" : "<i class='icon-ok'></i> 保存",
							"className" : "btn btn-sm btn-success",
							"callback": function() {
								var calEvent = {};
								calEvent.name = form.find("input[name=name]").val();
								calEvent.start = form.find("input[name=startDate]").val();
								calEvent.end = form.find("input[name=endDate]").val();
								calEvent.repeats = $("select[name=repeats]").val();
								calEvent.repeatsEndDate = $("input[name=repeatsEndDate]").val();
								calEvent.remind = $("select[name=remind]").val();
								calEvent.place = $("input[name=place]").val();
								calEvent.allDay = allDay;
								calEvent.status = $("select[name=status]").val();
								calEvent.backgroundColor = $("select[name=backgroundColor]").val();
								calEvent.categoryId = $("select[name=categoryId]").val();
								addEvent(calEvent);
								calendar.fullCalendar('refetchEvents');
							}
						} ,
						"close" : {
							"label" : "<i class='icon-remove'></i> 关闭",
							"className" : "btn-sm"
						} 
					}
				});
				form.find("input[name=startDate]").val(start.Format("yyyy-MM-dd hh:mm:ss"));
				form.find("input[name=endDate]").val(end.Format("yyyy-MM-dd hh:mm:ss"));
				form.find("label[name=until-time]").hide();
				form.find("input[name=repeatsEndDate]").hide();
				$('.date-time-picker').datetimepicker({format: 'yyyy-mm-dd hh:ii:ss'}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
//				$('input[name=startDate]').datepicker({autoclose:true,altFormat: 'yy-mm-dd 10:30:00' }).next().on(ace.click_event, function(){
//					$(this).prev().focus();
//				});
//				$('input[name=endDate]').datepicker({autoclose:true,altFormat: 'yy-mm-dd 19:30:00' }).next().on(ace.click_event, function(){
//					$(this).prev().focus();
//				});
//				$('input[name=repeatsEndDate]').datepicker({autoclose:true,altFormat: 'yy-mm-dd 00:00:00' }).next().on(ace.click_event, function(){
//					$(this).prev().focus();
//				});
				$('select[name=backgroundColor]').ace_colorpicker();
				$("select[name=repeats]").change(function(){
					 if(!empty($(this).val())){
						 form.find("label[name=until-time]").show();
						 form.find("input[name=repeatsEndDate]").show();
						 form.find("input[name=repeatsEndDate]").val(end.Format("yyyy-MM-dd hh:mm:ss"));
					 }else{
						 form.find("label[name=until-time]").hide();
						 form.find("input[name=repeatsEndDate]").hide();
					 }
				});
				calendar.fullCalendar('unselect');
			},
			eventClick: function(calEvent, jsEvent, view) {
				var form = $(getFormHtml(calEvent));
				var div = bootbox.dialog({
					title:"编辑事件",
					message: form,
					buttons: {
						"ok" : {
							"label" : "<i class='icon-ok'></i> 保存",
							"className" : "btn btn-sm btn-success",
							"callback": function() {
								calEvent.name = form.find("input[name=name]").val();
								calEvent.start = form.find("input[name=startDate]").val();
								calEvent.end = form.find("input[name=endDate]").val();
								calEvent.repeats = $("select[name=repeats]").val();
								calEvent.repeatsEndDate = $("input[name=repeatsEndDate]").val();
								calEvent.remind = $("select[name=remind]").val();
								calEvent.place = $("input[name=place]").val();
								calEvent.status = $("select[name=status]").val();
								calEvent.backgroundColor = $("select[name=backgroundColor]").val();
								calEvent.categoryId = $("select[name=categoryId]").val();
								
								if(!empty(calEvent.repeatsId)){
									var confirm_div = bootbox.dialog({
		//								title:"编辑事件",
										message: "这是一个重复事件，你想?",
										buttons: {
											"okAll" : {
												"label" : "<i class='icon-ok'></i> 修改当前和后续所有重复事件",
												"className" : "btn btn-sm btn-success",
												"callback": function() {
													modifyEvent(calEvent);
													confirm_div.modal("hide");
													calendar.fullCalendar('refetchEvents');
												}
											} ,
											"ok" : {
												"label" : "<i class='icon-ok'></i> 只修改当前事件",
												"className" : "btn btn-sm btn-success",
												"callback": function() {
													calEvent.repeatsId = "";
													modifyEvent(calEvent);
													confirm_div.modal("hide");
													calendar.fullCalendar('refetchEvents');
												}
											}
										}
									});
								}else{
									calEvent.repeatsId = "";
									modifyEvent(calEvent);
									calendar.fullCalendar('refetchEvents');
								}
								
								
								
//								modifyEvent(calEvent);
							//	calendar.fullCalendar('updateEvent', calEvent);
								div.modal("hide");
								
							}
						} ,
						"delete" : {
							"label" : "<i class='icon-trash'></i> 删除事件",
							"className" : "btn-sm btn-danger",
							"callback": function() {
								if(!empty(calEvent.repeatsId)){
									var confirm_div = bootbox.dialog({
		//								title:"编辑事件",
										message: "这是一个重复事件，你想?",
										buttons: {
											"okAll" : {
												"label" : "<i class='icon-trash'></i> 删除当前和后续所有重复事件",
												"className" : "btn-sm btn-danger",
												"callback": function() {
													delEvent(calEvent);
													calendar.fullCalendar('refetchEvents');
//													calendar.fullCalendar('removeEvents' , function(ev){
//														return (ev._id == calEvent._id);
//													})
												}
											} ,
											"ok" : {
												"label" : "<i class='icon-trash'></i> 只删除当前事件",
												"className" : "btn-sm btn-danger",
												"callback": function() {
													calEvent.repeatsId = "";
													delEvent(calEvent);
													calendar.fullCalendar('refetchEvents');
//													calendar.fullCalendar('removeEvents' , function(ev){
//														return (ev._id == calEvent._id);
//													})
												}
											}
										}
									});
								}else{
									calEvent.repeatsId = "";
									delEvent(calEvent);
									calendar.fullCalendar('refetchEvents');
								}
								
							}
						} ,
						"close" : {
							"label" : "<i class='icon-remove'></i> 关闭",
							"className" : "btn-sm"
						} 
					}
				});
				form.find("input[name=name]").val(calEvent.name);
				if(!empty(calEvent.start)){
					form.find("input[name=startDate]").val(calEvent.start.Format("yyyy-MM-dd hh:mm:ss"));
				}
				
				if(empty(calEvent.end)){
					calEvent.end = calEvent.start;
				}
				if(!empty(calEvent.end)){
					form.find("input[name=endDate]").val(calEvent.end.Format("yyyy-MM-dd hh:mm:ss"));
				}
				
				$("select[name=repeats]").val(calEvent.repeats);
				$("select[name=repeats]").attr("disabled","disabled");
				if(!empty(calEvent.repeatsEndDate)){
					$("input[name=repeatsEndDate]").val(calEvent.repeatsEndDate);
				}
				$("input[name=repeatsEndDate]").attr("disabled","disabled");
				$("select[name=remind]").val(calEvent.remind);
				$("input[name=place]").val(calEvent.place);
				$("select[name=status]").val(calEvent.status);
				$("select[name=backgroundColor]").val(calEvent.backgroundColor);
				$("select[name=categoryId]").val(calEvent.categoryId);
				$('.date-time-picker').datetimepicker({format: 'yyyy-mm-dd hh:ii:ss'}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				$('select[name=backgroundColor]').ace_colorpicker();
				//console.log(calEvent.id);
				//console.log(jsEvent);
				//console.log(view);
				// change the border color just for fun
				//$(this).css('border-color', 'red');
			},
			eventDragStart:function(calEvent, jsEvent, ui, view){
				
			},
			eventDragStop:function(calEvent, jsEvent, ui, view){
				
			},
			eventDrop: function(calEvent, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
				if(empty(calEvent.end)){
					calEvent.end = calEvent.start;
				}
				if(!empty(calEvent.repeatsEndDate)){
					var repeatsEndDate = new Date(calEvent.repeatsEndDate.replace(/-/g,   "/"));
					repeatsEndDate.setDate(repeatsEndDate.getDate() + dayDelta);
					repeatsEndDate.setMinutes(repeatsEndDate.getMinutes() + minuteDelta);
					calEvent.repeatsEndDate = repeatsEndDate.Format("yyyy-MM-dd hh:mm:ss");
				}
				calEvent.allDay = allDay;
				calEvent.start = calEvent.start.Format("yyyy-MM-dd hh:mm:ss");
		//		calEvent.name = calEvent.title;
				calEvent.end = calEvent.end.Format("yyyy-MM-dd hh:mm:ss");
				
				if(!empty(calEvent.repeatsId)){
					var confirm_div = bootbox.dialog({
//								title:"编辑事件",
						message: "这是一个重复事件，你想?",
						buttons: {
							"okAll" : {
								"label" : "<i class='icon-ok'></i> 修改当前和后续所有重复事件",
								"className" : "btn btn-sm btn-success",
								"callback": function() {
									modifyEvent(calEvent);
									confirm_div.modal("hide");
									calendar.fullCalendar('refetchEvents');
								}
							} ,
							"ok" : {
								"label" : "<i class='icon-ok'></i> 只修改当前事件",
								"className" : "btn btn-sm btn-success",
								"callback": function() {
									calEvent.repeatsId = "";
									modifyEvent(calEvent);
									confirm_div.modal("hide");
									calendar.fullCalendar('refetchEvents');
								}
							}
						}
					});
				}else{
					calEvent.repeatsId = "";
					modifyEvent(calEvent);
					calendar.fullCalendar('refetchEvents');
				}

				
			},
			eventResizeStart:function(calEvent, jsEvent, ui, view){
				
			},
			eventResizeStop:function(calEvent, jsEvent, ui, view){
				
			},
			eventResize: function(calEvent, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view){
				if(empty(calEvent.end)){
					calEvent.end = calEvent.start;
				}
				if(!empty(calEvent.repeatsEndDate)){
					var repeatsEndDate = new Date(calEvent.repeatsEndDate.replace(/-/g,   "/"));
					repeatsEndDate.setDate(repeatsEndDate.getDate() + dayDelta);
					repeatsEndDate.setMinutes(repeatsEndDate.getMinutes() + minuteDelta);
					calEvent.repeatsEndDate = repeatsEndDate.Format("yyyy-MM-dd hh:mm:ss");
				}
//				calEvent.allDay = allDay;
				calEvent.start = calEvent.start.Format("yyyy-MM-dd hh:mm:ss");
	//			calEvent.name = calEvent.title;
				calEvent.end = calEvent.end.Format("yyyy-MM-dd hh:mm:ss");
				if(!empty(calEvent.repeatsId)){
					var confirm_div = bootbox.dialog({
//								title:"编辑事件",
						message: "这是一个重复事件，你想?",
						buttons: {
							"okAll" : {
								"label" : "<i class='icon-ok'></i> 修改当前和后续所有重复事件",
								"className" : "btn btn-sm btn-success",
								"callback": function() {
									modifyEvent(calEvent);
									confirm_div.modal("hide");
									calendar.fullCalendar('refetchEvents');
								}
							} ,
							"ok" : {
								"label" : "<i class='icon-ok'></i> 只修改当前事件",
								"className" : "btn btn-sm btn-success",
								"callback": function() {
									calEvent.repeatsId = "";
									modifyEvent(calEvent);
									confirm_div.modal("hide");
									calendar.fullCalendar('refetchEvents');
								}
							}
						}
					});
				}else{
					calEvent.repeatsId = "";
					modifyEvent(calEvent);
					calendar.fullCalendar('refetchEvents');
				}
			}
		});
		function addEvent(calEvent){
			
			var id = "";
			if(calEvent.end==""||calEvent.end==null){
				var date = new Date();
				calEvent.end = date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate()+" 23:59:59";
			}
			var params = {
					"startDate":new   Date(calEvent.start.replace(/-/g,   "/")),
					"endDate":new   Date(calEvent.end.replace(/-/g,   "/")),
					"name":calEvent.name,
					"repeats":calEvent.repeats,
					"remind":calEvent.remind,
					"place":calEvent.place,
					"styleClass":calEvent.styleClass,
					"allDay" : calEvent.allDay,
					"backgroundColor" : calEvent.backgroundColor,
					"categoryId" : calEvent.categoryId,
			};
			if(!empty(calEvent.repeatsEndDate)){
				params.repeatsEndDate = new Date(calEvent.repeatsEndDate.replace(/-/g,   "/"));
			}
			$.ajax({
		            type: "POST",
		            url: ctx + "/business/event/add.json",
		            data: params,
		            async: false,
		            beforeSend:function(XMLHttpRequest){
//		            	bootbox.alert("加载中...", function (result) {
		//
//		                });
		            },
		            success: function (data) {
		            		id = data.id
		                },
		            error: function (e) {
		                    alert("error");
		                }
		        });
			return id;
		}
		function modifyEvent(calEvent){
			if(calEvent.end==""||calEvent.end==null){
				var date = calEvent.start;
				calEvent.end = new Date(date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate()+" 23:59:59");
			}
			if(calEvent.status=="2"){
				calEvent.backgroundColor="#c2c2c2";
			}
			var params = {
					"id":calEvent.id,
					"startDate":new Date(calEvent.start.replace(/-/g,   "/")),
					"endDate":new Date(calEvent.end.replace(/-/g,   "/")),
					"name":calEvent.name,
					"repeats":calEvent.repeats,
					"remind":calEvent.remind,
					"place":calEvent.place,
					"styleClass":calEvent.styleClass,
					"repeatsId":calEvent.repeatsId,
					"allDay" : calEvent.allDay,
					"status" : calEvent.status,
					"backgroundColor" : calEvent.backgroundColor,
					"categoryId" : calEvent.categoryId,
			};
			if(!empty(calEvent.repeatsEndDate)){
				params.repeatsEndDate = new Date(calEvent.repeatsEndDate.replace(/-/g,   "/"));
			}
			$.ajax({
		            type: "POST",
		            url: ctx + "/business/event/modify.json",
		            async: false,
		            data: params,
		            beforeSend:function(XMLHttpRequest){
//		            	bootbox.alert("加载中...", function (result) {
		//
//		                });
		            },
		            success: function (data) {
		            	
		                },
		            error: function (e) {
		                    alert("error");
		                }
		        });
		}
		function delEvent(calEvent){
			var params = {
					"id":calEvent.id,
					"startDate":calEvent.start,
					"repeatsId":calEvent.repeatsId,
			};
			$.ajax({
		            type: "POST",
		            url: ctx + "/business/event/delete.json",
		            data: params,
		            async: false,
		            beforeSend:function(XMLHttpRequest){
//		            	bootbox.alert("加载中...", function (result) {
		//
//		                });
		            },
		            success: function (data) {
		            	
		                },
		            error: function (e) {
		                    alert("error");
		                }
		        });
		}
		function getFormHtml(calEvent){
			var eventCate = getEventCate();
			var bootbox_form_html = "<form class=\"form-horizontal\" role=\"form\">";
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 名称: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-9\"><input type=\"text\" id=\"form-field-1\" name=\"name\" placeholder=\"\" class=\"col-xs-11 col-sm-8\"></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 类别: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-4\"><select class=\"form-control\" name=\"categoryId\">";
			for(var i = 0;i<eventCate.length;i++){
				bootbox_form_html = bootbox_form_html + "<option value='"+eventCate[i].id+"'>"+eventCate[i].name+"</option>";
			}
			bootbox_form_html = bootbox_form_html + "</select></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 开始: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-9\"><input type=\"text\" id=\"form-field-1\" name=\"startDate\" placeholder=\"\" class=\"col-xs-10 col-sm-5 date-time-picker\" data-date-format=\"yyyy-mm-dd hh:ii:ss\"></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 结束: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-9\"><input type=\"text\" id=\"form-field-1\" name=\"endDate\" placeholder=\"\" class=\"col-xs-10 col-sm-5 date-time-picker\" data-date-format=\"yyyy-mm-dd hh:ii:ss\"></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 重复: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-4\"><select class=\"form-control\" name=\"repeats\">";
			bootbox_form_html = bootbox_form_html + "<option value=>不重复</option><option value=day>每日重复</option><option value=week>每周重复</option>";
			bootbox_form_html = bootbox_form_html + "<option value=month>每月重复</option><option value=year>每年重复</option>";
			bootbox_form_html = bootbox_form_html + "</select></div>"
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-5\"><label class=\"col-xs-4 col-sm-4\" style=\"padding-top:4px;margin-bottom:4px\" name=\"until-time\">直到:</label><input type=\"text\" id=\"form-field-1\" name=\"repeatsEndDate\" placeholder=\"\" class=\"col-xs-8 col-sm-8 date-time-picker\" data-date-format=\"yyyy-mm-dd hh:ii:ss\"></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 提醒: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-4\"><select class=\"form-control\" name=\"remind\">";
			bootbox_form_html = bootbox_form_html + "<option value=0>不提醒</option><option value=1>提醒一小时</option><option value=24>提前一天</option>";
			bootbox_form_html = bootbox_form_html + "<option value=168>提前一周</option>";
			bootbox_form_html = bootbox_form_html + "</select></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 背景颜色: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-4\">";
			bootbox_form_html = bootbox_form_html + getColorPicker("backgroundColor","backgroundColor");
			bootbox_form_html = bootbox_form_html + "</div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 地点: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-9\"><input type=\"text\" id=\"form-field-1\" placeholder=\"\" name=\"place\" class=\"col-xs-10 col-sm-5\"></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "<div class=\"form-group\"><label class=\"col-sm-2 control-label no-padding-right\" for=\"form-field-1\"> 状态: </label>";
			bootbox_form_html = bootbox_form_html + "<div class=\"col-sm-4\"><select class=\"form-control\" name=\"status\">";
			bootbox_form_html = bootbox_form_html + "<option value=1>待完成</option><option value=2>已完成</option>";
			bootbox_form_html = bootbox_form_html + "</select></div></div>";
			bootbox_form_html = bootbox_form_html + "<div class=\"space-4\"></div>";
			
			bootbox_form_html = bootbox_form_html + "</form>";
			return bootbox_form_html;
		}
		function getEventCate(){
			var eventCate = [];
			$.ajax({
		        type: "POST",
		        url: ctx + "/business/dictionary/getall.json",
		        data: {"typeId":1},
		        async: false,
		        beforeSend:function(XMLHttpRequest){
		
		        },
		        success: function (data) {
		        	eventCate = data.list;
		        },
		        error: function (e) {
		        	
		        }
		    });
			return eventCate;
		}
});