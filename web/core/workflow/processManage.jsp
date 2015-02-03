<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	
})
function createFlow(){
	window.open("processDesign.htm?page_");
	/**$('#flowWin').window({  
	    width:$(self).width()*0.8,height:$(self).height()*0.8,
	    title:'流程设计',modal:true,
	    minimizable:false,
	    href:'flowDesign.do?page'
	});**/
}
function createFlowMini(){
	//window.open("./res/flow/myflow-min/demo4.html");
	//window.open("flowDesign.htm?pageeasy_");
	window.open("./res/flow/workflow/index.html");
}
function processDeploy(){
	$.hmqAJAX("../process/deploy",function(data){
		alert(data);
	});
}
function processStart(){
	$.hmqAJAX("../process/start",function(data){
		alert(data);
	});
}
function stopProcess(){//挂起
	var select=$("#processManage").easyselect();
	if(select){
		$.hmqAJAX("../process/stop",function(data){
			alert(data);
			$("#processManage").datagrid("load");
		},{"proId":select.id});
	}else{
		Easy.hmqDialogEror("请先选择要挂起的流程");
	}
}
function activationProcess(){//激活
	var select=$("#processManage").easyselect();
	if(select){
		$.hmqAJAX("../process/activation",function(data){
			alert(data);
			$("#processManage").datagrid("load");
		},{"proId":select.id});
	}else{
		Easy.hmqDialogEror("请先选择要激活的流程");
	}
	
}
</script>
<table id="processManage" class="easyui-datagrid" title="流程管理"
			 data-options="
			 	 singleSelect:true,
			 	 fit:true,
				 toolbar:'#processmanagetool',
			 	 url:'../process/finddeploy'
			 ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'100',align:'center'">流程ID</th>
					<th data-options="field:'deploymentId',width:'100',align:'center'">部署ID</th>
					<th data-options="field:'key',width:'100',align:'center'">KEY</th>
					<th data-options="field:'name',width:'150',align:'center'">流程名字</th>
					<th data-options="field:'version',width:'100',align:'center '">流程版本</th>
					<th data-options="field:'state',width:'100',align:'center '">状态</th>
				</tr>
			</thead>
		</table>
<div id="processmanagetool">
	<div id="menuToolbar" style="padding:5px;height:auto">
         <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="processDeploy()" plain="true">部署流程</a>|
         <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑流程</a>|
         <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="stopProcess()" plain="true">挂起</a>|
         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="activationProcess()" plain="true">激活</a>
	 </div>
</div>