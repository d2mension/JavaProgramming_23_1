<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "jakarta.servlet.http.HttpServletRequest"
    import = "database.Connect"
    import = "database.Delete"
    %>
<%!
HttpServletRequest request;
HttpSession sess; 
String ID;
String cond;
int success;
%>
<% sess = request.getSession(false); 
if(sess != null){
	ID = (String)sess.getAttribute("loggedInUser");
}
%>
<!DOCTYPE html>
<html lang = "ko">
<head>
    <meta charset = "UTF-8">
    <title>계정 삭제</title>
    <link rel = "stylesheet" href="logout_page.css">
    <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
</head>
<body>
    <section class = "deleteAccount-form">
        <div class = "logo">
            <a href="index.jsp"><img src = "img/logo.png" alt = "logo" width = 400 height = 155></a></div>
        
        <%if(ID != null){ %>
        <div class = "caption">
        <button type="button" 
        	<% 
        	Delete delAcc = new Delete();
        	cond = "userID = \"" + ID + "\"";
        	success = delAcc.del("user ", cond);
        	if(success==1)
        		sess.invalidate();
        	
        %>onclick = "location.href = 'index.jsp'; res();" 
        >계정 삭제</button>
    	</div>
        <%}else{%> 
        <div class = "caption">
        <button type="button" onclick = "location.href = 'login.html'">계정 삭제하기(로그인 필요)</button>
        </div>
        <%}%>
        
        <div class = "caption">
        <button type = "button" onClick="history.back();">뒤쪽 페이지로</button>
        </div>
    </section>
<script>
function res(){
	let result = <%=success%>;
    if(result == 1){
    	alert("계정 삭제 성공");
    }else{
    	alert("계정 삭제 오류");
    }
}
</script>
</body>
</html>
