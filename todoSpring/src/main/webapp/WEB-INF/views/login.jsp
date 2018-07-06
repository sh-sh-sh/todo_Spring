<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<jsp:include page="link.jsp" flush="false"/>
</head>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.min.js"></script>
<link rel="stylesheet" href="/../css/login.css">
<% String id=(String)request.getAttribute("TodoNum");%>
<body>
<section class="container-login">
  <article class="half">
     <h1>TODOLIST</h1>
     <font color="red">${error}</font>
     <div class="tabs">
        <span class="tab signin active"><a href="#signin">로그인</a></span>
        <span class="tab signup"><a href="#signup">회원가입</a></span>
     </div>
     <div class="content">
        <div class="signin-cont cont">
          <form action="/login" method="post" >
          	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          		<%if(id!=null){ %>
               <input type="text" name="id" id="id" class="inpt" required="required" 
               value="<%=id%>" maxlength="20" pattern=".{2,20}">
               <%}else{ %>
               <input type="text" name="id" id="id" class="inpt" required="required" 
               placeholder="아이디 입력" maxlength="20" pattern=".{2,20}">
               <%} %>
               <label for="id">아이디 입력</label>
               <input type="password" name="password" id="password" class="inpt" 
               required="required" placeholder="비밀번호 입력" maxlength="20" pattern=".{2,20}">
     			<label for="password">비밀번호 입력</label>
               <input type="checkbox" name="_spring_security_remember_me" id="remember" class="checkbox">
               <label for="remember">Remember me</label>
               <div class="submit-wrap">
                    <input type="submit" value="로그인" class="submit">
                    <a href="#" class="more"></a>
               </div>
	        </form>
        </div>
        <div class="signup-cont cont">
        <form action="/SignUp" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input type="text" name="id" id="id" class="inpt" required="required" 
        	placeholder="아이디 (2글자 이상 20자 이하)"  maxlength="20" onKeyDown="checkNumber();" 
        	pattern=".{2,20}"/>
           <label for="id">아이디 입력</label>
           <input type="password" name="password" id="password" class="inpt" required="required" 
           placeholder="비밀번호 (2글자 이상 20자 이하)" maxlength="20" pattern=".{2,20}">
 		   <label for="password">비밀번호 입력</label>
           <input type="text" name="name" id="name" class="inpt" required="required" 
           placeholder="이름 (2글자 이상 20자 이하)" onKeyDown="checkNumber();" maxlength="20" pattern=".{2,20}">
           <label for="name">이름 입력</label>
     	   <input type="email" name="email" id="email" class="inpt" required="required" 
     	   placeholder="이메일 (2글자 이상 40자 이하)" maxlength="40" pattern=".{2,40}">
           <label for="email">이메일 입력</label>
           <div class="submit-wrap">
                <input type="submit" value="회원가입" class="submit">
                <a href="#" class="more"></a>
           </div>
        </form>
      </div>
     </div>
  </article>
  <div class="half bg"></div>
	</section>

<script>
$('.tabs .tab').click(function(){
    if ($(this).hasClass('signin')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signin-cont').show();
    } 
    if ($(this).hasClass('signup')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signup-cont').show();
    }
});
$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
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