<%@page language="java" pageEncoding="utf-8"%>
<%@page import="com.mchange.v2.c3p0.PooledDataSource"%>
<%@page import="core.cq.hmq.dao.util.BeanUtil"%>
<meta http-equiv="Expires" Content="0" />
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" href="${ctx}/res/css/form/form.css" />
<%
Runtime r = Runtime.getRuntime();
Object ds = BeanUtil.getBean("dataSource");
PooledDataSource ps=null;
if(ds instanceof PooledDataSource){
	ps=(PooledDataSource)ds;
}
%>
<h3>系统状态</h3>
<table class="form">
	<tr>
		<th width="20%">空闲内存</th>
		<td width="30%"><%=r.freeMemory() / 1024 / 1024%>M</td>
		<th width="20%">最大内存</th>
		<td width="30%"><%=r.maxMemory() / 1024 / 1024%>M</td>
	</tr>
	<tr>
		<th>总内存</th>
		<td><%=r.totalMemory() / 1024 / 1024%>M</td>
		<th>可用处理器</th>
		<td><%=r.availableProcessors()%></td>
	</tr>
	<%if(ps!=null){ %><tr>
		<th>数据库总连接数</th>
		<td><%=ps.getNumConnectionsAllUsers()%></td>
		<th>数据库连接数</th>
		<td><%=ps.getNumConnectionsDefaultUser()%></td>
	</tr>
	<tr>
		<th>未释放连接数</th>
		<td><%=ps.getNumBusyConnectionsDefaultUser()%></td>
		<th>空闲连接数</th>
		<td><%=ps.getNumIdleConnectionsDefaultUser()%></td>
		
	</tr><%}else{%>
	<tr><td colspan="4">非c3p0连接池，无法监控到数据库连接情况</td></tr>
	<%} %>
</table>