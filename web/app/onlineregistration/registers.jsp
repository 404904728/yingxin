<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/res/print/print.js"></script>
<script type="text/javascript">
$(function(){
	
})
function queryUser(){
	$("#datagrid_register").datagrid("load",{
		"sex":$("input[name=sex]:checked").val(),
		"name":$("input[name=name]").val(),
		"nation.id":$("#nation").val(),
		"idcardno":$("input[name=idcardno]").val(),
		"grade":$("input[name=grade]:checked").val(),
		"origin":$("#origin").val()
	})
}
function addRegist(){
	$("#homeWin").window("setTitle","新增网上报名");
	$('#homeWin').window('open').window('refresh','${ctx}/onlineregist/page/save');
}
function updateRegist(){
	var selects=$("#datagrid_register").easyselect();
	if(selects){
		$("#homeWin").window("setTitle","修改网上报名");
		$('#homeWin').window('open').window('refresh','${ctx}/onlineregist/page/updatepage/'+selects.id);
	}else{
		Easy.hmqDialogEror("请您选择需要修改的行");
	}
	
}
function printRegist(){
	var selects=$("#datagrid_register").easyselect();
	if(selects){
		//$("#datagrid_register").datagrid("loading");
		if(selects.grade==2){
			createprintpage_xsc(selects.name,selects.examcardno)
		}else if(selects.grade==3){
			createprintpage_csg(selects.name,selects.examcardno)
		}
		LODOP.PREVIEW()
	}else{
		Easy.hmqDialogEror("请您选择需要修改的行");
	}
}
</script>
</script>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width="0" height="0"></object>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="查询" style="width:300px;padding:0px">
		<form id="registqueryform" action="">
			<table class="form">
				<tr>
					<th width="35%">姓名</th>
					<td width="65%">
						<input type="text" name="name"/>
					</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td>
						<input type="text" name="idcardno"/>
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						保密<input type="radio" name="sex" value="2"/>
						男<input type="radio"  name="sex" value="1"/>
						女<input type="radio"  name="sex" value="0"/>
					</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>
						<select id="nation" name="nation.id">
							<option value="">请选择</option>
							<c:forEach items="${nations}" var="nation">
								<option value="${nation.id}">${nation.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>拟读年纪</th>
					<td>
						小学<input type="radio" name="grade" value="1"/>
						初中<input type="radio"  name="grade" value="2"/>
						高中<input type="radio"  name="grade" value="3"/>
					</td>
				</tr>
				<tr>
					<th>填报时间(>)</th>
					<td>
						 <input id="reprotStartDate" type="text" class="easyui-datebox textbox"/>
					</td>
				</tr>
				<tr>
					<th>填报时间(<)</th>
					<td>
						 <input id="reprotEndDate" type="text" class="easyui-datebox textbox"/>
					</td>
				</tr>
				<tr>
					<th>信息来源</th>
					<td>
						<select id="origin" name="origin">
							<option value="">请选择</option>
							<option value="0">亲友介绍</option>
							<option value="1">老师推荐</option>
							<option value="2">慕名报读</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>操作</th>
					<td>
						 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						 <a href="#" onclick="$('#registqueryform')[0].reset()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid_register" class="easyui-datagrid" title="学生网上报名信息"
			 data-options="
			 	 singleSelect:true,pagination:true,
			 	 fit:true,toolbar:'#menuToolbar',rownumbers:true,
			 	 url:'${ctx}/onlineregist/data/ht'
		     ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'80',hidden:true">ID</th>
					<th data-options="field:'name',width:'80',sortable:true">姓名</th>
					<th data-options="field:'birthday',width:'80',sortable:true">出生年月</th>
					<th data-options="field:'sex',width:'80',sortable:true,
						formatter:function(value){
							return value==0?'女':(value==1?'男':'保密');
						}">性别</th>
					<th data-options="field:'idcardno',width:'150',sortable:true">身份证</th>
					<th data-options="field:'examcardno',width:'100',sortable:true,
						formatter:function(value){
							if($.isEmpty(value))return '';
							return '<font color=blue>'+value+'</font>';
						}">准考证号</th>
					<th data-options="field:'nation',width:'150',
						formatter:function(value){
							return value==null?'':value.name;
						}">民族</th>
					<th data-options="field:'place',width:'150',sortable:true">户口所在地</th>
					<th data-options="field:'address',width:'150',sortable:true">现住址</th>
					<th data-options="field:'school',width:'100',sortable:true">现就读学校</th>
					<th data-options="field:'grade',width:'100',sortable:true,
						formatter:function(value){
							return value==1?'小学':(value==2?'初中':'高中');
						}">拟读年级</th>
					<th data-options="field:'father',width:'100',sortable:true">监护人</th>
					<th data-options="field:'date',width:'150',sortable:true">填报时间</th>
				</tr>
			</thead>
		</table>
		 <div id="menuToolbar" style="padding:5px;height:auto">
            <a href="#" onclick="addRegist()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>|
            <a href="#" class="easyui-linkbutton" onclick="updateRegist()" iconCls="icon-edit" plain="true">编辑</a>|
            <a href="#" class="easyui-linkbutton" onclick="printRegist()" iconCls="icon-print" plain="true">打印准考证</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		 </div>
	</div>
</div>
