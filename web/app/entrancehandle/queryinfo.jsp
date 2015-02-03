<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function(){
				
})
function queryUser(){
	
	$("#datagrid_register").datagrid("reload",{
		"stage":$.trim($("input:radio[name='stage']:checked").val()),
		"examCardNo":$.trim($("#queryzhunkaoNo").val()),
		"name":$.trim($("#queryName").val()),
		"sex":$.trim($("input:radio[name='sex']:checked").val()),
		"startTime":$.trim($("#queryReprotStartDate").datebox('getValue')),
		"endTime":$.trim($("#queryReprotEndDate").datebox('getValue'))
	});
}
function updateRegist(){
	var selects=$("#datagrid_register").easyselect();
	//Easy.hmqDialogEror(selects.id);
	//Easy.hmqDialogEror(selects.isPay);
	//console.info(selects);
	if(selects){
		if(selects.isPay == 1){
			Easy.hmqDialogEror("该生已经缴费,无法修改");
			return;
		}else{
			$("#homeWin").window("setTitle","修改报名学生信息");
			$('#homeWin').window('open').window('refresh','${ctx}/entrancehandel/updatestudentmessage/'+selects.id);
		}
	}else{
		Easy.hmqDialogEror("请您选择需要修改的行");
	}
	
}
function resetForm(){
	document.getElementById('queryFormToStudent').reset();
	$("#queryReprotStartDate").datebox('setValue','');
	$("#queryReprotEndDate").datebox('setValue','');
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="查询" style="width:300px;padding: 5px 2px 0 2px">
		<form action="" id="queryFormToStudent">
			<table class="form">
				<tr>
					<th>拟读年级</th>
					<td>
						小学<input type="radio" name="stage" value="1"/>
						初中<input type="radio" name="stage" value="2"/>
						高中<input type="radio" name="stage" value="3"/>
					</td>
				</tr>
				<tr>
					<th width="35%">准考证号码</th>
					<td width="65%">
						<input type="text" id="queryzhunkaoNo" name="examCardNo"/>
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						<input type="text" id="queryName" name="name"/>
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						男<input type="radio" name="sex" value="1"/>
						女<input type="radio" name="sex" value="0"/>
					</td>
				</tr>
				<%-- <tr>
					<th>民族</th>
					<td>
						<select name="nation.id" id="queryStudentNation">
							<option value="">--请选择--</option>
							<c:forEach items="${nations}" var="nation">
								<option value="${nation.id}">${nation.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr> --%>
				<tr>
					<th>填报时间(>)</th>
					<td>
						 <input id="queryReprotStartDate" type="text" class="easyui-datebox textbox"/>
					</td>
				</tr>
				<tr>
					<th>填报时间(<)</th>
					<td>
						 <input id="queryReprotEndDate" type="text" class="easyui-datebox textbox"/>
					</td>
				</tr>
				<tr>
					<th>操作</th>
					<td>
						 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						 <a href="#" class="easyui-linkbutton" onclick="resetForm()" data-options="iconCls:'icon-reload'">清除</a>
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
			 	 url:'${ctx}/entrancehandel/finddata/data'
		     ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'80'">ID</th>
					<th data-options="field:'examCardNo',width:'100'">准考证号</th>
					<th data-options="field:'stage',width:'100',
						formatter:function(value){
							return value==1?'小学':(value==2?'初中':'高中');
						}">拟读年级</th>
					<th data-options="field:'name',width:'80'">姓名</th>
					<th data-options="field:'birthday',width:'80'">出生年月</th>
					<th data-options="field:'sex',width:'80',
						formatter:function(value){
							return value==0?'女':'男';
						}">性别</th>
					<th data-options="field:'idCardNo',width:'150'">身份证</th>
					<!-- <th data-options="field:'nation',width:'150',
						formatter:function(value){
							return value==null?'':value.name;
						}">民族</th> -->
					<th data-options="field:'nativePlace',width:'150'">籍贯</th>
					<th data-options="field:'address',width:'150'">现住址</th>
					<!-- <th data-options="field:'school',width:'100'">现就读学校</th> -->
					<th data-options="field:'guarDian',width:'100'">监护人</th>
					<th data-options="field:'createTime',width:'150'">填报时间</th>
					<th data-options="field:'isTeacherChild',width:'100',
						formatter:function(value){
							return value==0?'否':'是';
						}">教师子女</th>
					<th data-options="field:'scholarShip',width:'100',
						formatter:function(value){
							return value==0?'无':(value==1?'一等奖学金':(value==2?'二等奖学金':(value==3?'三等奖学金':(value==4?'四等奖学金':'五等奖学金'))));
						}">奖学金</th>
					<th data-options="field:'year',width:'100'">入学年份</th>
					<th data-options="field:'isPay',width:'100',
						formatter:function(value){
							return value==0?'否':'是';
						}">是否缴费</th>
				</tr>
			</thead>
		</table>
		 <div id="menuToolbar" style="padding:5px;height:auto">
            <!-- <a href="#" onclick="addRegist()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>| -->
            <a href="#" class="easyui-linkbutton" onclick="updateRegist()" iconCls="icon-edit" plain="true">信息修改</a>
            <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
		 </div>
	</div>
</div>
