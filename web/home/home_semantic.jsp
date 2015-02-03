<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>成都七中实验学校-迎新系统</title>
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<script src="${ctx}/res/script_/hmq/jquery-2.1.1.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/res/semantic/packaged/css/semantic.min.css" />
<script src="${ctx}/res/semantic/packaged/javascript/semantic.js"></script>
<script src="${ctx}/res/semantic/plugin/accordion.js"></script>
<script src="${ctx}/res/script_/hmq/hmq.js"></script>
<script type="text/javascript">
	$(function() {
		var $accordion = $('.ui.accordion');
		$accordion.accordion();
		$(".menu").find("a").click(function() {
			$(".menu").find("a").removeClass("active");
			$(this).addClass("active")
		})
		$(".menu .dropdown").dropdown({
			on : 'hover'
		});
		$("#homelogout").on('click', function() {
			$('body').transition({
				animation : 'slide down',
				duration : '0.5s',
				complete : function() {
					$.logout();
				}
			})
		})
	})
</script>
</head>
<body>
	<div class="ui grid">
		<div class="two column mobile only row">
			<div class="column">
				<div class="ui segment">移动设备</div>
			</div>
			<div class="column">
				<div class="ui segment">移动设备</div>
			</div>
		</div>
		<div class="three column row">
			<div class="ten wide computer only column">
				<div class="ui segment">计算机设备</div>
			</div>
			<div class="ten wide tablet mobile only column">
				<div class="ui segment">移动设备和平板设备</div>
			</div>
			<div class="four wide column">
				<div class="ui segment">所有</div>
			</div>
			<div class="two wide column">
				<div class="ui segment">所有</div>
			</div>
		</div>
		<div class="four column computer only row">
			<div class="column">
				<div class="ui segment">计算机设备</div>
			</div>
			<div class="column">
				<div class="ui segment">计算机设备</div>
			</div>
			<div class="column">
				<div class="ui segment">计算机设备</div>
			</div>
			<div class="column">
				<div class="ui segment">计算机设备</div>
			</div>
		</div>
		<div class="three column tablet only row">
			<div class="column">
				<div class="ui segment">平板设备</div>
			</div>
			<div class="column">
				<div class="ui segment">平板设备</div>
			</div>
			<div class="column">
				<div class="ui segment">平板设备</div>
			</div>
		</div>
	</div>
	<div class="ui blue secondary pointing menu">
		<a class="active item"> <i class="home icon"></i> Home
		</a> <a class="item"> <i class="mail icon"></i> Messages
		</a> <a class="item"> <i class="user icon"></i> Friends
		</a>
		<div class="right menu">
			<a id="homelogout" class="ui item"> Logout </a>
		</div>
	</div>
	<div class="ui  vertical  menu">
		<a class="item"> <i class="home icon"></i> Home
		</a> <a class="item"> <i class="grid layout icon"></i> Browse
		</a> <a class="item"> <i class="mail icon"></i> Messages
		</a>
		<div class="ui dropdown item">
			More <i class="dropdown icon"></i>
			<div class="menu">
				<a class="item"><i class="edit icon"></i> Edit Profile</a> <a
					class="item"><i class="globe icon"></i> Choose Language</a> <a
					class="item"><i class="settings icon"></i> Account Settings</a>
			</div>
		</div>
	</div>
</body>
</html>