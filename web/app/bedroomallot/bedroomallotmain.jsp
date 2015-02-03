<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
        $('#tt').tree({
            checkbox: false,
            onBeforeExpand: function(node){
            	var m = node.id.split(",");
              	$('#tt').tree('options').url = '${ctx}/bedroomallot/treevaluemen?id='+m[0];
              	$("#bedroominfo").val("");
            },
            onClick:function(node){
            	var tt = node.id.split(",");
            	if(tt[1] == '3' || tt[1] == 3){
	            	queryRoomMessage(tt[0]);
	            	$("#bedroominfo").val("");
            	}else{
            		$("#bedroominfo").val("");
            	}
    	    }
        });
        $('#dd').tree({
            checkbox: false,
            onBeforeExpand: function(node){
            	var m = node.id.split(",");
              	$('#dd').tree('options').url = '${ctx}/bedroomallot/treevaluewomen?id='+m[0];
              	$("#bedroominfo").val("");
            },
            onClick:function(node){
            	var m = node.id.split(",");
            	if(m[1] == '3' || m[1] == 3){
	            	queryRoomMessage(m[0]);
	            	$("#bedroominfo").val("");
            	}else{
            		$("#bedroominfo").val("");
            	}
    	    }
        });
        $("#jieduan").combobox({
	        onHidePanel:function(){
	        	var jieduanvalue = $('#jieduan').combobox('getValue');
	        	$('#grand').combobox('clear');
				$('#grand').combobox('reload','${ctx}/bedroomallot/findgrand?id='+jieduanvalue);
			}
        });
        $("#grand").combobox({
	        onHidePanel:function(){
	        	var jieduanvalue = $('#grand').combobox('getValue');
	        	$('#classes').combobox('clear');
				$('#classes').combobox('reload','${ctx}/bedroomallot/findgrand?id='+jieduanvalue);
			}
        });
        
});
//点击具体一间寝室后查询出其对应的详细信息
function queryRoomMessage(id){
	$.ajax({
        url: "${ctx}/bedroomallot/queryroominfo?id="+id,
        cache: false,
        dataType: "json",
        type: "POST",
        timeout: 600000,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function(data) {
        	if(data.flg == "1" || data.flg == 1){
        		var bed = data.data;
        		$("#bedroomname").val(bed.parent.parent.name + ' ' + bed.parent.name + ' ' + bed.name);
        		$("#livesum").val(bed.liveSum);
        		$("#alreadylive").val(bed.alreadyLive);
        		if(bed.gender == 1){
	        		$("#stukinds").val('男生寝室');
        		}else{
	        		$("#stukinds").val('女生寝室');
        		}
        		$("#liveteacher").val(bed.lifeTeacher);
        		$("#bedroominfo").val(bed.liveSum + "," + bed.alreadyLive + "," + bed.gender + "," + bed.id);
        	}else{
        		Easy.hmqDialogEror("数据异常,请联系管理员");
        		//document.getElementById('onlineregistForm').reset();
        		return;
        	}
        }
 	});
}
//获取选中的行
function Getcheckbox(flg) {
	var classid = $('#classes').combobox('getValue');
	var sex = $('#sex').combobox('getValue');
	if(!classid){
		$.messager.alert('提示','未选择班级,请选择班级进行学生查询','warning');
		return;
	}
	if(!sex){
		$.messager.alert('提示','未选择学生性别,请选择性别进行学生查询','warning');
		return;
	}
	if(flg == 1 || flg == '1'){
		if(sex == 1){
			var node = $('#tt').tree('getSelected');
			if(node){
				var bedinfo = node.id.split(",");
				if(bedinfo[1] == 1 || bedinfo[1] == '1'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+1+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'tt');
				}else if(bedinfo[1] == 2 || bedinfo[1] == '2'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+2+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'tt');
				}else if(bedinfo[1] == 3 || bedinfo[1] == '3'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+3+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'tt');
				}
			}else{
				$.messager.confirm('提示','您未选择对应男生的寝室楼,系统将为您自动分配寝室,是否确定操作',function(r){
				    if (r){
				    	// 4:代表没有选楼,系统自动选择
				    	var url = 'bedroomallot/autosavebedroominfo?roomId='+0+'&roomtype='+4+'&classId='+classid+'&gender='+ sex;
				    	autoallotbedroom(url,'tt');
				    }
				});
				//autoallotbedroom(url);
			}
		}else if(sex == 0){
			var node = $('#dd').tree('getSelected');
			if(node){
				var bedinfo = node.id.split(",");
				if(bedinfo[1] == 1 || bedinfo[1] == '1'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+1+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'dd');
				}else if(bedinfo[1] == 2 || bedinfo[1] == '2'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+2+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'dd');
				}else if(bedinfo[1] == 3 || bedinfo[1] == '3'){
					var url = 'bedroomallot/autosavebedroominfo?roomId='+bedinfo[0]+'&roomtype='+3+'&classId='+classid+'&gender='+ sex;
					autoallotbedroom(url,'dd');
				}
			}else{
				$.messager.confirm('提示','您未选择对应女生的寝室楼,系统将为您自动分配寝室,是否确定操作',function(r){
				    if (r){
				    	// 4:代表没有选楼,系统自动选择
				    	var url = 'bedroomallot/autosavebedroominfo?roomId='+0+'&roomtype='+4+'&classId='+classid+'&gender='+ sex;
				    	autoallotbedroom(url,'dd');
				    }
				});
			}
		}
	}else if(flg == 2 || flg == '2'){
		var rows = $('#studentinfo').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert('提示','未选择分配寝室的学生!','warning');
			return;
		}
		var bedflg = $("#bedroominfo").val();
		if(!bedflg){
			$.messager.alert('提示','未选择入住寝室!','warning');
			return;
		}
		var strs = new Array(); //定义一数组 
		strs = bedflg.split(","); //字符分割 0:居住总数 1：已住数量 2：性别+
		if(strs[2] != rows[0].sex){
			$.messager.alert('性别不符','入住寝室与学生性别不符性别不符!','warning');
			return;
		}
		if(strs[0] < rows.length){
			$.messager.alert('提示','选择的学生数('+ rows.length +'人)已经超出寝室的总人数!','warning');
			return;
		}
		if(strs[0] < (Number(strs[1]) + Number(rows.length))){
			$.messager.alert('提示','目前该寝室已经入住'+ strs[1] +'人,无法入住您选择的'+ rows.length +'人','warning');
			return;
		}
		//console.info(rows);
		var stuids = "";
	  	for(var i = 0; i < rows.length; i++){
	  		/* alert(rows[i].id);
	  		alert(rows[i].name); */
	  		stuids += rows[i].id + ",";
	  	}
	  	$.ajax({
	        url: "${ctx}/bedroomallot/savebedroominfo?roomId="+strs[3] + "&roomAlreadytLive=" + strs[1] + "&stuIds=" + stuids,
	        cache: false,
	        dataType: "json",
	        type: "POST",
	        timeout: 600000,
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",
	        success: function(data) {
	        	if(data.type == "1" || data.type == 1){
	        		Easy.hmqDialogInfo("分配寝室成功!");
	        		screenStudent();
	        		//$('#dd').tree('reload',$("#dd").target);
	        		var m = data.msg.split(",");
	        		if(m[2] == "1" || m[2] == 1){
		        		var node = $('#tt').tree('getSelected');
		        		if (node){
		        			$('#tt').tree('update', {
		        				target: node.target,
		        				text:m[3] + '(可住'+ m[0] +'人,已住'+ m[1] +'人)'
		        			});
		        		}
	        		}else{
	        			var node = $('#dd').tree('getSelected');
		        		if (node){
		        			$('#dd').tree('update', {
		        				target: node.target,
		        				text: m[3] + '(可住' + m[0] +'人,已住'+ m[1] +'人)'
		        			});
		        		}
	        		}
	        		$("#bedroominfo").val("");
	        	}else{
	        		Easy.hmqDialogEror("数据异常,请联系管理员");
	        		$("#bedroominfo").val("");
	        		//document.getElementById('onlineregistForm').reset();
	        		return;
	        	}
	        }
	 	});
	}
}
function autoallotbedroom(url,id){
	$.ajax({
        url: "${ctx}/"+url,
        cache: false,
        dataType: "json",
        type: "POST",
        timeout: 600000,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        success: function(data) {
        	if(data.flg == "1" || data.flg == 1){
        		alert(data.msg);
        		refreshhomepage('${ctx}/bedroomallot/main');
        	}else if(data.flg == "2" || data.flg == 2){
        		alert(data.msg);
        	}else{
        		alert(data.msg);
        		refreshhomepage('${ctx}/bedroomallot/main');
        	}
        }
 	});
}
//按照指定条件进行学生查询
function screenStudent(){
	var classid = $('#classes').combobox('getValue');
	var sex = $('#sex').combobox('getValue');
	if(!classid){
		$.messager.alert('提示','未选择班级!','warning');
		return;
	}
	if(!sex){
		$.messager.alert('提示','未选择学生性别!','warning');
		return;
	}
	//$('#maintable').datagrid({ url:"datagrid_data.php",queryParams:{pageNumber:pageNumber1,pageSize:pageSize1	},method:"post"});
	$('#studentinfo').datagrid(
			{url:"${ctx}/bedroomallot/finddata/data",queryParams:{classid:classid,sex:sex},method:"post"});
}
</script>
<div id="tabsll" class="easyui-tabs" data-options="fit:true">
	<div title="学生寝室">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'west',split:true" title="寝室数据"
				style="width: 300px; padding: 5px 2px 0 2px">
				<div class="easyui-accordion"
					data-options="fit:true,border:false,selected:true">
					<div title="男生寝室" style="padding: 10px; overflow: auto;">
						<ul id="tt" class="easyui-tree">
							<li state="closed" id='1'><span>成都实验七中</span></li>
						</ul>
					</div>
					<div title="女生寝室" style="padding: 10px;">
						<ul id="dd" class="easyui-tree">
							<li state="closed" id='0'><span>成都实验七中</span></li>
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center'">
				<div class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'north'" title="寝室详细信息"
						style="height: 120px; padding: 5px 2px 0 2px">
						<br>
						<input type="hidden" id="bedroominfo">
						<table class="form">
							<tr>
								<th width="10%">寝室名称</th>
								<td width="23%"><input type="text" value=""
									id="bedroomname" class="easyui-validatebox"
									data-options="required:false" readonly="readonly" /></td>
								<th>可住人数</th>
								<td><input type="text" id="livesum" value=""
									class="easyui-validatebox" data-options="required:false"
									readonly="readonly" /></td>
								<th width="10%">已住人数</th>
								<td width="23%"><input type="text" id="alreadylive"
									value="" class="easyui-validatebox"
									data-options="required:false" readonly="readonly" /></td>
							</tr>
							<tr>
								<th width="10%">学生类别</th>
								<td width="23%"><input type="text" id="stukinds" value=""
									class="easyui-validatebox" data-options="required:false"
									style="color: red;" readonly="readonly" /></td>
								<th width="10%">生活教师</th>
								<td width="23%"><input type="text" value=""
									id="liveteacher" class="easyui-validatebox"
									data-options="required:false" readonly="readonly" /></td>

								<th width="10%">寝室电话</th>
								<td width="23%"><input type="text" id="hege" value=""
									class="easyui-validatebox" data-options="required:false"
									readonly="readonly" /></td>
							</tr>
						</table>
					</div>
					
					<div data-options="region:'center'">
					    <table id="studentinfo" class="easyui-datagrid" 
					            title="学生信息"
					            data-options="rownumbers:true,
					            			  singleSelect:false,
					            			  pagination:true,
					            			  fit:true,
					            			  toolbar:'#menuToolbar'
					            			  ">
					        <thead>
					            <tr>
					            	<th data-options="field:'ck',checkbox:true"></th>
					            	<th data-options="field:'id',hidden:'true'">id</th>
					                <th data-options="field:'name',width:'80'">学生姓名</th>
					                <th data-options="field:'sex',width:'80',
										formatter:function(value){
											return value==0?'女':'男';
										}">性别</th>
					                <th data-options="field:'examCardNo',width:'80'">准考证号</th>
					                <th data-options="
					                	field:'idCardNo',
					                	width:'120'
					                	">身份证号码</th>
					                <!-- <th data-options="field:'tel',width:'80'">联系方式</th> -->
					                <th data-options="field:'guarDian',width:'80'">监护人</th>
					                <th data-options="field:'tel',width:'100'">监护人电话</th>
					                <th data-options="field:'isFenQS',width:'60',
											formatter:function(value){
												return value==0?'否':'是';
											}">寝室分配</th>
					                <th data-options="field:'year',width:'100'">入学年份</th>
					            </tr>
					        </thead>
					    </table>
					    <div id="menuToolbar" style="padding:5px;height:auto">
			            	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="Getcheckbox(1)">自动分配寝室</a>&nbsp;|
			           	 	<a href="#" class="easyui-linkbutton" onclick="Getcheckbox(2)" iconCls="icon-edit" plain="true">手动分配寝室</a>&nbsp;|&nbsp;
			            	<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
					 		 阶段:
				            <select class="easyui-combobox" id="jieduan" style="width:100px"
				            data-options="url:'${ctx}/bedroomallot/initjieduan',
				            valueField:'id',textField:'text',panelHeight:'auto',editable:false">
				            </select>&nbsp;
					 		 年级:
				            <select class="easyui-combobox" id="grand" style="width:100px" data-options="
							valueField:'id',textField:'text',panelHeight:'auto',editable:false">
				            </select>&nbsp;
					 		 班级:
				            <select class="easyui-combobox" id="classes" style="width:130px" 
				            data-options="
							valueField:'id',textField:'text',panelHeight:'auto',editable:false">
				            </select>&nbsp;
				                          性别:
				            <select class="easyui-combobox" id="sex" panelHeight="auto" editable="false" style="width:100px">
				                <option value="">--请选择--</option>
				                <option value="1">男</option>
				                <option value="0">女</option>
				            </select>&nbsp;
			           		<a href="#" onclick="screenStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">学生查询</a>&nbsp;
					 	</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div title="学生寝室查询" data-options="href:'${ctx}/bedroomallot/querynation'">
    </div>
</div>
