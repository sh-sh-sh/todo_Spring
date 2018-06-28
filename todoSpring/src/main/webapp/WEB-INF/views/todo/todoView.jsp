<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.study.model.TodoVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODO</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="./../css/bootstrap.min.css">
<script src="./../js/bootstrap.min.js"></script>
<script src="./../js/jquery.min.js"></script>
<link rel="stylesheet" href="./../css/theme.css">
<link rel="stylesheet" href="./../css/index.css">
<style>
.wrap{
margin:auto;
max-width:400px;
}
</style>
<body>
<div class="container2">
	<div class="starter-template">
	<%
		TodoVO todo=(TodoVO)request.getAttribute("todo");
	%>
			<h2><span class="label label-default"><%=todo.getCate_name()%></span> <%=todo.getTitle()%></h2>
			<font color="red">${error}</font>
			<font color="blue">${msg}</font>
			<p>
			<% String done;
				if(todo.isDone()){done="완료";
				}else{if(todo.getPast()){
						done="기한 지남";
					}else{
						done="미완료";
					}}%>
				<%=done%>
				<%if(!todo.isDone()){ %>
				<a href="/todo/done?idx=<%=todo.getIdx()%>&done=1"><input type="button" value="완료하기" class="btn btn-xs btn-success"></a>
				<%}else{ %>
				<a href="/todo/done?idx=<%=todo.getIdx()%>&done=0"><input type="button" value="되돌리기" class="btn btn-xs btn-warning"></a>
				<%} %>
				
			</p>
	<div class="wrap">
		<table class="table table-striped">
			<tr>
				<td>내용 : </td><td><%=todo.getContent()%></td>
			</tr>
			<tr>
				<td nowrap>시작일 : </td><td><%=todo.getStart_date()%></td>
			</tr>
			<tr>
				<td>기한일 : </td><td><%=todo.getTarget_date()%></td>
			</tr>
			<tr>
				<td>생성일 : </td><td><%=todo.getCreate_date()%></td>
			</tr>
			
		</table>
		<p>
		<a href="/todo/list?page=1&view=all"><input type="button" value="목록" class="btn btn-sm btn-warning"></a>
		<a href="/todo/edit?idx=<%=todo.getIdx()%>"><input type="button" value="수정" class="btn btn-sm btn-info"></a>
			<a href="/todo/del?idx=<%=todo.getIdx()%>"><input type="button" value="삭제" class="btn btn-sm btn-danger"></a></p>
		</div>

      </div>
    </div><!-- /.container -->
</body>
</html>