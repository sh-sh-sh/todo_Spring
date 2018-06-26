<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>가입 완료</title>
<jsp:include page="link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.min.js"></script>
<link rel="stylesheet" href="./css/theme.css">
<link rel="stylesheet" href="./css/index.css">
<body>
<div class="container4">
	<div class="starter-template">
			<h1>${name}님, <br>가입하신 것을 환영합니다!</h1>
					<p><a href="index.jsp"><input type="button" value="홈" class="btn btn-sm btn-Primary"></a>
					<a href="login.jsp"><input type="button" value="로그인" class="btn btn-sm btn-info"></a></p>
      </div>
    </div><!-- /.container -->
</body>
</html>