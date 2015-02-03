<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	$("#qinshilou").combobox({
	    onHidePanel:function(){
	    	var jieduanvalue = $('#qinshilou').combobox('getValue');
	    	$('#bedno').combobox('clear');
	    	$('#louceng').combobox('clear');
			$('#louceng').combobox('reload','${ctx}/bedroomallot/initbedroom?roomType=2&roomId='+jieduanvalue);
		}
	});
	$("#louceng").combobox({
	    onHidePanel:function(){
	    	var jieduanvalue = $('#louceng').combobox('getValue');
	    	$('#bedno').combobox('clear');
			$('#bedno').combobox('reload','${ctx}/bedroomallot/initbedroom?roomType=3&roomId='+jieduanvalue);
		}
	});
})


function ehupdateFormFormSubmit() {
	$("#updateForm").formAjax(function(data) {
		Easy.hmqDialog(data, function() {
			$("#datagrid_register").datagrid("reload");
			$("#homeWin").window("close");
		})
	},true);
}

</script>


<h5><i style="color: red;">*&nbsp;号为可修改内容</i></h5>
<form id="updateForm"
	action="${ctx}/bedroomallot/updatebedroomto" method="post">
				<input type="hidden" name="stuId" value="${eh.id}"> 
				<input type="hidden" name="oldRoom" value="${oldroom}"> 
	<table class="form">
		<tr>
			<th width="10%">准考证号</th>
			<td width="23%"><input type="text"  name="examCardNo" value="${eh.examCardNo}"
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th width="10%">拟读年级</th>
			<td colspan="3"><input type="text" value="${eh.stage == 1?'小学':(eh.stage == 2 ? '小学':'高中')}"
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" />
				<input type="hidden" name="stage" value="${eh.stage}">
			</td>
				
			

		</tr>
		<tr>
			<th width="10%">学生姓名</th>
			<td width="23%"><input type="text" value="${eh.name}" name="name"
				class="easyui-validatebox" data-options="required:true" readonly="readonly"
				/></td>
			<th width="10%">性别</th>
			<td>
				<input type="text" value="${eh.sex == 0 ? '女' : '男'}" name="name"
				class="easyui-validatebox" data-options="required:true" readonly="readonly"
				/>
				<input type="hidden" name="sex" value="${eh.sex}">
			</td>
			<th width="10%">现住寝室</th>
			<td width="23%"><input type="text" name="idCardNo" value="${eh.idCardNo}"
				class="easyui-validatebox" data-options="required:true" readonly="readonly"
				/></td>
			<%-- <th width="10%">出生日期</th>
			<td width="23%"><input type="text" name="birthday" value="${eh.birthday}" id="birthday"
				readonly="readonly"
				/></td> --%>
			
		</tr>
		<tr>
			<th>监护人</th>
			<td><input type="text" id="father" name="guarDian" value="${eh.guarDian}"
				class="easyui-validatebox" data-options="required:true" readonly="readonly"
				/></td>
			<th>监护人电话</th>
			<td><input type="text" id="fatel" value="${eh.tel}" name="tel"
				class="easyui-validatebox" fathertel="required:true" readonly="readonly"
				/></td>
			<th>籍贯</th>
			<td><input type="text" name="nativePlace"
				value="${eh.nativePlace}" class="easyui-validatebox"
				data-options="required:true" readonly="readonly"/></td>
			<%-- <th>现居住地址</th>
			<td><input type="text" id="address" name="address" value="${eh.address}"
				data-options="required:true" readonly="readonly"/></td> --%>
		</tr>
			
			<%-- <th>报到时间</th>
			<td><input type="text" id="createTime" value="${eh.createTime}" name="createTime"
				 fathertel="required:true" readonly="readonly"
				/></td> --%>
		<!-- <tr>
			<th>已获奖情况</th>
			<td colspan="5"><input type="text" id="award" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr> -->
	</table>
	<br> <br>
	<table class="form">
		<tr>
			<th width="10%"><i>*</i>&nbsp;寝室楼</th>
			<td width="23%">
	            <select class="easyui-combobox" id="qinshilou" style="width:180px"
	            data-options="url:'${ctx}/bedroomallot/initbedroom?sex='+${eh.sex}+'&roomType=1',
	            valueField:'id',textField:'text',panelHeight:'auto',required:true,editable:false">
	            </select>&nbsp;
			</td>
			<th width="10%"><i>*</i>&nbsp;寝室楼层</th>
			<td width="23%">
				<select class="easyui-combobox" id="louceng" style="width:180px" data-options="
					valueField:'id',textField:'text',panelHeight:'auto',required:true,editable:false">
	            </select>&nbsp;
			</td>
			<!-- <script>
				var scVal = "${eh.scholarShip}";
				if(scVal){
					$("#scholarShip").find("option[value='"+scVal+"']").attr("selected",true);
				}
			</script> -->
			<th width="10%"><i>*</i>&nbsp;寝室</th>
			<td width="23%">
				<select class="easyui-combobox" id="bedno" name="bedNo" style="width:180px" data-options="
					valueField:'id',textField:'text',panelHeight:'auto',required:true,editable:false">
	            </select>&nbsp;
			</td>
		</tr>
	</table>
</form>
<div style="margin-top: 5px; text-align: right; padding: 50px;">
	<input type="button" value="修改完成" id="submitButton"
		onclick="ehupdateFormFormSubmit()"
		style="width: 80px; height: 30px" />
</div>
