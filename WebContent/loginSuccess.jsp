<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Soar Like an Eagle 販售各式包包">
<meta name="keywords" content="Soar Like an Eagle 波士頓包 郵差包 腰包 肩背包 托特包">
<title>Soar Like an Eagle</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	var interval;

	function startSecond() {
		interval = window.setInterval("changeSecond()", 1000);

	}

	function changeSecond() {
		var second = document.getElementById("second");

		var svalue = second.innerHTML;

		svalue = svalue - 1;

		if (svalue == 0) {
			window.clearInterval(interval);
			location.href = "index.jsp";
			return;
		}

		second.innerHTML = svalue;
	}
</script>
</head>
<body onload="startSecond()">
	<%@include file="header.jsp"%>

	<div class="container" style="margin-top:50px">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">歡迎回來：${user.name }</h3>
					</div>
					<div class="panel-body">
						<img src="${pageContext.request.contextPath}/images/ok.png"
							width="26" height="26"><span id="second">5</span>秒後自動為您跳回首頁。
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>