<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="h" uri="/hmq-tags"%>
<script type="text/javascript">
$(function() {
   $("#accordion").accordion({
	   fit:true,
	   onSelect:function(title,index){
		   var tree=$("#accordion").accordion("getSelected").find("ul");
		   if(tree.attr("load")=="true")return;
		   tree.attr("load","true")
		   tree.tree({
			   url: '../menu/findData?pId='+ tree.attr("id"),
		       onClick:function(node) {
		           if (!$.isEmpty(node.attributes)) {
		               if (node.attributes.lastIndexOf("html") > 0) {
		                   window.open(node.attributes);
		               } else {
		                   if (node.attributes.indexOf("form") > 0) {
		                       window.open(node.attributes);
		                   }else{
		                   		$("#homeLayout").layout("panel", "center").panel("refresh",node.attributes);
		                   }
		               }
		           }
		       } 
		   })
	   }
   })
})
</script>
<div id="accordion">
	<c:forEach items="${menus}" var="menu">
		<div title="${menu.text}" data-options="iconCls:'${menu.iconCls}'">  
       		<ul id="${menu.id}" ></ul>
    	</div> 
	</c:forEach>
</div>



