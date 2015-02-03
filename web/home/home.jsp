<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成都七中实验学校-迎新系统</title>
<link rel="stylesheet" href="${ctx}/res/css/form/form.css" />
<link rel="stylesheet" href="${ctx}/res/css/icon/easy-icons/easy-icon.css" />
<link rel="stylesheet" href="${ctx}/res/script_/easyui-1.3.6/themes/icon.css" />
<link rel='stylesheet' type='text/css' href='${ctx}/res/script_/easyui-1.3.6/themes/${sessionModal.skin}/easyui.css' />
<script type="text/javascript" src="${ctx}/res/script_/easyui-1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/easyui-1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/hmq/easyUtil.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/hmq/easyValid.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/hmq/hmq.js"></script>
<!-- semantic ui -->
<link rel="stylesheet" href="${ctx}/res/semantic/minified/modules/transition.min.css" />
<script src="${ctx}/res/semantic/minified/modules/transition.min.js"></script>
<link rel="stylesheet" href="${ctx}/res/semantic/minified/elements/icon.min.css" />
<!-- semantic ui end-->
<script type="text/javascript">
	$(function() {
		$("#msg").html(
				"欢迎您:&nbsp<a href='#'>${sessionModal.name}</a>&nbsp&nbsp"
						+ hmq.getWeekdayName(new Date()));
		$("#homeWin").window({
			width : $(self).width() * 0.8,
			height : $(self).height() * 0.8,
			closed : true,
			modal : true,
			minimizable : false
		});
	});
	function changeSkin(skin) {//换肤
		$.hmqAJAX("../home/changeskin", function(data) {
			window.top.location.reload();
		}, {
			"skin" : skin
		})
	}
	function refreshhomepage(url) {
		$('#homecenterpage').transition({
			animation : 'scale',
			duration : '0.5s',
			complete : function() {
				$("#homecenterpage").html("");
				$("#homeLayout").layout("panel", "center").panel("refresh",url);
			}
		}).transition({
			animation : 'scale',
			duration : '0.5s',
			complete : function() {
				
			}
		})
	}
</script>
</head>
<body>
	<div id="homeLayout" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false"
			style="height: 90px; line-height: 30px; background: #F2F2F2; padding: 0; border: 1px solid #ddd">
			<div class="easyui-panel" data-options="fit:true"
				style="padding-left: 5px">
				<div style="text-align: right; margin-top: 10px">
					<a href="#" class="easyui-linkbutton"
						data-options="plain:true,size:'large',iconCls:'icon-house-large',iconAlign:'top'">主页</a>
					<c:forEach items="${headmenus}" var="headmenu">
						<c:if test="${fn:length(headmenu.child)!=0}">
							<a href="#" class="easyui-menubutton"
								data-options="menu:'#menu${headmenu.id}',size:'large',iconCls:'${headmenu.icon}',iconAlign:'top'">${headmenu.name}</a>
							<div id="menu${headmenu.id}" style="width: 150px;">
								<c:forEach items="${headmenu.child}" var="child">
									<div data-options="iconCls:'${child.icon}'"
										onclick="refreshhomepage('${child.url}')">${child.name}</div>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${fn:length(headmenu.child)==0}">
							<a href="#" class="easyui-linkbutton" onclick="refreshhomepage('${ctx}${headmenu.url}')"
								data-options="plain:true,size:'large',iconCls:'${headmenu.icon}',iconAlign:'top'">${headmenu.name}</a>
						</c:if>
					</c:forEach>
					<a href="#" class="easyui-menubutton"
						data-options="menu:'#skin',size:'large',iconCls:'icon-drawing-large',iconAlign:'top'">换肤</a>
					<a href="#" class="easyui-menubutton"
						data-options="menu:'#help_menu',size:'large',iconCls:'icon-set-large',iconAlign:'top'">设置</a>
					<div id="skin" style="width: 150px;">
						<div onclick="changeSkin('default')">default</div>
						<div onclick="changeSkin('black')">black</div>
						<div onclick="changeSkin('bootstrap')">bootstrap</div>
						<div onclick="changeSkin('gray')">gray</div>
						<div onclick="changeSkin('metro')">metro</div>
						<div onclick="changeSkin('metro-blue')">metro-blue</div>
						<div onclick="changeSkin('metro-gray')">metro-gray</div>
						<div onclick="changeSkin('metro-green')">metro-green</div>
						<div onclick="changeSkin('metro-orange')">metro-orange</div>
						<div onclick="changeSkin('metro-red')">metro-red</div>
						<div onclick="changeSkin('ui-cupertino')">ui-cupertino</div>
						<div onclick="changeSkin('ui-dark-hive')">ui-dark-hive</div>
						<div onclick="changeSkin('ui-pepper-grinder')">ui-pepper-grinder</div>
						<div onclick="changeSkin('ui-sunny')">ui-sunny</div>
					</div>
					<div id="help_menu" style="width: 100px;">
						<div data-options="iconCls:'icon-undo'">Help</div>
						<div data-options="iconCls:'icon-undo'" onclick="$.logout()">退出</div>
						<div data-options="iconCls:'icon-undo'">关于</div>
					</div>
				</div>
			</div>
		</div>
		<%-- <div data-options="region:'west',href:'${ctx}/menu/index',border:false,split:true,title:'菜单'" style="width:180px"></div> --%>
		<div data-options="region:'south',border:false"
			style="height: 30px; background: #A9FACD; text-align: center;">
			Copyright © 2013-1-25 HMQ2.0</div>
		<div id="homecenterpage" data-options="region:'center',border:false" style="padding: 5px"></div>
	</div>
	<div id="homeWin" style="padding: 5px">
		<!-- 所有模态弹出框 -->
	</div>
</body>
</html>