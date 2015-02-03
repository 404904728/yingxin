<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	
})
function queryUser(){
	$("#datagrid_user").datagrid("load",{
		"no":$("input[name=no]").val(),
		"name":$("input[name=name]").val()
	})
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="查询" style="width:250px;padding: 5px 2px 0 2px">
		<table class="form">
			<tr>
				<th width="30%">帐号</th>
				<td width="70%">
					<input type="text" name="no"/>
				</td>
			</tr>
			<tr>
				<th>姓名</th>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>
					保密<input type="radio" name="sex"/>
					男<input type="radio" name="sex"/>
					女<input type="radio" name="sex"/>
				</td>
			</tr>
			<tr>
				<th>组织</th>
				<td>
					
				</td>
			</tr>
			<tr>
				<th>操作</th>
				<td>
					 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid_user" class="easyui-datagrid" title="用户信息"
			 data-options="
			 	 singleSelect:true,pagination:true,
			 	 fit:true,toolbar:'#menuToolbar',rownumbers:true,
			 	 url:'${ctx}/user/finduser'
		     ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'80',align:'center'">用户ID</th>
					<th data-options="field:'name',width:'100',align:'center'">用户姓名</th>
					<th data-options="field:'no',width:'80',align:'center'">用户帐号</th>
					<th data-options="field:'sex',width:'80',align:'center',
						formatter:function(value){
							return value==0?'保密':(value==1?'男':'女');
						}">用户性别</th>
					<th data-options="field:'org',width:'100',align:'center',
						formatter:function(value){
							return value==null?'':value.name;
						}">所属组织</th>
					<th data-options="field:'tel',width:'80',align:'center'">联系电话</th>
				</tr>
			</thead>
		</table>
		 <div id="menuToolbar" style="padding:5px;height:auto">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		 </div>
	</div>
</div>
