<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$.hmqAJAX("",function(){
			
	})
})
function sqlQuery(){
	var sqlString=$('#sqlString').val();
	if($.isEmpty(sqlString)){
		Easy.hmqDialogWarning("请先输入sql，再点击查询");
		return;
	}
	$.hmqAJAX("../sqlQuery/columns",function(data){
		$("#datagrid_sql").datagrid({
			url:'../sqlQuery/query',
			//data:data.value,
			pagination:true,rownumbers:true,
			//singleSelect:true,fitColumns:true,
			fit:true,toolbar:'#menuToolbar',
			queryParams:{"sqlString":sqlString},
			pagerBtns:[{
				iconCls:'icon-add',
				handler:function(){alert('add')}
			},'-',{
				iconCls:'icon-save',
				handler:function(){alert('save')}
			}],
			columns:[data]
		});
	},{"sqlString":sqlString})
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north', tools:'#sqlQueryTools'" title="sql查询" style="height:150px;padding: 5px 2px 0 2px">
		<textarea id="sqlString" rows="" cols="" style="width: 99%;height: 92%"></textarea>
		<div id="sqlQueryTools">
			<a href="#" class="icon-search" onclick="sqlQuery()"></a>
		</div>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid_sql"></table>
		 <div id="menuToolbar" style="padding:5px;height:auto">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		 </div>
	</div>
</div>
