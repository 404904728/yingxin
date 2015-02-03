<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	
})
function updateRole(id){
	var selects=$('#roleTable').easyselect();
	if(selects){
		$("#homeWin").window("setTitle","角色修改");
		$('#homeWin').window('open').window('refresh','${ctx}/role/pageform&id='+selects.id);
	}else{
		Easy.hmqDialogEror("请先选择要操作的行");
	}
}
function addRole(id){
	$("#homeWin").window("setTitle","角色添加");
	$('#homeWin').window('open').window('refresh','${ctx}/role/pageform');
}
function deleteRole(id){
	var selects=$('#roleTable').easyselect();
	if(selects){
		Easy.hmqConfirm("警告","您确定删除该记录吗?",function(){
			$.hmqAJAX('${ctx}/role/del',function(data){
				 $("#roleTable").datagrid("reload");
				 Easy.hmqDialog(data);
			},{"id":selects.id});
		})
	}else{
		Easy.hmqDialogEror("请先选择要操作的行");
	}
}
function grantPermission(){
	var selects=$('#roleTable').easyselect();
	if(selects){
		$("#homeWin").window("setTitle","权限分配");
		$("#homeWin").window("open").window("refresh",'${ctx}/role/grantperpage?id='+selects.id);
	}else{
		Easy.hmqDialogEror("请先选择要操作的行");
	}
	
}
function grantUser(){
	var selects=$('#roleTable').easyselect();
	if(selects){
		$("#homeWin").window("setTitle","人员分配");
		$("#homeWin").window("open").window("refresh",'${ctx}/role/grantuserpage?id='+selects.id);
	}else{
		Easy.hmqDialogEror("请先选择要操作的行");
	}
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="查询" style="width:250px;padding: 5px 2px 0 2px">
	</div>
	<div data-options="region:'center'">
		<table id="roleTable" class="easyui-datagrid"  title="角色信息"
			data-options="
				singleSelect:true,
				collapsible:true,rownumbers:true,
				fit:true,pagination:true,
				toolbar:'#rightMenuRole',
				url:'../role/findData',
				pagerBtns:[{
						iconCls:'icon-add',
						handler:function(){
							$('#homeWin').window('open').window('refresh','role.htm?pageform');;
						}
					},'-',{
					iconCls:'icon-save',
					handler:function(){
						
					}
				}]
			">
			<thead>
				<tr>
					<th data-options="field:'name',width:200,sortable:true">角色名称</th>
					<th data-options="field:'use',width:100,sortable:true,formatter:function(value){return value?'是':'否'}">是否可用</th>
					<th data-options="field:'desc',width:300,sortable:true">角色描述</th>
				</tr>
			</thead>
		</table>
		 <div id="rightMenuRole"  style="padding:5px;height:auto">
           <a href="#" onclick="addRole()" class="easyui-linkbutton" plain="true"><i class="purple big  add icon"/>新增</a>|
           <a href="#" onclick="updateRole()" class="easyui-linkbutton" plain="true"><i class="purple big edit icon"/>编辑</a>|
           <a href="#" onclick="deleteRole()" class="easyui-linkbutton" plain="true"><i class="purple big remove icon"/>删除</a>|
           <a href="#" onclick="grantPermission()" class="easyui-linkbutton" plain="true"><i class="purple big setting icon"/>分配权限</a>|
           <a href="#" onclick="grantUser()" class="easyui-linkbutton"  plain="true"><i class="purple big users icon"/>配置用户</a>
	 	 </div>
	</div>
</div>