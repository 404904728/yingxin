<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
function onlineregistFormFormSubmit(){
	
	
	var ndnj = $.trim($("#ndnj").val());
	var datastage = $.trim($("#datastage").val());
	var examCardNo = $.trim($("#examCardNo").val());
	var testyear = $.trim($("#year").val());
	var createTime = $.trim($("#createTime").datebox('getValue'))
	if(null == ndnj || "" == ndnj || null == datastage || "" == datastage){
		Easy.hmqDialogEror("学生数据为空,不能报到");
		return;
	}
	if(null == examCardNo || "" == examCardNo){
		Easy.hmqDialogEror("准考证号不能为空");
		return;
	}
	if(null == createTime || "" == createTime){
		Easy.hmqDialogEror("报到时间不能为空");
		return;
	}
	if(null == testyear || "" == testyear){
		Easy.hmqDialogEror("入学年份不能为空,填写内容为学生入学年,如：2014");
		return;
	}
	/* var reg = /^[19|20]{1}\d{2}$/;
	if(!reg.test(testyear)){
		Easy.hmqDialogEror("入学年份不正确!);
		return;
	}
	return; */
	var result = $("#onlineregistForm").serialize();
	if($("#jianhuren option:selected").val() == '无' || $("#dianhua option:selected").val() == '无'){
		if(confirm("监护人与监护人电话为空,是否确定提交")){
			$("#submitButton").attr({"disabled":"disabled"});
			subResl(examCardNo,result);
		}else{
			return;
		}
	}
	$("#submitButton").attr({"disabled":"disabled"});
	subResl(examCardNo,result);	
	//Easy.hmqDialogEror("报到完成");
	//document.getElementById('onlineregistForm').reset();
}

function subResl(examCardNo,result){
	$.ajax({
        url: "${ctx}/entrancehandel/isexisstu/"+examCardNo,
        cache: false,
        dataType: "json",
        type: "POST",
        timeout: 600000,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function(data) {
        	if(data == 0 || data == '0'){
        		Easy.hmqDialogEror("该学生已经进行过报名操作,无需重复操作!");
        		document.getElementById('onlineregistForm').reset();
        		$("#submitButton").removeAttr("disabled");
        		return;
        	}else if(data == 2 || data == '2'){
        		Easy.hmqDialogEror("准考证号异常,请重新操作");
        		document.getElementById('onlineregistForm').reset();
        		$("#submitButton").removeAttr("disabled");
        		return; //
        	}else if(data == 1 || data == '1'){
        		$.ajax({
        	        url: "${ctx}/entrancehandel/startbaodao?"+result,
        	        cache: false,
        	        dataType: "json",
        	        type: "POST",
        	        timeout: 600000,
        	        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        	        success: function(data) {
        	        	if(data == "1" || data == 1){
        	        		Easy.hmqDialogInfo("报到成功,请前往财务缴费!");
        	        		document.getElementById('onlineregistForm').reset();
        	        		$("#submitButton").removeAttr("disabled");
        	        	}else{
        	        		Easy.hmqDialogEror("数据异常,请联系管理员");
        	        		document.getElementById('onlineregistForm').reset();
        	        		$("#submitButton").removeAttr("disabled");
        	        		return;
        	        	}
        	        }
        	 	});
        	}
        	//console.log(data);
       }
 });
}
</script>


<br>
<input class="easyui-searchbox"
	data-options="prompt:'请先输入准考证号回车进行查询...',searcher:doSearch"
	style="width: 500px; height: 30px;"></input>
<script>
       function doSearch(value){
    	   //var event=arguments.callee.caller.arguments[0]||window.event;
    		   if("" == value || null == value){
    	       		Easy.hmqDialogEror("准考证号不能为空");
    	       		return;
    	       	}else{
    	       		$.ajax({
    	      		        url: "${ctx}/entrancehandel/querystuinfo/"+encodeURIComponent(value),
    	      		        cache: false,
    	      		        dataType: "json",
    	      		        type: "POST",
    	      		        timeout: 600000,
    	      		        contentType: "application/x-www-form-urlencoded;charset=utf-8",
    	      		        success: function(data) {
    	      		        	if(data.flg == 0){
    	      		        		Easy.hmqDialogEror("没有找到对应的学生,请重新查找");
    	      		        		return;
    	      		        	}else if(data.flg == 1){
    	      		        		console.info(data);
    	      		        		$("#ndnj").val(data.grand);
    	      		        		$("#datastage").val(data.datagrand);
    	      		        		$("#examCardNo").val(data.examNo);
    	      		        		$("#totalscore").val(data.totalScore);
    	      		        		$("#order").val(data.order);
    	      		        		$("#hege").val(data.hege);
    	      		        		$("#name").val(data.name);
    	      		        		$("#sex").val(data.sex);
    	      		        		$("#datasex").val(data.datasex);
    	      		        		$("#nat").val(data.nation);
    	      		        		$("#idCardNo").val(data.idCard);
    	      		        		$("#birthday").val(data.bir);
    	      		        		$("#palce").val(data.place);
    	      		        		$("#addd").val(data.address);
    	      		        		$("#sch").val(data.School);
    	      		        		$("#rea").val(data.rea);
    	      		        		$("#father").val(data.fa);
    	      		        		$("#fatherwork").val(data.fawo);
    	      		        		$("#fatel").val(data.faTel);
    	      		        		$("#mother").val(data.ma);
    	      		        		$("#motherwork").val(data.mawo);
    	      		        		$("#mothertel").val(data.maTel);
    	      		        		$("#interest").val(data.inter);
    	      		        		$("#award").val(data.aw);
    	      		        		$("#jianhuren").empty();
    	      		        		$("#dianhua").empty();
    	      		        		$("#jianhuren").append(data.gradVal);
    	      		        		$("#dianhua").append(data.telVal);
    	      		        	}
    	      		        	//console.log(data);
    	      		       }
    	      		 });				        		
    	       	}
       }
			</script>

<br>
<br>
<form id="onlineregistForm"
	action="${ctx}/onlineregist/data/saveorupdate" method="post">
				<input type="hidden" name="id" value=""> 
				<input type="hidden" id="datasex" name="sex" value=""> 
				<input type="hidden" id="datastage" name="stage" value="">
	<table class="form">
		<tr>
			<th width="10%">拟读年级</th>
			<td width="23%"><input type="text" value="" id="ndnj"
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>准考证号</th>
			<td colspan="3"><input type="text" id="examCardNo" name="examCardNo"
				value="" class="easyui-validatebox"
				data-options="required:false" readonly="readonly" /></td>
		</tr>
		<tr>
			<th width="10%">总成绩</th>
			<td width="23%"><input type="text" id="totalscore" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th width="10%">考试排名</th>
			<td width="23%"><input type="text" value="" id="order"
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>

			<th width="10%">是否符合报到资格</th>
			<td width="23%"><input type="text" id="hege" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th width="10%">姓名</th>
			<td width="23%"><input type="text" id="name" name="name" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th width="10%">性别</th>
			<td width="23%"><input type="text" id="sex" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th width="10%">民族</th>
			<td width="23%"><input type="text" id="nat" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>身份证</th>
			<td><input type="text" name="idCardNo" id="idCardNo"
				value="" class="easyui-validatebox"
				data-options="required:false" readonly="readonly" /></td>
			<th>出生年月</th>
			<td><input type="text" id="birthday" name="birthday" value=""
				data-options="required:false" readonly="readonly" /></td>
			<th>户口所在地</th>
			<td><input type="text" id="palce" name="nativePlace" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>现住址</th>
			<td><input type="text" id="addd" name="address" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>现就读学校</th>
			<td><input type="text" id="sch" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>报读我校原因</th>
			<td><input type="text" id="rea" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>父亲姓名</th>
			<td><input type="text" id="father"  value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>父亲工作单位</th>
			<td><input type="text" id="fatherwork" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>父亲电话</th>
			<td><input type="text" id="fatel" value="" 
				class="easyui-validatebox" fathertel="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>母亲姓名</th>
			<td><input type="text" id="mother" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>母亲工作单位</th>
			<td><input type="text" id="motherwork" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
			<th>母亲电话</th>
			<td><input type="text" id="mothertel" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>兴趣爱好</th>
			<td colspan="5"><input type="text" id="interest" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<th>已获奖情况</th>
			<td colspan="5"><input type="text" id="award" value=""
				class="easyui-validatebox" data-options="required:false"
				readonly="readonly" /></td>
		</tr>
		<%-- <tr>
			<th>报读我校原因</th>
			<td colspan="5"><input type="text" name="reason" value="喜欢"
				class="easyui-validatebox" data-options="required:false" readonly="readonly"/></td>
			 <th>信息来源</th>
			<td>亲友介绍:<input type="radio" name="origin"
				${regist.origin==0?'checked':''} /> 老师推荐:<input type="radio"
				name="origin" ${regist.origin==1?'checked':''} /> 慕名报读:<input
				type="radio" name="origin" ${regist.origin==2?'checked':''} /></td>
			<th>填报时间</th>
			<td><input type="text" name="date" value="${regist.date}"
				class="easyui-datebox" data-options="required:false" /></td>
			</td>
		</tr> --%>
	</table>
	<br> <br>
	<table class="form">
		<tr>
			<th width="10%"><i>*</i>&nbsp;是否教师子女</th>
			<td width="23%"><select name="isTeacherChild" id="queryStudentNation"
				style="width: 200px;">
					<option value="0">否</option>
					<option value="1">是</option>
			</select></td>
			<th width="10%"><i>*</i>&nbsp;奖学金等级</th>
			<td width="23%"><select name="scholarShip" id="queryStudentNation"
				style="width: 200px;">
					<option value="0">无奖学金</option>
					<option value="1">一等奖学金</option>
					<option value="2">二等奖学金</option>
					<option value="3">三等奖学金</option>
					<option value="4">四等奖学金</option>
			</select></td>
			<th width="10%"><i>*</i>&nbsp;报到时间</th>
			<td width="23%"><input id="createTime" type="text" value="" name="createTime"
				class="easyui-datebox textbox" /> <script>
					var date=new Date();
					$('#createTime').val(date.getFullYear()+"-"+eval(date.getMonth()+1)+"-"+date.getDate());
				</script></td>
		</tr>
		<tr>
			<th width="10%"><i>*</i>&nbsp;入学年份</th>
			<td width="23%"><input type="text" id="year" value="" name="year"
				class="easyui-validatebox" data-options="required:false"
				/></td>
			<script>
				var date=new Date();
				$('#year').val(date.getFullYear());
			</script>
			<th width="10%"><i>*</i>&nbsp;监护人</th>
			<td width="23%"><select name="guarDian" id="jianhuren"
				style="width: 200px;">
				
			</select></td>
			<th width="10%"><i>*</i>&nbsp;监护人电话</th>
			<td width="23%"><select name="tel" id="dianhua"
				style="width: 200px;">
				
			</select></td>
		</tr>
	</table>
</form>
<div style="margin-top: 5px; text-align: right; padding: 50px;">
	<input type="button" value="报到完成" id="submitButton"
		onclick="onlineregistFormFormSubmit()"
		style="width: 80px; height: 30px" />
</div>
