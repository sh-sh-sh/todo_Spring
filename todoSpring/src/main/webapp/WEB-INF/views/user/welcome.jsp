<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>환영합니다</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>
<link rel="stylesheet" href="../css/theme.css">
<link rel="stylesheet" href="../css/index.css">
<body>
<div class="container2">
	<div class="starter-template">
			<h1 class="h122">${name}님, 환영합니다!</h1>
					<p><a href="/todo/list?page=1&view=all"><input type="button" value="할일 관리" class="btn btn btn-Primary"></a>
					<a href="/todo/add"><input type="button" value="할일 추가" class="btn btn btn-info"></a></p>
      </div>
    </div><!-- /.container -->
</body>
</html>