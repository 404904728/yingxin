<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#attachGrid").datagrid({
		url:'${ctx}/attach/finddata',
		pagination:true,rownumbers:true,
		//singleSelect:true,fitColumns:true,
		fit:true,toolbar:'#attachToolbar',
		pagerBtns:[{
			iconCls:'icon-add',
			handler:function(){alert('add')}
		},'-',{
			iconCls:'icon-save',
			handler:function(){alert('save')}
		}],
		onDblClickRow:function(rowIndex,rowData){
			var docType=$.isOffice(rowData.fileName);
			if(docType!=null){
				window.open("attach.htm?officeView&id="+rowData.id+"&docType='"+docType+"'","文档预览");
			}else{
				Easy.addTabFrame("attachTab","附件查阅","attach.htm?attachUpLoad");
			}
			
		},
		columns:[[
					{field:'suffix',title:'附件类型',width:100,align:'center'},  
					{field:'fileName',title:'附件名称',sortable:true,width:200,align:'center'},  
					{field:'sizeView',title:'附件大小',sortable:true,width:100,align:'center'},
					{field:'date',title:'上传日期',sortable:true,width:200,align:'center'},
					{field:'user',title:'上传用户',sortable:true,width:200,align:'center'}
		]],
		toolbar:[{  
            text:'附件上传',  
            iconCls:'icon-upload-small',  
            handler:function(){
            	hmq.openWindow("../attach/uploadpage","600px","400px");
            }  
        },{  
            text:'附件删除',  
            iconCls:'icon-cut',  
            handler:function(){
	        	var selectRow=$("#attachGrid").easyselect();
	        	if(selectRow){
	        		$.hmqAJAX("../attach/delete",function(data){
						Easy.hmqDialog(data,function(){
							$("#attachGrid").datagrid("load");
						})
		        	},{"id":selectRow.id});
	            }else{
					Easy.hmqDialogEror("请先选择要删除的记录");
	            }
            }  
        },'-',{  
            text:'修改描述',  
            iconCls:'icon-edit',  
            handler:function(){
            }  
        },'-',{  
            text:'下载附件',  
            iconCls:'icon-edit',  
            handler:function(){
        		var selectRow=$("#attachGrid").easyselect();
            	if(selectRow){
            		window.open(selectRow.htmlUrl);
                }else{
					Easy.hmqDialogEror("请先选择要查看的记录");
                }
            	
            }  
        },'-',{  
            text:'webOffice',  
            iconCls:'icon-edit',  
            handler:function(){
        		var selectRow=$("#attachGrid").datagrid("getSelected");
            	if(selectRow){
            		window.open("${ctx}/attach.htm?officeView_&id="+selectRow.id+"&docType='"+selectRow.suffix+"'");
                }else{
					Easy.hmqDialogEror("请先选择要查看的记录");
                }
            	
            }  
        }]
	});
})
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="查询" style="width:250px;padding: 5px 2px 0 2px">
	</div>
	<div data-options="region:'center'">
		<table id="attachGrid" title="附件信息"></table>
		<div id="attachToolbar" style="padding:5px;height:auto">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>|
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		 </div>
	</div>
</div>