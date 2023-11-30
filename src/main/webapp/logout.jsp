<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "jakarta.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang = "ko">
<head>
    <meta charset = "UTF-8">
    <title>로그아웃</title>
    <link rel = "stylesheet" href="logout_page.css">
    <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
</head>
<body>
    <section class = "logout-form">
        <div class = "logo">
            <a href="index.jsp"><img src = "img/logo.png" alt = "logo" width = 400 height = 155></a></div>
        <div class = "caption">
        <button type="button" onclick="location.href='index.jsp'"
        <%session.invalidate(); %>>로그아웃</button>
        </div>
        
        <div class = "caption">
        <button type = "button" onClick="history.back();">뒤쪽 페이지로</button>
        </div>
    </section>

</body>
</html>