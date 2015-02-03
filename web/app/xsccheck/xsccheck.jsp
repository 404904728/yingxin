<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/res/script_/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var currentYear = new Date().getFullYear();
var stage = null;
var year = null;
var status = null;


$(function(){
	$("#year").val(currentYear+"年");	
})

$("#searchlink").click(function(){
	$("#datagrid_jwc").datagrid("reload",getConditions());
});

$("#oklink").click(function(){
	if(status && status == 0){
		$.hmqAJAX('${ctx}/xsccheck/updateStatus',function(data){
			if(data && data == 1){
				$("#datagrid_jwc").datagrid("reload",getConditions());
				$.messager.alert('提示','保存成功!','info');
			}
		},{stage:stage,year:year})
	}else{
		$.messager.alert('提示','请选择数据!','info');
	}
});

$('#stage').combobox({
	onSelect: function(record){
		$("#datagrid_jwc").datagrid("reload",getConditions());
	}
});

function getConditions(){
	var conditon = {};
	var stage = $('#stage').combobox('getValue');
	var kaohao = $("#kaohao").val();
	var name = $("#name").val();
	var year = $("#year").val();
	
	if(stage){
		conditon.stage = stage;	
	}
	
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
	
	if(year){
		var re = /^(2\d{3})年$/;
		var rs=re.exec(year);
		if(rs && rs.length == 2){
			conditon.year = rs[1];
		}else{
			 $.messager.alert('提示','入学年份输入有误,请重新输入(如2014年)!','warning');
			 return;
		}
	}else{
		conditon.year = currentYear;
	}
	return conditon;
}

function onkeydowncondition(){
	var event=arguments.callee.caller.arguments[0]||window.event;
	if (event.keyCode == 13) 
    {      
		$("#datagrid_jwc").datagrid("reload",getConditions());
    }
}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center'">
		<table id="datagrid_jwc" class="easyui-datagrid" title="学生信息"
			 data-options="singleSelect:true,pagination:true,
			 	 fit:true,toolbar:'#menuToolbar',rownumbers:true,
			 	 url:'${ctx}/xsccheck/findata',onDblClickRow:function(rowIndex, rowData){
	  			  refreshhomepage('${ctx}/xsccheck/detail?stage='+rowData.stage+'&year='+rowData.year);
			},onSelect:function(rowIndex, rowData){
	  			 year = rowData.year;
	  			 stage = rowData.stage;
	  			 status = rowData.status;
			}">
			<thead>
				<tr>
					<th data-options="field:'stage',width:'300',
						formatter:function(value){
							return value==1?'小学':(value==2?'初中':'高中');
						}">阶段</th>
					<th data-options="field:'year',width:'300'">入学年份</th>
					<th data-options="field:'weidao',width:'250'">未到人数</th>
					<th data-options="field:'duodao',width:'250'">多到人数</th>
					<th data-options="field:'status',width:'200',
						formatter:function(value){
							return value == 0?'未确认':'已确认';
						}">确认情况</th>
				</tr>
			</thead>
		</table>
		 <div id="menuToolbar" style="padding:5px;height:auto">
		 <table>
		 	<tr>
		 	<td align="right">阶段：</td>
		 		<td>
		 				   <select id="stage" class="easyui-combobox" panelHeight="auto" style="width:100px;border-bottom: 18px">
			                <option value="">请选择</option>
			                <option value="1">小学</option>
			                <option value="2">初中</option>
			                <option value="3">高中</option>
           			 </select> </td>
           			 <td>
					 &nbsp;入学年份：<input id="year" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy年',autoPickDate:true})">
		 		</td>
		 		<td>
		 			 &nbsp; <a href="#" id="searchlink" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		 			 &nbsp; <a href="#" id="oklink" class="easyui-linkbutton" iconCls="icon-ok">确认</a>
		 		</td>
		 	</tr>
		 </table>
		 </div>
	</div>
</div>
