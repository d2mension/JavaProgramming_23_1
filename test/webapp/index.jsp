<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jakarta.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang = "ko">
    <head>
        <meta charset = "UTF-8">
        <meta http-equiv="X-UA-Compatible"
        content="IE=edge">
        <meta name = "viewport"
        content="width=device-width, initial-scale=1.0">

        <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
        <title>BOOKSEARCH</title>
        <link rel = "stylesheet" href="style.css">
    </head>
    <body>
        <!--헤더 영역 시작-->
        <header>
            <!--로고-->
            <div class = "logo">
                <a href = "index.jsp"><img src = "img/logo.png" alt = "logo" width = 300> <!--로고 사이즈 정하고 넣기-->
            	</a>
            </div>
            
            <!--메뉴-->
            <nav class = "menu_bar">
                <ul class = "top_menu">
                	<li><a href = "inquiry.html"><img src = "img/magnify.png" width = 11>  조회</a></li>
                	<%if(session.getAttribute("loggedInUser")==null){ %>
                    <li><a href = "login.html"><img src = "img/lock.png" width = 9 height = 11.5> 로그인</a></li>
                    <%} else{%>
                    <li><a href = "mypage.jsp"><img src = "img/user.png" width = 10 height = 11>  마이페이지</a></li>
                    <li><a href = "logout.jsp"><img src = "img/lock.png" width = 9 height = 11.5> 로그아웃</a></li>
                	<%} %>
                </ul>
                </nav>

                <!--검색창-->
            <div class = "container">
                <form action= "" class = "search-bar">
                    <input type = "text" placeholder= "검색어를 입력하세요" name ='q'>
                    <button type="submit"><img src = "img/search-bar2.png"></button> <!--이미지 넣기 (검색창 아이콘)-->
                </form>
            </div>
        </header>

        <!--추천 도서 메인 슬라이드-->
        <section>
            <div class = "bookslide"></div>)
                
        </section>

        <!-- 푸터 영역 시작 -->
        <footer>
            <div class = "footer_bottom"></div>
                <a>Copyright</a>
        </footer>
            <!-- 푸터 영역 끝-->
    </body>
</html>