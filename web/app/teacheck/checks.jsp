<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<script type="text/javascript" src="${ctx}/res/script_/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/easyui-1.3.6/locale/jquery.form.js"></script>
<script type="text/javascript">
var orgid = "${orgid}";
var orgname = "${orgname}";
/** 已到 2  还是 未到 1 */
var flag = null;
var stuId=null;

/** 预定义按钮 */
var del = {
        text:'删除',
        handler:function(){
        var id = $('#id').val();
        if(id){
             $.hmqAJAX('${ctx}/teacheck/deleteduodao?tcId='+id,function(data){
                 if(data && 1 == data){
                 		alert('删除成功!');
                	 	$('#id').val(null);
                    	$('#addduodaoform')[0].reset();  
                    	 $('#addduodaodiv').dialog('close');
                	 	$.hmqAJAX('${ctx}/teacheck/findUnStudent?orgid='+orgid+'&orgname='+orgname,function(data){
                	 		$('#datagrid_duodao').datagrid({data:data.duodao});
                	 		$('#centerdiv').panel('setTitle',data.title); 
                	 	});
                 }
            });
        }else{
        	alert('该数据尚未保存,不能删除!');
        }
        }
    };

var save ={
        text:'保存',
        iconCls:'icon-ok',
        handler:function(){
           $('#addduodaoform').formAjax(function(data){
           if(data && 1 == data){
                	 $('#addduodaodiv').dialog('close');
                	 alert('操作成功!');
                	 $('#id').val(null);
                	 $('#addduodaoform')[0].reset();  
            	 	$.hmqAJAX('${ctx}/teacheck/findUnStudent?orgid='+orgid+'&orgname='+orgname,function(data){
            	 		$('#datagrid_duodao').datagrid({data:data.duodao});
            	 		$('#centerdiv').panel('setTitle',data.title); 
            	 	});
            	 }else{
            	 	 alert('操作失败!');
            	 }
           });
        }
    };


var cancel = {
        text:'取消',
        handler:function(){
         $('#id').val(null);
        $('#addduodaoform')[0].reset();  
            $('#addduodaodiv').dialog('close');
        }
    };


$(function(){
	$.hmqAJAX('${ctx}/teacheck/findUnStudent?orgid='+orgid+'&orgname='+orgname,function(data){
			$("#centerdiv").panel('setTitle',data.title); 
			$("#datagrid_check").datagrid({
			    url:'${ctx}/teacheck/findata?org='+orgid,
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
								]],
			    pagination:true,
			    fit:true,
			    singleSelect:true,
			    toolbar:'#menuToolbar',
			    rowStyler: function(index,row){
			    	if(data.weidao && data.weidao.length > 0){
			    		 if (data.weidao.indexOf(row.id) != -1){
			                    return 'background-color:#6293BB;color:#fff;font-weight:bold;';
			                }
			    	}
	            },
			    rownumbers:true,
			    fitColumns:true,
			    onClickRow: function(rowIndex, rowData){
			    	//console.info($("#datagrid_check").prev().find(".datagrid-body tr[datagrid-row-index='"+rowIndex+"']").attr('style'));
			    	stuId = rowData.id;
			    	if("bold" == $("#datagrid_check").prev().find(".datagrid-body tr[datagrid-row-index='"+rowIndex+"']").css("font-weight")){
			    		$('a#weidao').linkbutton({text:'已到'});
			    		flag = 2;
			    	}else{
			    		$('a#weidao').linkbutton({text:'未到'});
			    		flag = 1
			    	}
			    }
			});
			
			if(data && data.duodao){
				$("#datagrid_duodao").datagrid({
				    data:data.duodao,
				    columns:[[ {field:'id',hidden:true,width:1},
				             {field:'examcardno',title:'考号',width:140},{field:'name',title:'姓名',width:80},
							 {field:'orgname',title:'班级',width:140}]],
				    fit:true,
				    singleSelect:true,
				    rownumbers:true,
				    fitColumns:true,
				    onDblClickRow: function(rowIndex, rowData){
				    	var buttons = [];
				    	buttons[0] = save;
				    	buttons[1] = cancel;
				    	buttons[2] = del;
				      	$("#addduodaodiv").dialog({buttons:buttons});
				    	$("#addduodaodiv").dialog('open');
				    	$("input[name='id']").val(rowData.id);
				    	$("input[name='addName']").val(rowData.name);
				    	$("input[name='examcardNo']").val(rowData.examcardno);
				    }
				});
			}
	});
})

$("#searchlink").click(function(){
	$("#datagrid_check").datagrid("reload",getConditions());
});

$("#weidao").click(function(){
	$.hmqAJAX('${ctx}/teacheck/updateUnStatus?org.id='+orgid+'&stuId='+stuId+'&flag='+flag+'&org.type=${tCheck.org.type}',function(data){
		if(data && 1 == data){
		    alert("操作成功！");
		    refreshhomepage('${ctx}/teacheck/ppage');
		}
	});
	
});

$("#duodao").click(function(){
	if('none' == $("#westdiv").css("display")){
		$("#div").layout('expand','west');
	}
	var buttons = [];
	buttons[0] = save;
	buttons[1] = cancel;
  	$("#addduodaodiv").dialog({buttons:buttons});
	$("#addduodaodiv").dialog('open');
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
		$("#datagrid_check").datagrid("reload",getConditions());
    }
}

</script>
<div id="div" class="easyui-layout" data-options="fit:true,border:false">
	<div id="westdiv"  title="多到学生" data-options="region:'west',collapsed:true,split:true,collapsible:true" style="width: 361px">
			<table id="datagrid_duodao"></table>
	</div>
	<div id="centerdiv" data-options="region:'center'" title="学生信息">
		<table id="datagrid_check"></table>
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
		 			 &nbsp; <a href="#" id="weidao" class="easyui-linkbutton" iconCls="icon-back">未到</a>
		 			 &nbsp; <a href="#" id="duodao" class="easyui-linkbutton" iconCls="icon-add">多到</a>
		 		</td>
		 	</tr>
		 </table>
	</div>
	
	
	 <div id="addduodaodiv" class="easyui-dialog" title="添加多到学生" style="width:350px;height:200px;padding:10px"
            data-options="closed:true,
            	closable:false,
            	modal:true,
                iconCls: 'icon-save'
            ">
      <f:form id="addduodaoform" action="${ctx}/teacheck/saveduodao" method="post" commandName="tCheck">
        <table cellpadding="5">
	        	<f:hidden path="org.id"/>
	        	<f:hidden path="org.type"/>
	        	<f:hidden path="id"/>
                <tr>
                    <td>姓名:</td>
                    <td><f:input path="addName"></f:input></td>
                </tr>
                <tr>
                    <td>考号:</td>
                    <td><f:input path="examcardNo"></f:input></td>
                </tr>
         </table>
         </f:form>
    </div>
	
</div>
