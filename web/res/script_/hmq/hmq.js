var hmq = {
	webPath : '/yx/', // 项目路径
	fileSizeLimit : '10240kb', // uploadify 能创传的附件大小
	time : new Date().getMilliseconds(), // 进入项目时间
	submiting : false, // 用于防止重复提交
	ajaxing : false,
	path : function(url) {
		return hmq.webPath + url;
	},
	httpPath : function(url) {
		return "http://127.0.0.1/" + hmq.path(url);
	},
	timestamp : function(url) {// 添加时间戳 ,每个ajaxURL需加上时间戳，防止缓存出现
		if (url != '' && url.indexOf('tsp=') < 0) {
			var tsp = new Date();
			url += (url.indexOf('?') < 0 ? '?' : '&') + 'tsp=' + tsp.getTime();
		}
		return url;
	},
	// 手机号吗中间4位变成* 151 23308 745
	formatTel : function(tel) {
		if ($.isEmpty(tel)) {
			return "";
		}
		return tel.substring(0, 3) + "****" + tel.substring(7);
	},
	formatDateString : function(date) {
		var result = '';
		result += date.getFullYear();
		result += (date.getMonth() > 8) ? (date.getMonth() + 1) : '0'
				+ (date.getMonth() + 1);
		result += (date.getDate() > 9) ? date.getDate() : '0' + date.getDate();
		result += (date.getHours() > 9) ? date.getHours() : '0'
				+ date.getHours();
		result += (date.getMinutes() > 9) ? date.getMinutes() : '0'
				+ date.getMinutes();
		result += (date.getSeconds() > 9) ? date.getSeconds() : '0'
				+ date.getSeconds();
		return result;
	},
	formatDate : function(date) {
		var result = hmq.getWeekdayName(date);
		result += ' ';
		result += date.getFullYear();
		result += '-';
		result += (date.getMonth() > 8) ? (date.getMonth() + 1) : '0'
				+ (date.getMonth() + 1);
		result += '-';
		result += (date.getDate() > 9) ? date.getDate() : '0' + date.getDate();
		result += ' ';
		result += (date.getHours() > 9) ? date.getHours() : '0'
				+ date.getHours();
		result += ':';
		result += (date.getMinutes() > 9) ? date.getMinutes() : '0'
				+ date.getMinutes();
		result += ':';
		result += (date.getSeconds() > 9) ? date.getSeconds() : '0'
				+ date.getSeconds();
		result += ' ';
		return result;
	},
	getWeekdayName : function(date) {
		var result = '今天是：';
		switch (date.getDay()) {
		case 0:
			result += '星期天';
			break;
		case 1:
			result += '星期一';
			break;
		case 2:
			result += '星期二';
			break;
		case 3:
			result += '星期三';
			break;
		case 4:
			result += '星期四';
			break;
		case 5:
			result += '星期五';
			break;
		case 6:
			result += '星期六';
			break;
		default:
			result = '';
			break;
		}
		return result;
	},
	updateCurrentDate : function(time) {
		date = new Date();
		date.setTime(parseInt(time) + 1000);
		$('#currentDate').html(hmq.formatDate(date));
		setTimeout('hmq.updateCurrentDate(' + date.getTime() + ')', 1000);
	},
	hmqSubString : function(str, length) {
		return str.substring(0, length) + ". . .";
	},
	openWindow : function(url, w, h) {
		var par = "dialogHeight=" + h + ";dialogWidth=" + w
				+ ";help=yes;status=no";
		window.showModalDialog(url, par);
	}
};
/** *******************JQuery扩展********************* */

/**
 * 该方法需要jquery.validate.min.js与jquery.form.js ck edtior 不能取到值 succ 成功后处理方法 befor
 * 提交前处理方法
 */
jQuery.fn.validAjax = function(succ, befor, err) {
	var obj = $(this);
	obj.validate();
	obj.ajaxForm({
		beforeSubmit : function() {
			if (!$.isEmpty(befor)) {
				befor();
			}
			if (hmq.submiting && hmq.ajaxing) {
				return false;
			}
			if (obj.valid())
				hmq.ajaxing = true;
			return obj.valid();
		},
		success : function(data) {
			hmq.submiting = false;
			hmq.ajaxing = false;
			succ(eval("(" + data + ")"));
		},
		error : function(data) {
			hmq.submiting = false;
			hmq.ajaxing = false;
			if (err) {
				err(data);
			} else {
				alert("系统错误--hmq.js");
				// $.messager.alert("提示","系统错误","error");
			}
		}
	});
};
/*******************************************************************************
 * jQuery 表单提交********** spring mvc easyui form表单 json数据返回提交IE返回页面另存为 解决方案：
 * 
 * @RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
 * @ResponseBody public void saveUser(User user, HttpServletResponse res) throws
 *               IOException { Map<String, Object> result = new HashMap<String,
 *               Object>(2); result.put("msg", true); PrintWriter out =
 *               res.getWriter(); out.write(result.toString()); out.flush(); }
 *               2013.4.18修改验证方式
 */
jQuery.fn.formAjax = function(callBack, isValidate) {
	if (hmq.submiting && hmq.ajaxing) {
		$.messager.alert("提示", "后台正在处理中...", "error");
		return;
	}
	var isPass = true;
	if ($.isEmpty(isValidate)|| isValidate === true) {
		isPass = $(this).form('validate');
	}
	if (!isPass)
		return ;
	$.ajax({
		url : $(this).attr("action"),
		data : $(this).serialize(),
		cache : false,
		dataType : "json",
		type : "POST",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		success : function(data) {
			hmq.submiting = false;
			hmq.ajaxing = false;
			callBack(data);
		},error:function(a,b,c){
			console.log(a);
			console.log(b);
			console.log(c);
		}
	});
};
/** *******************jQuery 表单提交结束******************************* */

/*******************************************************************************
 * ajax 异步提交
 ******************************************************************************/
jQuery.extend({
	hmqAJAX : function(url, callback, parameters, error) {
		$.ajax({
			url : hmq.timestamp(url), // 加上时间戳
			cache : false,
			data : parameters,
			type : "POST",
			dataType : 'JSON',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			beforeSubmit : function() {
				if (hmq.submiting) {
					$.messager.alert("提示", "后台正在处理中...", "error");
					return false;
				}
				hmq.submiting = true;
				return true;
			},
			success : function(data) {
				hmq.submiting = false;
				callback(data);
			}
		});
	}
});
/*******************************************************************************
 * ajax 异步提交结束
 ******************************************************************************/

jQuery.extend({// 获取当前用户的sessionId
	getCurrentUser : function() {
		var _sessionModal = $.ajax({
			url : hmq.path("/home/currentUser"),
			dataType : "JSON",
			async : false,
			cache : false,
			type : "post"
		}).responseText;
		if ($.isEmpty(_sessionModal)) {
			return null;
		}
		var jsonSession = eval("(" + _sessionModal + ")");
		return jsonSession;
	}
});

/*******************************************************************************
 * jquery 扩展 验证是否为空
 ******************************************************************************/
jQuery.extend({
	isEmpty : function(str) {
		return str === undefined || str === null || str === '';
	}
});

/*******************************************************************************
 * jquery 扩展 验证是否为office文档
 ******************************************************************************/
jQuery.extend({
	isOffice : function(fileName) {
		var docType = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length);
		if (docType !== 'doc' && docType !== 'docx' && docType !== 'xls'
				&& docType !== 'xlsx' && docType !== 'ppt' && type !== 'pptx') {
			return null;
		}
		return docType;
	}
});
/** ***************************************** */
jQuery.extend({
	logout : function() {
		window.open("logout?tps=" + new Date().getTime(), "_self");
		// $.messager.confirm('提示', '您确定要退出系统吗？', function(r) {
		// if (r) {
		// window.open("home.do?logout&tps=" + new Date(), "_self");
		// }
		// });
	}
});
/**********************局部页面局部刷新*********************/
jQuery.fn.refreshpage = function(url) {
	 var $this=$(this);
	 $.ajax({
         type:"post",     
         url:url,   
         success:function(html){
        	 $this.html("");
        	 $this.append(html);
         },   
         error:function(){   
             alert("系统错误，请尝试刷新页面");   
         }
     });   
}
jQuery.extend({
	booleanIE : function() {
		var browser = navigator.appName;
		if (browser === "Microsoft Internet Explorer") {
			var b_version = navigator.appVersion;
			var version = b_version.split(";");
			var trim_Version = version[1].replace(/[ ]/g, "");
			if (trim_Version === "MSIE10.0") {
				return 10;// IE8浏览器
			} else if (trim_Version === "MSIE9.0") {
				return 9;// IE7浏览器
			} else if (trim_Version === "MSIE8.0") {
				return 8;
			} else if (trim_Version === "MSIE7.0") {
				return 7;
			}
		} else {
			return 0;// 不是IE浏览器
		}
	}
});

// $.getScript('./res/kindeditor/kindeditor-min.js', function() {
//            
// });
