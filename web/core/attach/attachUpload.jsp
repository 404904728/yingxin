<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/res/uploadify/uploadify.css"/>
<script type="text/javascript" src="${ctx}/res/uploadify/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/res/script_/hmq/hmq.js"></script>
<script type="text/javascript" src="${ctx}/res/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
#attachList {
    background-color: #FFF;
    border-radius: 3px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.25);
    height:400px;
    margin-bottom: 10px;
    overflow: auto;
    padding: 5px 10px;
    width: 300px;
}
</style>
<script type="text/javascript">
$(function(){
	$("#file_upload").uploadify({
		height:40,width:100,//debug:true,
		swf:'${ctx}/res/uploadify/uploadify.swf',
		hideButton:true,
		//buttonImage:'./res/script_/easyui-1.3.2/themes/icons/attach.png',
		fileSizeLimit:hmq.fileSizeLimit,
		uploader:'${ctx}/uploadify/upload',
		//uploader:${ctx}+'uploadify/uploadVoice.htm',
	    //buttonText:'添加',
		queueID:'attachList',
		removeCompleted:false,
		onUploadSuccess:function(file,data,response){
			//var d=eval("("+data+")");
			//window.console.log(file.name+"————"+d.msg);
		}
	});
});
</script>
</head>
<body>
<div style="padding: 20px">
	<input id="file_upload" type="file" multiple="true">
	<div id="attachList"></div>
</div>
</body>
</html>
