var sy = sy || {};
sy.msgShowCenter = function(msg) {
	$.messager.show({
		title : '消息',
		msg : msg,
		width : '250px',
		height : '180px',
		showSpeed : 0,
		timeout : 2000,
		showType : 'show',
		style : {
			right : '',
			top : (document.documentElement.scrollHeight) / 4,
			bottom : ''
		}
	});

};
sy.progress = function() {
	$.messager.progress({
		title : '处理中',
		msg : '加载中....',
		text : '正在进行操作.......'
	});
};
sy.closeProgress = function() {
	$.messager.progress('close');
};
// 添加并显示遮罩层
sy.addshade = function(divId) {
	// 计算机屏幕高度
	var windowWidth = $(window).width();
	// 计算机屏幕长度
	var windowHeight = $(window).height();
	$("<div id='bg'></div>").width(windowWidth * 0.99).height(
			windowHeight * 0.99).click(function() {
	}).appendTo("#" + divId);
}
// 读取配置文件
sy.readPropertise = function(p_key) {
	var url = "common/findPropertiseVal";
	var p_val = "";
	$.ajax({
		type : 'get',
		url : url,
		dataType : "json",
		async : false,
		data : {
			p_key : p_key
		},
		success : function(data) {
			p_val = data.p_val;
		}
	});
	return p_val;
}

sy.com_alert = function com_alert(msg, tag) {
	/*
	 * if(tag=='complete'){ $("#img_tag").attr("src","/images/complete.png");
	 * }else{ $("#img_tag").attr("src","/images/warning.png"); }
	 */
	$("#dialog-form-alert").show();
	$("#span_alert").html(msg);
	if(tag!="notimeout"){
		setTimeout(function() {
			$("#dialog-form-alert").hide();
		}, 3000);
	}else{
		$("#qd").hide();
		$("#gb").hide();
	}
}

// 将form表单数据转换成json String
$.fn.serializeJson = function() {
	var serializeObj = {};
	var array = this.serializeArray();
	var str = this.serialize();
	$(array).each(
			function() {
				if (serializeObj[this.name]) {
					if ($.isArray(serializeObj[this.name])) {
						serializeObj[this.name].push(this.value);
					} else {
						serializeObj[this.name] = [ serializeObj[this.name],
								this.value ];

					}
				} else {
					serializeObj[this.name] = this.value;
				}
			});
	var jsonStr = JSON.stringify(serializeObj)
	return jsonStr;
}

// 动态提交使用jquery完成ajax文件下载，当不用传参时，可以将data去掉
sy.download = function(url, data, method) { // 获得url和data
	if (url && data) {
		// data 是 string 或者 array/object
		data = typeof data == 'string' ? data : jQuery.param(data); // 把参数组装成
																	// form的
																	// input
		var inputs = '';
		jQuery.each(data.split('&'), function() {
			var pair = this.split('=');
			inputs += '<input type="hidden" name="' + pair[0] + '" value="'
					+ pair[1] + '" />';
		}); // request发送请求
		jQuery(
				'<form action="' + url + '" method="' + (method || 'post')
						+ '">' + inputs + '</form>').appendTo('body').submit()
				.remove();
	}
	;
};

// 删除tbody中的内容
sy.clrCarrier = function(tbodyId) { // 获得url和data
	var planCarrierTbody = document.getElementById(tbodyId);
	var tr = planCarrierTbody.getElementsByTagName("tr");
	if (planCarrierTbody) {
		for (var i = tr.length - 1; i >= 0; i--) {
			var node = tr[i];
			node.parentNode.removeChild(node);
		}
	}
};
// 弹出框的挪动
sy.mousedivdown = function(divId, divClass) {
	$('#' + divId).mousedown(function(event) {
		var isMove = true;
		var abs_x = event.pageX - $('div.' + divClass).offset().left;
		var abs_y = event.pageY - $('div.' + divClass).offset().top;
		$(document).mousemove(function(event) {
			if (isMove) {
				var obj = $('div.' + divClass);
				obj.css({
					'left' : event.pageX - abs_x,
					'top' : event.pageY - abs_y
				});
			}
		}).mouseup(function() {
			isMove = false;
		});
	});
}

/**
 * 判断界面中的必填项是否填写
 * 
 * @returns {Boolean}
 */
sy.validateRequiredFields = function validateRequiredFields(formID) {
	var formEl = $("#" + formID + "");
	var requiredFields = $("input[validate~='required']", formEl);

	var invalidInput = [];
	$.each(requiredFields, function(index, field) {
		var fieldEl = $(field);
		if (sy.stringIsEmpty(field.value)) {
			invalidInput.push(fieldEl);
			fieldEl.addClass("invalidField");
		}
	});
	if (invalidInput.length == 0) {
		return true;
	} else {
		return false;
	}

}
/**
 * 判断输入框是否为空
 * 
 * @param str
 * @returns {Boolean}
 */
sy.stringIsEmpty = function stringIsEmpty(str) {
	var str = $.trim(str);
	if (str === "") {
		return true;
	} else {
		return false;
	}
}

// base64加密
sy.encodeBase64 = function encodeBase64(mingwen, times) {
	var code = "";
	var num = 1;
	if (typeof times == 'undefined' || times == null || times == "") {
		num = 1;
	} else {
		var vt = times + "";
		num = parseInt(vt);
	}
	if (typeof mingwen == 'undefined' || mingwen == null || mingwen == "") {
	} else {
		$.base64.utf8encode = true;
		code = mingwen;
		for (var i = 0; i < num; i++) {
			code = $.base64.btoa(code);
		}
	}
	return code;
}