<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#orgTree").tree({  
	    url:'${ctx}/user/findOrgUser',
	    onClick:function(node){
	    	var idtype=node.id.split(":")[0];
	    	if(idtype=="org"){
	    		//alert("部门"+node.id.split(":")[1])
		    }else if(idtype=="user"){
		    	//alert("用户"+node.id.split(":")[1])
			}
	    },onBeforeExpand:function(){
			//alert(1);
		 }
	});
})
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north'" style="height:100px">
		
	</div>
	<div data-options="region:'west',split:true" style="width:250px;">
		<ul id="orgTree"></ul>
	</div>
	<div data-options="region:'center'">
	</div>
</div>
