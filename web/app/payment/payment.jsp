<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/res/script_/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var currentYear = new Date().getFullYear();
$(function(){
	$("#year").val(currentYear+"年");	
})

$("#searchlink").click(function(){
	$("#datagrid_pay").datagrid("reload",getConditions());
});

$('#datagrid_pay').datagrid({singleSelect:true,pagination:true,
	 fit:true,toolbar:'#menuToolbar',rownumbers:true,
 	 url:'${ctx}/payment/findata',
	onDblClickRow: function(rowIndex, rowData){
		var ps = '';
		if(rowData.paySum){
			ps= rowData.paySum;
		}
		$.messager.confirm('缴费', '您确定<b style="color: red;">'+rowData.name+'('+rowData.examCardNo+')</b>已缴费,<br>且金额为:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style="width:190px" value="'+ps+'" id="paymentsum"/>', function(r){
            if (r){
            	var r = $("#paymentsum").val();
        		if(isNaN(r)){
       			  alert('金额应为数字,请重新输入!');
       			}else{
       				$.hmqAJAX('${ctx}/payment/updatePaySum',function(data){
       					if(data  && 1 == data){
       						alert("保存成功,缴费金额为:"+r+"元");
       						$("#datagrid_pay").datagrid("reload",getConditions());
       					}else{
       						alert('保存失败!');
       					}
       				},{id:rowData.id,sum:r});
       			}
            }
        });
	}
});

$('#stage').combobox({
	onSelect: function(record){
		$("#datagrid_pay").datagrid("reload",getConditions());
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
		$("#datagrid_pay").datagrid("reload",getConditions());
    }
}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center'">
		<table id="datagrid_pay" title="学生信息"
			 >
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true,width:'80'">ID</th>
					<th data-options="field:'stage',width:'100',
						formatter:function(value){
							return value==1?'小学':(value==2?'初中':'高中');
						}">阶段</th>
					<th data-options="field:'examCardNo',width:'120'">考号</th>
					<th data-options="field:'name',width:'80'">姓名</th>
					<th data-options="field:'sex',width:'80',
						formatter:function(value){
							return value==0?'女':'男';
						}">性别</th>
					<th data-options="field:'birthday',width:'100'">出生年月</th>
					<th data-options="field:'idCardNo',width:'150'">身份证</th>
					<!-- <th data-options="field:'nation',width:'150',
						formatter:function(value){
							return value==null?'':value.name;
						}">民族</th>
					<th data-options="field:'nativePlace',width:'150'">籍贯</th>
					<th data-options="field:'address',width:'150'">现住址</th> -->
					<!-- <th data-options="field:'school',width:'100'">现就读学校</th> -->
					<th data-options="field:'guarDian',width:'100'">监护人姓名</th>
					<th data-options="field:'tel',width:'120'">监护人电话</th>
					<!-- <th data-options="field:'createTime',width:'150'">填报时间</th> -->
					<th data-options="field:'isTeacherChild',width:'100',
						formatter:function(value){
							return value==0?'否':'是';
						}">教师子女</th>
					<th data-options="field:'scholarShip',width:'120',
						formatter:function(value){
							return value==0?'无':(value==1?'一等奖学金':(value==2?'二等奖学金':(value==3?'三等奖学金':(value==4?'四等奖学金':'五等奖学金'))));
						}">奖学金</th>
					<th data-options="field:'specialApprove',width:'120'">特批情况</th>
					<th data-options="field:'year',width:'100'">入学年份</th>
					<th data-options="field:'paySum',width:'100'">已缴金额</th>
					<th data-options="field:'isPay',width:'100',
						formatter:function(value){
							return value==0?'否':'是';
						}">是否缴费</th>
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
           			 <b style="color: red;"></b>
					 &nbsp;考号：<input type="text" id="kaohao" onkeydown="onkeydowncondition()"></input>
					 &nbsp;姓名：<input type="text" id="name" onkeydown="onkeydowncondition()"></input>
					 &nbsp;入学年份：<input id="year" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy年',autoPickDate:true})">
		 		</td>
		 		<td>
		 			 &nbsp; <a href="#" id="searchlink" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		 		</td>
		 	</tr>
		 </table>
		 </div>
	</div>
</div>
