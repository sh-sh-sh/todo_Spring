<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODOLIST</title>
<jsp:include page="./link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.min.js"></script>
<link rel="stylesheet" href="./css/theme.css">
<link rel="stylesheet" href="./css/index.css">
<body>
<div class="container2">
	<div class="starter-template">
		<font color="red">${error}</font>
		<font color="blue">${msg}</font>
			<h1 class="ha">TODOLIST</h1>
					<p>할 일을 웹으로 관리하세요!</p>
      </div>
    </div><!-- /.container -->
</body>
</html>