<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<script type="text/javascript" src="${ctx}/res/script_/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
		$("#datagrid_xsc_d").datagrid({
			    url:'${ctx}/xsccheck/findata_d?stage=${stage}&year=${year}',
			    columns:[[{field:'id',hidden:true,width:1},
			             {field:'stage',title:'阶段',width:100,formatter:function(value){
								return value==1?'小学':(value==2?'初中':'高中');
							}},{field:'examCardNo',title:'考号',width:130},{field:'name',title:'姓名',width:110},
							{field:'sex',title:'性别',width:80,
								formatter:function(value){
									return value==0?'女':'男';
								}},{field:'birthday',title:'出生年月',width:130},{field:'idCardNo',title:'身份证',width:130}
								,{field:'nativePlace',title:'籍贯',width:100}
								,{field:'guarDian',title:'监护人姓名',width:130}
								,{field:'tel',title:'监护人电话',width:130}
								,{field:'year',title:'入学年份',width:100}
								,{field:'status',title:'状态',width:100,
									formatter:function(value){
										return value=='1'?'未到':'多到';
									}}
								,{field:'checkstatus',title:'确认情况',width:100,
										formatter:function(value){
											return value == 0?'未确认':'已确认';
										}}
								]],
			    pagination:true,
			    fit:true,
			    singleSelect:true,
			    toolbar:'#menuToolbar',
			    rownumbers:true,
			    fitColumns:true
			});
})

$("#searchlink").click(function(){
	$("#datagrid_xsc_d").datagrid("reload",getConditions());
});

$("#returnlink").click(function(){
	var flag = "${flag}";
	if(flag && 'jwc' == flag){
		refreshhomepage('${ctx}/xsccheck/ppage_jwc');
	}else{
	    refreshhomepage('${ctx}/xsccheck/ppage');
	}
});

function getConditions(){
	var conditon = {};
	var kaohao = $("#kaohao").val();
	var name = $("#name").val();
	
	if(kaohao){
		if(isNaN(kaohao)){
			 $.messager.alert('提示','考号应为数字,请重新输入!','warning');
			 return;
		}
		conditon.examCardNo =kaohao;
	}	
	
	if(name){
		//conditon.name = encodeURIComponent(name);
		conditon.name = name;
	}
	return conditon;
	
}

function onkeydowncondition(){
	var event=arguments.callee.caller.arguments[0]||window.event;
	if (event.keyCode == 13) 
    {      
		$("#datagrid_xsc_d").datagrid("reload",getConditions());
    }
}

</script>
<div id="div" class="easyui-layout" data-options="fit:true,border:false">
	<div id="centerdiv" data-options="region:'center'" title="学生信息">
		<table id="datagrid_xsc_d"></table>
			</div>
	 <div id="menuToolbar" style="padding:5px;height:auto">
		 <table>
		 	<tr>
           			<td>
					 &nbsp;考号：<input type="text" id="kaohao" onkeydown="onkeydowncondition()"></input>
					 &nbsp;姓名：<input type="text" id="name" onkeydown="onkeydowncondition()"></input>
		 		</td>
		 		<td>
		 			 &nbsp; <a href="#" id="searchlink" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		 			 &nbsp; <a href="#" id="returnlink" class="easyui-linkbutton" iconCls="icon-redo">返回</a>
		 		</td>
		 	</tr>
		 </table>
	</div>
</div>
