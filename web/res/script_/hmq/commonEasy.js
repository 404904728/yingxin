/** 引入easyUI */
document.write(writeScript("easyui-1.3.6/jquery.min.js"));
//document.write(writeStyle("script_/easyui-1.3.2/themes/ui-cupertino/easyui.css"));
document.write(writeStyle("script_/easyui-1.3.6/themes/icon.css'"));
document.write(writeStyle("css/icon/easy-icons/easy-icon.css"));
//document.write(writeStyle("css/awesome/css/font-awesome.min.css"));
//document.write(writeStyle("css/awesome/css/color-awesome.css"));
document.write(writeScript("easyui-1.3.6/jquery.easyui.min.js"));
document.write(writeScript("easyui-1.3.6/locale/easyui-lang-zh_CN.js"));
document.write(writeScript("hmq/easyUtil.js"));
document.write(writeScript("hmq/hmq.js"));
document.write(writeStyle("css/form/form.css"));
document.close();
function writeScript(path) {
	return "<script type='text/javascript' src='../res/script_/" + path
			+ "'></script>";
}
function writeStyle(path) {
	return "<link rel='stylesheet' type='text/css' href='../res/" + path
			+ "'/>"
}