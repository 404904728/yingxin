<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

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
	action="${ctx}/entrancehandel/updateStudent" method="post">
				<input type="hidden" name="id" value="${eh.id}"> 
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
			<th width="10%"><i>*</i>&nbsp;学生姓名</th>
			<td width="23%"><input type="text" value="${eh.name}" name="name"
				class="easyui-validatebox" data-options="required:true"
				/></td>
			<th width="10%"><i>*</i>&nbsp;性别</th>
			<td>
				<c:if test="${eh.sex == 1}">
					男<input type="radio" name="sex1" value="1" checked="checked"/>
					女<input type="radio" name="sex1" value="0"/>
				</c:if>
				<c:if test="${eh.sex == 0}">
					男<input type="radio" name="sex1" value="1"/>
					女<input type="radio" name="sex1" value="0" checked="checked"/>
				</c:if>
				<input type="hidden" name="sex" value="${eh.sex}">
			</td>
			<th width="10%"><i>*</i>&nbsp;出生日期</th>
			<td width="23%"><input type="text" name="birthday" value="${eh.birthday}" id="birthday"
				class="easyui-datebox textbox" data-options="required:true"
				/></td>
			
		</tr>
		<tr>
			<th width="10%"><i>*</i>&nbsp;身份证</th>
			<td width="23%"><input type="text" name="idCardNo" value="${eh.idCardNo}"
				class="easyui-validatebox" data-options="required:true"
				/></td>
			<th><i>*</i>&nbsp;籍贯</th>
			<td><input type="text" name="nativePlace"
				value="${eh.nativePlace}" class="easyui-validatebox"
				data-options="required:true" /></td>
			<th><i>*</i>&nbsp;现居住地址</th>
			<td><input type="text" id="address" name="address" value="${eh.address}"
				data-options="required:true"/></td>
		</tr>
		<tr>
			<th><i>*</i>&nbsp;监护人</th>
			<td><input type="text" id="father" name="guarDian" value="${eh.guarDian}"
				class="easyui-validatebox" data-options="required:true"
				/></td>
			<th><i>*</i>&nbsp;监护人电话</th>
			<td><input type="text" id="fatel" value="${eh.tel}" name="tel"
				class="easyui-validatebox" fathertel="required:true"
				/></td>
			<th><i>*</i>&nbsp;报到时间</th>
			<td><input type="text" id="createTime" value="${eh.createTime}" name="createTime"
				class="easyui-datebox textbox" fathertel="required:true"
				/></td>
		</tr>
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
			<th width="10%"><i>*</i>&nbsp;是否教师子女</th>
			<td width="23%"><select name="isTeacherChild" id="isTeacherChild"
				style="width: 200px;">
				<c:choose>
					<c:when test="${eh.isTeacherChild == 0}">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
					</c:when>
					<c:otherwise>
						<option value="0">否</option>
						<option value="1" selected="selected">是</option>
					</c:otherwise>
				</c:choose>
			</select></td>
			<th width="10%"><i>*</i>&nbsp;奖学金等级</th>
			<td width="23%"><select name="scholarShip" id="scholarShip" data-options="required:true"
				style="width: 200px;">
					<option value="0">无</option>
					<option value="1">一等奖学金</option>
					<option value="2">二等奖学金</option>
					<option value="3">三等奖学金</option>
					<option value="4">四等奖学金</option>
			</select></td>
			<script>
				var scVal = "${eh.scholarShip}";
				if(scVal){
					$("#scholarShip").find("option[value='"+scVal+"']").attr("selected",true);
				}
			</script>
			<th width="10%"><i>*</i>&nbsp;入学年份</th>
			<td width="23%"><input type="text" id="year" value="${eh.year}" name="year"
				class="easyui-validatebox" data-options="required:true"
				/></td>
		</tr>
	</table>
</form>
<div style="margin-top: 5px; text-align: right; padding: 50px;">
	<input type="button" value="修改完成" id="submitButton"
		onclick="ehupdateFormFormSubmit()"
		style="width: 80px; height: 30px" />
</div>
