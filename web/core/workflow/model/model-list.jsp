<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table id="processmodel" class="easyui-datagrid" title="流程管理"
			 data-options="
			 	 singleSelect:true,
			 	 fit:true,
				 toolbar:'#processmodeltool'
			 ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'100',align:'center'">模型ID</th>
					<th data-options="field:'key',width:'100',align:'center'">模型KEY</th>
					<th data-options="field:'name',width:'150',align:'center'">模型名字</th>
					<th data-options="field:'version',width:'100',align:'center '">模型版本</th>
					<th data-options="field:'createTime',width:'250',align:'center '">创建时间</th>
					<th data-options="field:'lastUpdateTime',width:'250',align:'center '">最后更新时间</th>
					<th data-options="field:'metaInfo',width:'300',align:'center '">元数据</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="model">
					<tr>
						<td>${model.id}</td>
						<td>${model.key}</td>
						<td>${model.name}</td>
						<td>${model.version}</td>
						<td>${model.createTime}</td>
						<td>${model.lastUpdateTime}</td>
						<td>${model.metaInfo}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">
			function addmodel(){
				$("#modeldlg").dialog("open");
			}
			function updatemodel(){
				var select =$("#processmodel").easyselect();
				if(select){
					window.open("${act}/modeler/service/editor?id="+select.id,'_blank');
				}else{
					Easy.hmqDialogEror("请选择要编辑的模型");
				}
			}
		</script>
		<div id="processmodeltool">
			<div id="menuToolbar" style="padding:5px;height:auto">
		         <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addmodel()" plain="true">添加流程</a>|
		         <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updatemodel()">编辑流程</a>|
			 </div>
		</div>
		<div id="modeldlg" class="easyui-dialog" title="流程模型添加" style="width:400px;height:200px;padding:10px"
	            data-options="
	                iconCls: 'icon-save',
	                closed:true,
	                buttons: [{
	                    text:'确定',
	                    iconCls:'icon-ok',
	                    handler:function(){
	                        $('#modelForm').submit();
	                    }
	                },{
	                    text:'取消',
	                    iconCls:'icon-remove',
	                    handler:function(){
	                        $('#modelForm').reset();
	                    }
	                }]
	            ">
	        <form id="modelForm" action="${ctx}/workflow/model/create" target="_blank" method="post">
	        	<table class="form">
	        		<tr>
	        			<th>名称</th>
	        			<td><input type="text" name="name"/></td>
	        		</tr>
	        		<tr>
	        			<th>KEY</th>
	        			<td><input type="text" name="key"/></td>
	        		</tr>
	        		<tr>
	        			<th>描述</th>
	        			<td><input type="text" name="description"/></td>
	        		</tr>
	        	</table>
	        </form>
	    </div>
</body>
</html>