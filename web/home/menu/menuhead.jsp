<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="h" uri="/hmq-tags"%>

<script type="text/javascript">
$(function(){
	
})
function changeSkin(skin){//换肤
	$.hmqAJAX("../home/changeskin",function(data){
		window.top.location.reload();
	},{"skin":skin})
}
function  refreshhomepage(url){
	$("#homeLayout").layout("panel", "center").panel("refresh",url);
}
</script>
<div style="text-align: right;margin-top: 10px">
<a href="#" class="easyui-linkbutton" data-options="plain:true,size:'large',iconCls:'icon-house-large'" >主页</a>
<c:forEach items="${headmenus}" var="headmenu">
	<a href="#" class="easyui-menubutton" data-options="menu:'#menu${headmenu.id}',size:'large',iconCls:'${headmenu.icon}'">${headmenu.name}</a>
	<div id="menu${headmenu.id}" style="width:150px;">
	<c:forEach items="${headmenu.child}" var="child">
		<div data-options="iconCls:'${child.icon}'" onclick="refreshhomepage('${child.url}')">${child.name}</div>
	</c:forEach>
	</div>
</c:forEach>
<a href="#" class="easyui-menubutton" data-options="menu:'#skin',size:'large',iconCls:'icon-drawing-large'">换肤</a>
<a href="#" class="easyui-menubutton" data-options="menu:'#help_menu',size:'large',iconCls:'icon-set-large'">设置</a>
<div id="skin" style="width:150px;">
	<div onclick="changeSkin('default')">default</div>
	<div onclick="changeSkin('black')">black</div>
	<div onclick="changeSkin('bootstrap')">bootstrap</div>
	<div onclick="changeSkin('gray')">gray</div>
	<div onclick="changeSkin('metro')">metro</div>
	<div onclick="changeSkin('ui-cupertino')">ui-cupertino</div>
	<div onclick="changeSkin('ui-dark-hive')">ui-dark-hive</div>
	<div onclick="changeSkin('ui-pepper-grinder')">ui-pepper-grinder</div>
	<div onclick="changeSkin('ui-sunny')">ui-sunny</div>
</div>
<div id="help_menu" style="width:100px;">
	<div data-options="iconCls:'icon-undo'">Help</div>
	<div data-options="iconCls:'icon-undo'" onclick="$.logout()">退出</div>
	<div data-options="iconCls:'icon-undo'">关于</div>
</div>
</div>