<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'east',split:true" title="" style="width:500px">
		<form id="registqueryform" action="">
			<table class="easyform">
				<tr>
					<th width="20%">入学年级</th>
					<td width="30%">
						 初中<input type="radio" value="2"/>
						 高中<input type="radio" value="3"/>
					</td>
					<th width="20%">总班数</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0" data-options="min:0,max:20,required:true"></input>
					</td>
				</tr>
				<tr>
					<th width="20%">火箭班</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0" data-options="min:0,max:2,required:true"></input>
					</td>
					<th width="20%">分数线</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0"></input>
					</td>
				</tr>
				<tr>
					<th width="20%">尖子班</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0" data-options="min:0,max:2,required:true"></input>
					</td>
					<th width="20%">分数线</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0"></input>
					</td>
				</tr>
				<tr>
					<th width="20%">普通班</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0" data-options="min:0,max:2,required:true"></input>
					</td>
					<th width="20%">分数线</th>
					<td width="30%">
						 <input class="easyui-numberspinner" value="0"></input>
					</td>
				</tr>
				<tr>
					<th>操作</th>
					<td colspan="3">
						 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						 <a href="#" onclick="$('#registqueryform')[0].reset()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'" style="padding: 10px;">
		jQuery EasyUI framework help you build your web page easily.
		
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right; padding: 5px 0 0;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:alert('ok')" style="width: 80px">确定</a> 
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:alert('cancel')" style="width: 80px">取消</a>
	</div>
</div>
