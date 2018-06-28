<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀 입력</title>
</head>
<link rel="stylesheet" href="./css/bootstrap.css">
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.min.js"></script>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">TODOLIST</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
			<%if(session.getAttribute("userid")==null){%>
			<li><a href="/login">로그인</a></li>
			<%}else{%>
			<li><a href="/user/Profile">프로필</a></li>
			<li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">할일 보기<span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="/todo/list?page=1&view=all">전체 보기</a></li>
	            <li><a href="/todo/list?page=1&view=today">오늘의 할일</a></li>
	            <li><a href="/todo/list?page=1&view=week">이번주 할일</a></li>
	            <li><a href="/todo/list?page=1&view=month">이번달 할일</a></li>
	            <li class="divider"></li>
	            <li><a href="/todo/list?page=1&view=undone">미완료한 할일</a></li>
	            <li class="divider"></li>
	            <li><a href="/todo/list?page=1&view=done">완료한 할일</a></li>
	          </ul>
	        </li>
			<li><a href="/todo/add">할일 추가</a></li>
			<li><a href="/user/logout">로그아웃</a></li>
			<%} %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
</body>
</html>