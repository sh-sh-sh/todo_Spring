<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.study.model.TodoVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀 입력</title>
<jsp:include page="../link.jsp" flush="false"/>
</head>
<script src="./../js/jquery.min.js"></script>
<script src="./../js/bootstrap.min.js"></script>
<script src="./../js/moment.js"></script>
<script src="./../js/bootstrap-material-datetimepicker.js"></script>
<link rel="stylesheet" href="./../css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="./../css/theme.css">
<link rel="stylesheet" href="./../css/index.css">
<link rel="stylesheet" href="./../css/bootstrap-material-datetimepicker.css" />
		
<style>
.content{
width:300px;
height:100px;
}
.wrap{	max-width: 300px;
margin:auto;}
</style>
<body>
<%
	TodoVO todo=(TodoVO)request.getAttribute("todo");
%>
<div class="container2">
	<div class="starter-template">
		<font color="red">${error}</font>
		<font color="blue">${msg}</font>
			<h1 class="ha">할 일 수정</h1>
		<form action="/todo/edit/do" method="post">  
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type='hidden' name='idx' value='<%=todo.getIdx()%>'>
		<div class="wrap">
			<table class="table table-hover">
				<tr>
					<td>시작일</td><td>
					<div class="inputcss">
					<input type="text" name="start_date" id="date-start" required="required"  
					class="form-control floating-label" placeholder="Start Date" value="<%=todo.getStart_date()%>">
					</div>
					</td>
				</tr>
				<tr>
					<td nowrap>기한/종료일</td><td>
					<div class="inputcss">
					<input type="text" name="target_date" id="date-end"  required="required" 
					class="form-control floating-label" placeholder="End Date" value="<%=todo.getTarget_date()%>">
					</div>
					</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td><Select name="category" style="width:150px" class="form-control floating-label" id="category">
						<option value="1">생활</option>
						<option value="2">학업</option>
						<option value="3">직장</option>
						<option value="4">연애</option>
						<option value="5">가족</option>
						<option value="6">기타</option>
					</Select></td>
					<script>
					$('#category').val("<%=todo.getCategory()%>").prop('selected',true);
					</script>
				</tr>
				<tr>
					<td>제목</td><td>
					<input type='text' name="title" class="form-control floating-label" 
					required="required" placeholder="1자 이상 20자 이하" value="<%=todo.getTitle()%>" 
					onKeyDown="checkNumber();" maxlength="20" pattern=".{1,20}" />
					</td>
				</tr>
				<tr>
					<td>내용</td><td><input type="text" name="content" class="form-control floating-label"  
					placeholder="입력하지 않으셔도 됩니다. 200글자 이하" value="<%=todo.getContent()%>" 
					onKeyDown="checkNumber();" maxlength="200" pattern=".{,200}"/></td>
				</tr>
				
			</table>
			<input type='submit' name="수정" class="btn btn-info" value="수정"/>
			<a href="/todo/del?idx=<%=todo.getIdx()%>"><input type="button" value="삭제" class="btn btn-danger"></a>
			<br>
			<br>
		</div><!-- wrap -->
		</form>
      </div>
    </div><!-- /.container -->
    		<script type="text/javascript">
		$(document).ready(function()
		{

			$('#date-end').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'YYYY-MM-DD HH:mm'
			});
			$('#date-start').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'YYYY-MM-DD HH:mm', shortTime : true
			}).on('change', function(e, date)
			{
				$('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
			});

			$('#min-date').bootstrapMaterialDatePicker({ format : 'YYYY-MM-DD HH:mm', minDate : new Date() });

		});
		
		function checkNumber()
		{
		 var objEv = event.srcElement;
		 var num ="|\"<>{}";    //입력을 막을 특수문자 기재.
		 event.returnValue = true;
		  
		 for (var i=0;i<objEv.value.length;i++)
		 {
		
		 if(-1 != num.indexOf(objEv.value.charAt(i)))
		 event.returnValue = false;
		 }
		  
		 if (!event.returnValue)
		 {
		  alert("해당 문자는 입력하실 수 없습니다.");
		  objEv.value="";
		 }
		}
		</script>
</body>
</html>