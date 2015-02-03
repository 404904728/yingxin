<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">



</script>
<div id="tabsll" class="easyui-tabs" data-options="fit:true">    
    <div title="入学办理">
        <jsp:include page="entrancemanage.jsp"></jsp:include>
    </div>
    <div title="报到学生查询" data-options="href:'${ctx}/entrancehandel/querynation'">
    </div>
    <!-- <div title="信息修改">
        No contact data.
    </div>   -->  
</div>    
