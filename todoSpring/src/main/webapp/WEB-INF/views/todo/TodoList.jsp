<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.study.model.TodoVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TODOLIST</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/footable.min.js"></script>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/theme.css">
<link rel="stylesheet" href="../../css/index.css">
<link rel="stylesheet" href="../../css/footable.bootstrap.min.css" />
<style>
.pageidx{padding: 5px;}
div {text-align:center}
.midd{
max-width:400px;
margin:10px auto 0 auto;
text-align: center;
}
.haha{
margin:auto;
max-width:600px;
}
</style>
<%
List<TodoVO> list=(List<TodoVO>)request.getAttribute("list");
String view=request.getParameter("view"); 
%>
<body>
<div class="container3">
	<div class="starter-template">
		<h1><%=(String)(request.getAttribute("title"))%></h1>
		<font color="red">${error}</font>
		<font color="blue">${msg}</font>
	<%
		if(!view.equals("done")&&!view.equals("undone")){
	%>
		<div class="haha">
		<div class="progress">
  <div class="progress-bar progress-bar-striped active" role="progressbar" style="width:${doneRate}%">
    <span class="sr-only"></span>
  </div>
  </div>
</div>
<%
	}
%> 
		<div class="haha">
		<table class="table table-bordered">
			<tr class="cols123">
				<th>분류</th><th class="footable-first-visible">제목</th>
				<th class="tha" data-breakpoints="xs">시작일</th>
				<th class="tha" data-breakpoints="xs">종료일</th><th width=46px>달성</th>
				<th data-breakpoints="xs">버튼</th>
			</tr>
			<%
				for(int i=0;i<list.size();i++){
					 TodoVO todo=list.get(i);
					 if(todo.getTitle().length()>10){
						 todo.setTitle(todo.getTitle().substring(0, 9)+"…");
					 }
			%>
			<tr class="row123">
				<td><span class="label label-default"><%=todo.getCateName()%></span></td><td><a href="Todo.do?idx=<%=todo.getIdx()%>"><%=todo.getTitle()%></a></td>
				<td><%=todo.getStart_date_listver()%></td><td><%=todo.getTarget_date_listver()%></td>
				<% String done;
					if(todo.isDone()){
						done="O";
					}else{
						if(todo.getPast()){
							done="지남";
						}else{
							done="X";
						}
					}
				%>
				<td><%=done %></td>
				<td>
				<%if(!todo.isDone()){ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=1"><input type="button" value="완료" class="btn btn-xs btn-success"></a>
				<%}else{ %>
				<a href="TodoDone.do?idx=<%=todo.getIdx()%>&done=0"><input type="button" value="취소" class="btn btn-xs btn-warning"></a>
				<%} %>
				&nbsp;&nbsp;<a href="TodoMod.do?idx=<%=todo.getIdx()%>"><input type="button" value="수정" class="btn btn-xs btn-info"></a>
				&nbsp;&nbsp;<a href="TodoDel.do?idx=<%=todo.getIdx()%>"><input type="button" value="삭제" class="btn btn-xs btn-danger"></a></td>
			</tr>
			<%} %>
		</table>
		</div>
<nav aria-label="Page navigation">
  <ul class="pagination justify-content-center pagination-sm">
<% 
int a=Integer.parseInt(request.getParameter("page"));
int maxpage=(Integer)(request.getAttribute("maxpage"));
int pagestart=1+((a-1)/10)*10;
if(pagestart!=1){%>
    <li class="page-item">
      <a class="page-link" href="/TodoList.do?page=<%=pagestart-1%>&view=<%=view%>" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a></li>
	<%}
for(int i=pagestart;i<pagestart+10&&i<=maxpage;i++){ 
	if(a==i){%>
    <li class="page-item active"><a class="page-link" href="/TodoList.do?page=<%=i%>&view=<%=view%>"><%=i%><span class="sr-only">(current)</span></a></li>
    <%}else{ %>
    <li class="page-item"><a class="page-link" href="/TodoList.do?page=<%=i%>&view=<%=view%>"><%=i%></a></li>
<%}} 
if(pagestart+10<=maxpage){%>
    <li class="page-item">
      <a class="page-link" href="/TodoList.do?page=<%=pagestart+10%>&view=<%=view%>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a></li>
<%} %>
  </ul>
</nav>
<br>
      </div>
    </div><!-- /.container -->
    <script type="text/javascript">
    jQuery(function($){
    	$('.table').footable({
    		"cascade": true
    	});
    });
    </script>
</body>
</html>