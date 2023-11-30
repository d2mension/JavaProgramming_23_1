<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "database.Select"
import = "database.Connect"
import = "jakarta.servlet.http.HttpServletRequest"%>
<%!
HttpServletRequest request;
String ID;
String EMAIL;
HttpSession sess;
String table;
String condition;
%>
<% 
ID="없음";
EMAIL="없음";
sess= request.getSession(false);
if(sess!=null) {
	ID=(String)sess.getAttribute("loggedInUser");
	Select se=new Select();
	table="user ";
	String condition="userID = \""+ID+"\"";
	EMAIL = se.selection(table, condition, 3);
} 
%>
<!DOCTYPE html>
<html lang = "ko">
<head>
    <meta charset = "UTF-8">
    <title>마이페이지</title>
    <link rel = 'stylesheet' href = 'mypage.css'>
    <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
</head>

<body>
    <header>
        <div class = "logo">
            <a href = "index.jsp"><img src = "img/logo.png" alt = "logo" width = 330></a>
        </div>
    </header>
    <main>
        <div class="mypage_bar">
            <div class="information">
                <p>아이디: <strong><%=ID %></strong></p>
                <p>이메일: <strong><%=EMAIL %></strong></p>
            </div>
            <div class = "caption">
        <button type="button" onclick="location.href='changePW.html'"
        	>비밀번호 변경하기</button>
        </div>
        
        <div class = "caption">
        <button type = "button" onClick="location.href='delAccount.jsp'"
        	>계정 삭제하기</button>
        </div>
        </div>
    
        <div class="wishlist">
            <div class="wishlist_bar">
            </div>
        </div>
    </main>
</body>
</html>
