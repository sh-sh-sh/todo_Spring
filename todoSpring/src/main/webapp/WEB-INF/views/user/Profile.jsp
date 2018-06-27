<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.study.model.UserVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROFILE</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/jquery.min.js"></script>
<link rel="stylesheet" href="../../css/theme.css">
<link rel="stylesheet" href="../../css/index.css">
<style>
.wrap{
margin:auto;
max-width:300px;
}
.haha{
margin:auto;
max-width:300px;
}
</style>
<body>
<div class="container2">
	<div class="starter-template">
	<%
		UserVO user=(UserVO)request.getAttribute("user");
		int TodoNum=(Integer)request.getAttribute("TodoNum");
		int DoneNum=(Integer)request.getAttribute("DoneNum");
	%>
			<h2><%=user.getName()%></h2>
			<font color="red">${error}</font>
			<font color="blue">${msg}</font>
			<%if(TodoNum!=0){ %>
				<div class="haha">
					<div class="progress">
					  <div class="progress-bar progress-bar-striped active" role="progressbar" style="width: 
					  <%= DoneNum*100/TodoNum%>%">
					    <span class="sr-only"></span>
					  </div>
					  </div>
					</div>
					<%} %>
	<div class="wrap">
		<table class="table table-striped">
			<tr>
				<td>메일 : </td><td><%=user.getEmail()%></td>
			</tr>
			<tr>
				<td nowrap>등록한 할일 수 : </td><td><%=TodoNum%></td>
			</tr>
			<tr>
				<td>완료한 할일 수 : </td><td><%=DoneNum%></td>
			</tr>

		</table>
		<p>
		<a href="/user/edit"><input type="button" value="수정" class="btn btn-sm btn-info"></a>
			<a href="/user/del"><input type="button" value="탈퇴" class="btn btn-sm btn-danger"></a></p>
		</div>

      </div>
    </div><!-- /.container -->
</body>
</html>