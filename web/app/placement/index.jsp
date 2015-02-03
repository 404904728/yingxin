<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function placement(){
	$("#homeWin").window("setTitle","规则设置");
	$('#homeWin').window('open').window('refresh','${ctx}/placement/page/rule');
	return;
	Easy.hmqConfirm("提示","您确定要为今天的学生进行分班操作吗?",function(){
		$.hmqAJAX("${ctx}/placement/divideintoclasses",function(data){
			alert(data.type);
		})
	})
}
</script>
<div id="tt" class="easyui-tabs" data-options="fit:true">
   <div title="本届分班" data-options="iconCls:'icon-fbcl-small'">
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
								 <a href="#" onclick="queryUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">分班</a>
								 <a href="#" onclick="$('#registqueryform')[0].reset()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div data-options="region:'center'">
				<table id="datagrid_register" class="easyui-datagrid"
					 data-options="
					 	 singleSelect:true,pagination:true,
					 	 fit:true,rownumbers:true,showFooter: true,
					 	 url:'${ctx}/placement/data/find'
				     ">
					<thead>
						<tr>
							<th data-options="field:'e.id_f',width:'80',hidden:true">ID</th>
							<th data-options="field:'e.name_f',width:'80',sortable:true">姓名</th>
							<th data-options="field:'e.birthday_f',width:'80',sortable:true">出生年月</th>
							<th data-options="field:'e.sex_f',width:'50',sortable:true,
								formatter:function(value){
									return value==0?'女':(value==1?'男':'');
								}">性别</th>
							<!-- <th data-options="field:'e.idCardNo_f',width:'150',sortable:true">身份证</th> -->
							<th data-options="field:'e.examCardNo_f',width:'100',sortable:true,
								formatter:function(value){
									if($.isEmpty(value))return '';
									return '<font color=blue>'+value+'</font>';
								}">准考证号</th>
							<th data-options="field:'e.createTime_f',width:'100',sortable:true">填报时间</th>
							<th data-options="field:'e.stage_f',width:'100',sortable:true,
								formatter:function(value){
									if($.isEmpty(value)){
										return '';
									}
									return value==1?'小学':(value==2?'初中':'高中');
								}">拟读年级</th>
							<th data-options="field:'e.isFenBan_f',width:'100',sortable:true,
								formatter:function(value){
								return value==0?'<font color=red>未分班</font>':(value==1?'<font color=blue>已分班</font>':'');	
							}">是否分班</th>
							<th data-options="field:'s.totalScore_f',width:'100',sortable:true">入学总成绩</th>
							<th data-options="field:'s.gradeOrder_f',width:'100',sortable:true">入学成绩排名</th>
						</tr>
					</thead>
				</table>
			</div>
   		</div>
   	</div>
   <div title="班级查询" data-options="iconCls:'icon-fbcx-small',href:'http://www.baidu.com'">
       tab2
   </div>
</div>