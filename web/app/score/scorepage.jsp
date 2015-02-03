<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript" src="${ctx}/res/script_/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
	loaddata(null);
	$("#year").val(new Date().getFullYear().toString()+"年");
});

function loaddata(url){
	var basepath = '${ctx}/scoremange/findScoreData?l=1';
	var stage = $("input[name='stage']:checked").val();
	if(!url){
		url = basepath + '&stage='+stage;
	}else{
		url= basepath+url+ '&stage='+stage;
	}
	$.hmqAJAX('${ctx}/scoremange/findScoreHeader?stage='+stage,function(data){
		$('#dg').datagrid({
		    columns:[data],
		    url:url,
		    pagination:true,
		    fit:true,
		    singleSelect:true,
		    rownumbers:true,
		    fitColumns:true
		   // onDblClickRow: sendOneRecord
		});
	});
}

function searchscore(){
	loaddata(getUrl());
}

function getUrl(){
	var url = '';
	var kaohao = $("#kaohao").val();
	var stage = $("input[name='stage']:checked").val();
	var name = $("#name").val();
	var totalscore = $("#totalscore").val();
	var year = $("#year").val();
	
	if(kaohao){
		if(isNaN(kaohao)){
			 $.messager.alert('提示','考号应为数字,请重新输入!','warning');
			 return;
		}
		url +='&sno='+kaohao;
	}
	
		if(name){
			url +='&sName='+encodeURIComponent(name);
		}
	
	if(totalscore){
		if(isNaN(totalscore)){
			 $.messager.alert('提示','分数应为数字,请重新输入!','warning');
			 return;
		}
		url +='&totalScore='+totalscore;
	}
	
	if(year){
		var re = /^(2\d{3})年$/;
		var rs=re.exec(year);
		if(rs && rs.length == 2){
			year = rs[1];
		}else{
			 $.messager.alert('提示','入学年份输入有误,请重新输入(如2014年)!','warning');
			 return;
		}
	}else{
		year = new Date().getFullYear();
	}
	url +='&importDate='+year;
	return url;
	
}

function onkeydowncondition(){
	var event=arguments.callee.caller.arguments[0]||window.event;
	if (event.keyCode == 13) 
    {      
		searchscore();
    }
}

function saveTemplate(){
	var conent1 = $("#conent1").val();
	var conent2 = $("#conent2").val();
	var url = '${ctx}/scoremange/saveTemplate?l=1';
	if(conent1 || conent2){
		if(conent1){
			url += '&conent1='+encodeURIComponent(conent1);
		}
		if(conent2){
			url += '&conent2='+encodeURIComponent(conent2);
		}
		
		$.hmqAJAX(url,function(data){
			if(data &&　'1' == data){
				  $.messager.alert('提示','保存模板成功!','info');
			}else{
				  $.messager.alert('提示','保存模板失败!','error');
			}
		});
	}else{
		 $.messager.alert('提示','请输入模板内容','warning');
	}
}

function sendnotice(){
	var total = $('#dg').datagrid('getData').total;
	if(total && total > 0){
		 $.messager.confirm('确认', '您确定通过'+$("input[name='noticetype']:checked + span").text()+'的方式发送'+total+'条记录吗?', function(r){
	         if (r){
				var type = $("input[name='noticetype']:checked").val();
	         	var basepath = '${ctx}/scoremange/sendMsgByCondtion?type='+type;
	        	var stage = $("input[name='stage']:checked").val();
	        		basepath += '&stage='+stage +getUrl();
	        	$.hmqAJAX(basepath,function(data){
	        		if(data && data == '1'){
	  				  $.messager.alert('提示','发送成功!','info');
	        		}else{
	  				  $.messager.alert('提示','发送失败!','error');
	    			}
	        	});
	         }
	     });
	}else{
		  $.messager.alert('提示','没有数据需要发送!','info');
	}
}

function sendOneRecord(rowIndex, rowData){
    	alert(rowData.sno +" " + rowData.totalScore);
}

</script>    
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'west',split:true" title="信息查询" style="width:350px;">
		<form id="seachform" method="post">
            <table cellpadding="10">
                <tr>
                    <td>考号:</td>
                    <td><input type="text" id="kaohao" onkeydown="onkeydowncondition()"></input></td>
                </tr>
                <tr>
                    <td>阶段:</td>
                    <td>
                    	<input onclick="javascript:searchscore()" type="radio" name="stage" value="1">小学</input>
	                    <input onclick="javascript:searchscore()" type="radio" name="stage" checked="checked" value="2">初中</input>
	                    <input onclick="javascript:searchscore()" type="radio" name="stage" value="3">高中</input>
                    </td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input  type="text" id="name" onkeydown="onkeydowncondition()"></input></td>
                </tr>
                <tr>
                    <td> 总分(包含):</td>
                    <td><input  type="text" id="totalscore" onkeydown="onkeydowncondition()"></input></td>
                </tr>
                <tr>
                    <td>入学年份:</td>
                   <td>
                   <input id="year" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy年',autoPickDate:true})">
                   </td>
                </tr>
                <tr>
                	<td></td>
                    <td>
                      <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
                      <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a> -->
                  	 <input type="button" value="查询" style="margin-right: 5px" onclick="searchscore()"></input>
                  	  <input type="reset" value="重置"></input>
                    </td>
                
                </tr>
            </table>
        </form>
        <hr color="#daeef5"/>
        <div id="p" class="easyui-panel" title="短信通知" 
        style="padding:10px;background:#fafafa;"
        data-options="collapsible:true,collapsed:true,fit:true">
        	 <table cellpadding="10">
			   <tr>
                    <td>通知模板:</td>
                    <td><textarea id="conent1" draggable="false" rows="4" cols="25">${type1}</textarea></td>
                </tr>
                <tr>
                    <td>录取模板:</td>
                    <td><textarea id="conent2" draggable="false" rows="4" cols="25">${type2}</textarea></td>
                </tr>
                <tr>
                    <td>类型:</td>
                    <td>
	                    <input type="radio" name="noticetype" checked="checked" value="1"><span>通知短信</span></input>
                    	<input type="radio" name="noticetype" value="2"><span>录取短信</span></input>
                    </td>
                </tr>
                <tr>
                	<td></td>
                    <td>
                  	  <input type="button" value="保存模板" style="margin-right: 5px" onclick="saveTemplate()"></input>
                  	  <input type="button" value="发送通知" onclick="sendnotice()"></input>
                    </td>
                
                </tr>
            </table>
		</div>
	 </div>
  	<div data-options="region:'center',title:'成绩信息',iconCls:'icon-ok'">
		<table id="dg"></table>	
  	</div>
</div>