<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function(){
	$("#grandquery").combobox({
        onHidePanel:function(){
        	var jieduanvalue = $('#grandquery').combobox('getValue');
        	$('#classesquery').combobox('clear');
			$('#classesquery').combobox('reload','${ctx}/bedroomallot/findgrand?id='+jieduanvalue);
		}
    });
})
function inintclassmess(id){
	if(id){
		$('#grandquery').combobox('clear');
		$('#grandquery').combobox('reload','${ctx}/bedroomallot/findgrand?id='+id);
	}
}
function queryUser(){
	
	$("#datagrid_register").datagrid("reload",{
		"examCardNo":$.trim($("#queryzhunkaoNo").val()),
		"name":$.trim($("#stuName").val()),
		"dormitory":$.trim($("#dormitory").val()),
		"sex":$.trim($("input:radio[name='sex']:checked").val()),
		"org":$.trim($('#classesquery').combobox('getValue'))
	});
}
function updateRegist(){
	var selects=$("#datagrid_register").easyselect();
	//Easy.hmqDialogEror(selects.id);
	//Easy.hmqDialogEror(selects.isPay);
	//console.info(selects);
	if(selects){
		$("#homeWin").window("setTitle","修改学生寝室信息");
		$('#homeWin').window('open').window('refresh','${ctx}/bedroomallot/updatebedroom/?sex='+selects.sex+'&stuId='+selects.id+'&roomId='+selects.bedid);
	}else{
		Easy.hmqDialogEror("请您选择需要修改的行");
	}
	
}
function resetForm(){
	document.getElementById('queryFormToStudent').reset();
	$('#grandquery').combobox('clear');
	$('#classesquery').combobox('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="寝室查询" style="width:300px;padding: 5px 2px 0 2px">
		<form action="" id="queryFormToStudent">
			<table class="form">
				<tr>
					<th width="35%">准考证号码</th>
					<td width="65%">
						<input type="text" id="queryzhunkaoNo" name="examCardNo"/>
					</td>
				</tr>
				<tr>
					<th width="35%">学生姓名</th>
					<td width="65%">
						<input type="text" id="stuName" name="name"/>
					</td>
				</tr>
				</table>
				<hr color="#daeef5">
				<hr color="#daeef5">
				<table class="form">
				<tr>
					<th width="35%">寝室编码</th>
					<td width="65%">
						<input type="text" id="dormitory" name="dormitory"/>
					</td>
				</tr>
				<tr>
					<th>寝室类别</th>
					<td>
						男生寝室<input type="radio" name="sex" value="1"/>
						女生寝室<input type="radio" name="sex" value="0"/>
					</td>
				</tr>
				</table>
				<hr color="#daeef5">
				<hr color="#daeef5">
				<table class="form">
				<tr>
					<th width="35%">阶段</th>
					<td width="65%">
						小学<input type="radio" name="stage" value="2" onchange="inintclassmess(this.value)"/>
						初中<input type="radio" name="stage" value="3" onchange="inintclassmess(this.value)"/>
						高中<input type="radio" name="stage" value="4" onchange="inintclassmess(this.value)"/>
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
				            <select class="easyui-combobox" id="grandquery" style="width:130px" data-options="
							valueField:'id',textField:'text',panelHeight:'auto',editable:false">
				            </select>&nbsp;
					</td>
				</tr>
				
				<tr>
					<th>班级</th>
					<td>
				            <select class="easyui-combobox" id="classesquery" style="width:130px" name="org"
				            data-options="
							valueField:'id',textField:'text',panelHeight:'auto',editable:false">
				            </select>&nbsp;
					</td>
				</tr>
				</table>
				<hr color="#daeef5">
				<hr color="#daeef5">
				<table class="form">
				<tr>
					<td colspan="2" style="text-align: center;">
						 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						 <a href="#" class="easyui-linkbutton" onclick="resetForm()" data-options="iconCls:'icon-reload'">清除</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid_register" class="easyui-datagrid" title="学生寝室信息"
			 data-options="
			 	 singleSelect:true,pagination:true,
			 	 fit:true,toolbar:'#querybedroom',rownumbers:true,
			 	 url:'${ctx}/bedroomallot/querystudentroom'
		     ">
			<thead>
				<tr>
					<th data-options="field:'id',width:'80', hidden:'true'">stuID</th>
					<th data-options="field:'bedid',width:'80', hidden:'true'">bedID</th>
					<th data-options="field:'examcardno',width:'100'">准考证号</th>
					<th data-options="field:'stuName',width:'80'">姓名</th>
					<th data-options="field:'sex',width:'80',
						formatter:function(value){
							return value==0?'女':'男';
						}">性别</th>
					<th data-options="field:'guardian',width:'100'">监护人</th>
					<th data-options="field:'tel',width:'100'">监护人电话</th>
					<th data-options="field:'bedName',width:'150'">所在寝室</th>
					<th data-options="field:'bedCode',width:'150'">寝室编码</th>
				</tr>
			</thead>
		</table>
		 <div id="querybedroom" style="padding:5px;height:auto">
            <a href="#" class="easyui-linkbutton" onclick="updateRegist()" iconCls="icon-edit" plain="true">学生寝室修改</a>
		 </div>
	</div>
</div>
