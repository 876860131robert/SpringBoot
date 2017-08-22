//@ sourceURL=materielsDataList.js
//初始化
$(function() {
	query();
	//新增、编辑弹出框挪动
	sy.mousedivdown('banner','div_07');
	//过滤弹出框挪动
	sy.mousedivdown('banner1','div_06');
	//上传弹出框挪动
	sy.mousedivdown('banner2','div_05');
})
//查询方法
function query() {
	$("#pageNumber").val("1");  
	var brand = sy.encodeBase64(encodeURI($("#brand_query").val()));
	var materielstype = sy.encodeBase64(encodeURI($("#type_query").val()));
	var pcategory=sy.encodeBase64($("#pcategory").val());
	findMaterielsDataInfo(1,brand,materielstype,pcategory);
}

//点击首页、下一页、上一页，最后一页触发的方法
function goToPage(tag) {
	var pageNum = 1;
	var brand = sy.encodeBase64($("#brand_query").val());
	var materielstype = sy.encodeBase64($("#type_query").val());
	var pcategory=sy.encodeBase64($("#pcategory").val());
	if (tag == 1) {
		findMaterielsDataInfo(1,brand,materielstype,pcategory);
	} else if (tag == 2) {
		var page = Number($("#pageNumber").val()) + 1;
		findMaterielsDataInfo(page,brand,materielstype,pcategory);
	} else if (tag == 3) {
		var page = Number($("#pageNumber").val()) - 1;
		findMaterielsDataInfo(page,brand,materielstype,pcategory);
	} else {
		var page = $("#pageTimes").val()
		findMaterielsDataInfo(page,brand,materielstype,pcategory);
	}
}

//与后台交互的查询方法
function findMaterielsDataInfo(page,brand,materielstype,pcategory) {
	sy.clrCarrier("materielsData_list");
	var url = sy.readPropertise("materielsData_list_url");
	var temp;
	$.ajax({
				type : 'get',
				url : url,
				data : {
					page : page,
					brand : brand,
					materielstype : materielstype,
					pcategory:pcategory
				},
				async : false,
				dataType : "json",
				success : function(obj) {
					var tempDiv = "";
					var bgcolor = "";
					var brand = "";
					var materielstype = "";
					for (var i = 0; i < obj.rows.length; i++) {
						if (i % 2 == 0) {
							tempDiv += "<tr style='background:#f8f8f8;'><td style='width:3%;'>";
						} else {
							tempDiv += "<tr><td style='width:3%;'>";
						}
						tempDiv += "<input type='checkbox'  id='"
								+ obj.rows[i].id
								+ "' name='checkChild' class='checkboxClass'></td>";
						var category= obj.rows[i].category;
						if(category==undefined){
							category="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ category + "</td>"
						var brand= obj.rows[i].brand;
						if(brand==undefined){
							brand="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ brand + "</td>"
						var materielstype= obj.rows[i].materielstype;
						if(materielstype==undefined){
							materielstype="-";
						}		
						tempDiv += "<td style='width:7%;'>"
								+ materielstype + "</td>"
						var capacity= obj.rows[i].capacity;
						if(capacity==undefined){
							capacity="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ capacity + "</td>"
						var ratedvoltage= obj.rows[i].ratedvoltage;
						if(ratedvoltage==undefined){
							ratedvoltage="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ ratedvoltage + "</td>"
						var ratedcurrent= obj.rows[i].ratedcurrent;
						if(ratedcurrent==undefined){
							ratedcurrent="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ratedcurrent + "</td>"
						var ratedpower= obj.rows[i].ratedpower;
						if(ratedpower==undefined){
							ratedpower="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ ratedpower + "</td>"
						var voltageform= obj.rows[i].voltageform;
						if(voltageform==undefined){
							voltageform="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ voltageform + "</td>"
						var installationmode= obj.rows[i].installationmode;
						if(installationmode==undefined){
							installationmode="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ installationmode + "</td>"
						var dimension= obj.rows[i].dimension;
						if(dimension==undefined){
							dimension="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ dimension + "</td>"
						var unit= obj.rows[i].unit;
						if(unit==undefined){
							unit="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ unit + "</td>"	
						var equipmentprice= obj.rows[i].equipmentprice;
						if(equipmentprice==undefined){
							equipmentprice="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ equipmentprice + "</td>"	
						var servicecost= obj.rows[i].servicecost;
						if(servicecost==undefined){
							servicecost="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+ servicecost + "</td>"
						var remarks= obj.rows[i].remarks;
						if(remarks==undefined){
							remarks="-";
						}
						tempDiv += "<td style='width:7%;'>"
								+  remarks + "</td>"
						tempDiv += "</tr>"
					}
					$("#pageNumber").val(obj.currentPage);
					$("#pageTimes").val(obj.pageTimes);
					$("#span1").html(obj.currentPage);
					$("#span2").html(obj.pageTimes);
					$("#span3").html(obj.countNum);
					if (obj.pageTimes == 1 || obj.pageTimes == 0) {
						$("#nextButton").attr("disabled", true);
						$("#preButton").attr("disabled", true);
						$("#nextButton").css({background:"#e6e6e6"})
						$("#preButton").css({background:"#e6e6e6"})
					} else {
						if (obj.currentPage == obj.pageTimes) {
							$("#nextButton").attr("disabled", true);
							$("#nextButton").css({background:"#e6e6e6"})
							$("#preButton").attr("disabled", false);
							$("#preButton").css({background:"#2cbbe3"})
						} else if (obj.currentPage == 1) {
							$("#preButton").attr("disabled", true);
							$("#preButton").css({background:"#e6e6e6"})
							$("#nextButton").attr("disabled", false);
							$("#nextButton").css({background:"#2cbbe3"})
						} else {
							$("#preButton").attr("disabled", false);
							$("#nextButton").attr("disabled", false);
							$("#preButton").css({background:"#2cbbe3"})
							$("#nextButton").css({background:"#2cbbe3"})
						}
					}
					$("#materielsData_list").append(tempDiv);
				}
			});
}
//批量删除dialog中的全选/全不选
function checkAllList(obj){
        $("input[name='checkChild']").prop("checked", $(obj).prop("checked"));
}
//批量删除物料数据
function batchDel() {
	sy.mousedivdown('cloud_widget_dialog_hh','cloud-widget-dialog');
	var ids = [];
	$($("input[name='checkChild']:checked")).each(function() {
		var id = $(this).attr("id"); // id这个是你要在列表中取的单个id
		ids.push(id); // 然后把单个id循环放到ids的数组中
	});
	if(ids.length==0){
		sy.com_alert("请至少选择一行数据!");
		return false;
	}
	$( "#dialog-form-confirm" ).show();
	  //  添加并显示遮罩层 
    sy.addshade("selectBom")
}
//confirm 关闭确认删除框
function noconfirmDel(){
	$( "#dialog-form-confirm" ).hide();
	$("#bg").remove();
}
//confirm 确认删除
function confirmDel(){
	var ids = [];
	$($("input[name='checkChild']:checked")).each(function() {
		var id = $(this).attr("id"); // id这个是你要在列表中取的单个id
		ids.push(id); // 然后把单个id循环放到ids的数组中
	});
	var idBase=sy.encodeBase64(encodeURI(ids));//对数组先进行encodeURI然后进行加密
	var materielsDataDelUrl = sy.readPropertise("materielsData_del_url");
	var url = materielsDataDelUrl + '/' + idBase;
	var pcategory=sy.encodeBase64($("#pcategory").val());
	$.ajax({
		type : 'post',
		url : url,
		dataType : "json",
		data : {
			pcategory:pcategory
		},
		success : function(data) {
			$("#bg").remove();
			if (data.result == "1") {
				sy.com_alert("删除成功!","complete");
				$("#pcategory").val(data.pcategory);
				query();
				$("#allCheck").removeAttr("checked");
			} else {
				sy.com_alert("删除失败!");
			}
			$( "#dialog-form-confirm" ).hide();
		}
	});
}
//弹出新增框
function batchAdd(){
	$( "#add_data" ).show();
	getMaterielsList();
	  //  添加并显示遮罩层 
	 sy.addshade("selectBom");
}
//关闭新增框
function closeAddMateriels(){
	$( "#add_data" ).hide();
	$("#bg").remove();
	$('#materialsForm')[0].reset();//form表单清空
}
//点击新增确认
function confirmOk(){
	if(sy.validateRequiredFields("materialsForm")){
		if($('#materialsForm').valid()){
		var materielsId = '';
		$($("input[name='checkChild']:checked")).each(function() {
			materielsId= sy.encodeBase64($(this).attr("id"));
		});
		var pcategory=sy.encodeBase64($("#pcategory").val());
		var addData = sy.encodeBase64(encodeURI($('#materialsForm').serializeJson()));
		var materialsUrl = sy.readPropertise("materielsData_save_url");
		var url = materialsUrl+"?jsonStr="+addData;
		var formObj = document.getElementById("materialsForm");
		formObj.action =url;
		}else{
			sy.com_alert("请检查输入项填写是否正确!");
			return false;
		}
	}else{
		sy.com_alert("必填项不能为空!");
		return false;
	}
	$.ajax({
		type : 'post',
		url : url,
		data : {
			pcategory:pcategory,
			materielsId:materielsId
		},
		dataType : "json",
		success : function(data) {
			$("#bg").remove();
			if (data.result == "1") {
				$( "#add_data" ).hide();
				query();
				sy.com_alert(data.msg,"complete");
				$('#materialsForm')[0].reset();//form表单清空
				$("#pcategory").val(data.pcategory);
			} else{
				sy.com_alert(data.msg);
			}

		}
	});
	
}


//新增物料数据获取物料类别下拉框
function getMaterielsList() {
	var pcategory=sy.encodeBase64($("#pcategory").val());
	var url = sy.readPropertise("materielsData_getAll_url");
	$.ajax({
		type : 'post',
		url : url,
		dataType : "json",
		data : {
			pcategory:pcategory
		},
		success : function(json) {
			$("#category").empty();
			for(var i=0;i<json.length;i++){
				$("#category").append("<option value='"+json[i].valuecolumn+"'>"+json[i].valuecolumn+"</option>");
			}
		}
	});
}
//编辑物料数据获取物料类别下拉框的选中项
function getMaterielSelect(pcategoryold,category) {
	var pcategory=sy.encodeBase64(pcategoryold);
	var url = sy.readPropertise("materielsData_getAll_url");
	var flag=false;
	$.ajax({
		type : 'post',
		url : url,
		dataType : "json",
		data : {
			pcategory:pcategory
		},
		success : function(json) {
			$("#category").empty();
			for(var i=0;i<json.length;i++){
				$("#category").append("<option value='"+json[i].valuecolumn+"'>"+json[i].valuecolumn+"</option>");
				flag=true;
			}
			if(flag){
				var select=document.getElementById("category");
				var count=select.options.length;
				for(var i=0 ;i<count;i++){
					if(select.options[i].value==category){
						select.selectedIndex=i;
						break;
					}
				}
			}
		}
	});
}
//弹出编辑框
function batchEdit(){
	var materielsId = '';
	$($("input[name='checkChild']:checked")).each(function() {
		materielsId= sy.encodeBase64($(this).attr("id"));
	});
	if($("input[name='checkChild']:checked").length!=1){
		sy.com_alert("请选择一行数据!");
		return false;
	}
	$( "#add_data" ).show();
	getEditInfo(materielsId) ;
	  //  添加并显示遮罩层 
	 sy.addshade("selectBom");
    
}

//根据id查询物料数据回填到编辑界面
function getEditInfo(materielsId) {
	var url = sy.readPropertise("materielsData_findById_url");

	$.ajax({
		type : 'post',
		url : url,
		dataType : "json",
		data : {
			materielsId : materielsId
		},
		success : function(data) {
			if (data.msg == "1") {
				getMaterielSelect(data.result.pcategory,data.result.category);
				$("#brand").val(data.result.brand);
				$("#type").val(data.result.materielstype);
				$("#capacity").val(data.result.capacity);
				$("#ratedVoltage").val(data.result.ratedvoltage);
				$("#ratedCurrent").val(data.result.ratedcurrent);
				$("#ratedPower").val(data.result.ratedpower);
				$("#voltageForm").val(data.result.voltageform);
				$("#installationMode").val(data.result.installationmode);
				$("#dimension").val(data.result.dimension);
				$("#unit").val(data.result.unit);
				$("#equipmentPrice").val(data.result.equipmentprice);
				$("#serviceCost").val(data.result.servicecost);
				$("#remarks").val(data.result.remarks);
			} else {
				sy.com_alert('获取信息失败!');
			}
		}

	});
}

//选择过滤条件
function confirmFilter(){
	query();
	$( "#filter_data" ).hide();
	$("#bg").remove();
	$("input[name='brand_query']").val("");//清空文本框内容；
	$("input[name='type_query']").val("");//清空文本框内容；
}

//弹出过滤条件框
function filterAdd(){
	$( "#filter_data" ).show();
	  //  添加并显示遮罩层 
	 sy.addshade("selectBom");
}

//关闭过滤条件框
function closeFilter(){
	$( "#filter_data" ).hide();
	$("#bg").remove();
	$("input[name='brand_query']").val("");//清空文本框内容；
	$("input[name='type_query']").val("");//清空文本框内容；
}

//关闭alert弹窗口
function closeAlert(msg){
	$( "#dialog-form-"+msg).hide();
	if(msg=='confirm'){
		$("#bg").remove();
	}
}

//批量添加物料数据
function uploadData(){
		$( "#upload_data" ).show();
    	$("#textField").val("");
		  //  添加并显示遮罩层 
	    sy.addshade("selectBom");
}
//点击上传数据
function uploadOk(){
	if($("#fileField").val()==""){
		sy.com_alert("请选择需要上传的附件！");
		return false
	}
	
	//sy.com_alert("正在上传请稍后！",'notimeout');
	var pcategory=$("#pcategory").val();
	var url = sy.readPropertise("materielsData_delByCategory_url")+"/"+pcategory;
	$.ajaxFileUpload({  
            url:url,
            type:'post',
            secureuri:false,  //是否启用安全提交，默认false
            fileElementId:'fileField',//file标签的id 
            dataType: 'JSON',//返回数据的类型  
            success: function (data,status) { 
            	//$("#bg").remove();
    			if (status == "success") {
        			$( "#upload_data" ).hide();
        			query();
    				$("#bg").remove();
    				sy.com_alert("上传完成","complete");
//    				sy.com_alert(json.msg,"complete");
    			} else{
    				sy.com_alert(json.msg);
    			}
            },  
            error: function (data, status, e) {  
            }  
        });
	
}


//关闭alert弹窗口
function closeUploadMateriels(){
	$("#upload_data").hide();
		$("#bg").remove();
}

//批量添加人工采集数据
function uploadData1(){
		$( "#upload_data1" ).show();
    	$("#textField1").val("");
		  //  添加并显示遮罩层 
	    sy.addshade("selectBom");
}
//点击上传数据
function uploadExplorationData(){
	if($("#fileField1").val()==""){
		sy.com_alert("请选择需要上传的附件！");
		return false
	}
	//sy.com_alert("正在上传请稍后！",'notimeout');
	var url = sy.readPropertise("explorationData_upload_url");
	$.ajaxFileUpload({  
            url:url,
            type:'post',
            secureuri:false,  //是否启用安全提交，默认false
            fileElementId:'fileField1',//file标签的id 
            dataType: 'JSON',//返回数据的类型  
            success: function (data,status) { 
            	//$("#bg").remove();
    			if (status == "success") {
        			$( "#upload_data1" ).hide();
        			query();
        			$("#bg").remove();
        			sy.com_alert("上传完成","complete");
//    				sy.com_alert(json.msg,"complete");
    			} else{
    				sy.com_alert(json.msg);
    			}
            },  
            error: function (data, status, e) {  
            }  
        });
	
}
//关闭alert弹窗口
function closeUploadExploration(){
	$("#upload_data1").hide();
		$("#bg").remove();
}