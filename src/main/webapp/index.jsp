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
                <form action= "after-search.jsp" method = "GET" class = "search-bar">
                	<label for="search"></label>
					<select name="search" id="search">
					    <option value="title">제목</option>
					    <option value="author">작가</option>
					    <option value="keyword">키워드</option>
					</select>
                    <input type = "text" placeholder= "검색어를 입력하세요" name ='search_word' id = "search_word">
                    <button type="submit"><img src = "img/search-bar2.png"></button> <!--이미지 넣기 (검색창 아이콘)-->
                </form>
            </div>
        </header>

        <!--추천 도서 메인 슬라이드-->
        
        <div id="slide">
            <div class="slider-container">
                <span class="leftBtn">
                    <i class="fa-solid fa-chevron-left"></i>
                </span>
                <div class="slider">
                    <span class="first-img">
                        <div class="text-wrap">
                            <h5>Today</h5>
                            <h1>추천 도서</h1>
                        </div>
                        <img src="img/test_kr.png" alt="0" />
                    </span>
                    <span>
                        <div class="text-wrap">
                            <h5>#추천</h5>
                            <h1>오늘은 이 책!</h1>
                        </div>
                        <img src="img/010.png" alt="1" />
                    </span>
                    <span>
                        <div class="text-wrap">
                            <h5>#같이 읽어봅시다</h5>
                            <h1>영미 소설</h1>
                        </div>
                        <img src="img/015.png" alt="2" />
                    </span>
                    <span>
                        <div class="text-wrap">
                            <h5>#new</h5>
                            <h1>프랑스 소설까지!</h1>
                        </div>
                        <img src="img/014.png" alt="3" />
                    </span>
                    <span class="last-img">
                        <div class="text-wrap">
                            <h5>#추천</h5>
                            <h1>오늘은 이 책!</h1>
                        </div>
                        <img src="img/010.png" alt="4" />
                    </span>
                </div>
                <span class="rightBtn">
                    <i class="fa-solid fa-chevron-right"></i>
                </span>
                <ul class="pagination">
                    <li class="active" data-index="1"></li>
                    <li data-index="2"></li>
                    <li data-index="3"></li>
                  </ul>
            </div>
        </div>

        <!-- 푸터 영역 시작 -->
        <footer>
            <div class = "footer_bar">
                <p>JAVA PROGRAMMING T6</p>
                </div>
        </footer>
        
    </body>
    <script src="app.js"></script>
    <script>
    	function loadData() {
    		var search = document.getElementById("search").value;
    	    var searchWord = document.getElementById("search_word").value;
    	    
    	    var encodedSearchWord = encodeURIComponent(searchWord);
    	    
            var url = "after-search.jsp?search=" + search + "&search_word=" + encodedSearchWord;

            window.location.href = url;
    	    
    	}
	</script>
</html>