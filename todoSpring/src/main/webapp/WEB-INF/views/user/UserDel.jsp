<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODOLIST</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>
<link rel="stylesheet" href="../css/theme.css">
<link rel="stylesheet" href="../css/index.css">
<style>
.ho{
max-width:300px;
margin:auto;

}
</style>
<body>
<div class="container4">
	<div class="starter-template">
		<font color="red">${error}</font>
		<font color="blue">${msg}</font>
			<h1 class="ha">정말 탈퇴하시겠습니까? ;(</h1>
					<h2>탈퇴하려면 비밀번호를 입력하세요.</h2>
					<form action="/user/del/do" method="post">  
					<p>
					<div class="ho">
					<input type='password' required="required" 
				placeholder="비밀번호 입력" class="form-control floating-label" name="password" maxlength="20" pattern=".{2,20}"/>
				</div></p>
				<p><input type='submit' name="탈퇴" class="btn btn-danger" value="탈퇴"/>
			<a href="/"><input type="button" value="돌아가기" class="btn btn-info"></a></p>
				</form>
      </div>
    </div><!-- /.container -->
</body>
</html>