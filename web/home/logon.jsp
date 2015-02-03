<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>迎新系统-登录</title>
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="${ctx}/res/semantic/packaged/css/semantic.min.css" />
<script src="${ctx}/res/script_/hmq/jquery-2.1.1.min.js"></script>
<script src="${ctx}/res/semantic/packaged/javascript/semantic.min.js"></script>
<script src="${ctx}/res/script_/hmq/hmq.js"></script>
<script type="text/javascript">
	$(function() {
		$("#logonform").form({
			no : {
				identifier : 'no',
				rules : [ {
					type : 'empty',
					prompt : '请输入用户名'
				} ]
			},
			pwd : {
				identifier : 'pwd',
				rules : [ {
					type : 'empty',
					prompt : '请输入密码'
				} ]
			}
		}, {
			inline : true,
			on : 'blur'
		})
	})
</script>
</head>
<body>
	<div class="two column doubling ui grid">
		<div class="two  column row">
			<div class="column">
				<div class="ui fluid form segment">
					<h3 class="ui header">用户登录</h3>
					<form id="logonform" action="${ctx}/home/login" method="post">
						<div class="field">
							<label>帐号</label>
							<div class="ui left labeled icon input">
								<input type="text" name="no" placeholder="用户名"> <i
									class="user icon"></i>
								<div class="ui corner label">
									<i class="icon asterisk"></i>
								</div>
							</div>
						</div>
						<div class="field">
							<label>密码</label>
							<div class="ui left labeled icon input">
								<input type="password" name="pwd" placeholder="密码"> <i
									class="lock icon"></i>
								<div class="ui corner label">
									<i class="icon asterisk"></i>
								</div>
							</div>
						</div>
						<div class="1 fluid ui buttons">
							<div onclick="logon(this)" class="ui blue button">
								<i class="sign in icon"></i> <span>登录</span>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="tablet computer only column">
				<div class="ui piled segment">
					<div class="ui ribbon label">迎新</div>
					<p>成都七中实验学校</p>
					<div class="ui teal ribbon label">功能强大</div>
					<p>可以在所有移动设备上访问</p>
					<div class="ui red ribbon label">最完美的系统</div>
					<p>能适应七中实验学校的迎新安排，财务活动</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function logon() {
			//$('#logondiv').transition('scale');
			$('#logonform').formAjax(function(data) {
				//$(#logonloder").removeClass("active");
				if (data.type == 0) {
					$('.ui.fluid.form.segment').transition({
						animation : 'scale',
						duration : '0.5s',
						complete : function() {
							window.open("${ctx}/home/index", "_self");
						}
					})
				} else {
					$('.ui.fluid.form.segment').transition('shake');
				}
			})
		}
	</script>
</body>
</html>
