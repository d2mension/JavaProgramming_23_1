<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="database.Select" %>
<!DOCTYPE html>
<%
    String ID = null;
    HttpSession sess = request.getSession(false);
    if (sess != null) {
        ID = (String) sess.getAttribute("loggedInUser");
        Select se = new Select();
        String table = "user ";
        String condition = "userID = \"" + ID + "\"";
        String EMAIL = se.selection(table, condition, 3);
    }
%>
<html lang = "ko">
    <head>
        <meta charset = "UTF-8">
        <title>조회 - 한국 미스테리</title>
        <link rel = 'stylesheet' href = 'inquiry.css'>
        <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet&display=swap" rel="stylesheet">
    </head>
    <body>
        <header>
            <div class = "logo">
                <a href = "index.jsp"><img src = "img/logo.png" alt = "logo" width = 330></a> <!--로고 사이즈 정하고 넣기-->
            </div>
        </header>
        <!--메뉴바-->
        <div class = "container">
            <div class="menu_wrap">
                <ul class = "dep1">
                    <li>
                        <a href="#">한국</a>
                        <ul class = "dep2">
                            <li><a href="kr_sf.jsp">한국 SF</a></li>
                            <li><a href="kr_mistery.jsp">한국 미스테리</a></li>
                            <li><a href="kr_or.jsp">한국 일반</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">영미</a>
                        <ul class = "dep2">
                            <li><a href="en_sf.jsp" id="en-sf">영미 SF</a></li>
                            <li><a href="en_or.jsp">영미 일반</a></li>
                            <li><a href="en_cl.jsp">영미 고전</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">프랑스 일반</a>
                        <ul class = "dep2">
                            <li><a href="fr_cl.jsp">프랑스 고전</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>

        <script>
        	var country = '프랑스'; 
            var category = '일반'; 
            var dataUrl = 'http://localhost:8090/Java_23_1/GetData?country=' + encodeURIComponent(country) + '&category=' + encodeURIComponent(category);

            var userID = '<%=ID%>';

            
            function checkFavoriteStatus(bookTitle, favStar) {
                var checkUrl = url = 'http://localhost:8090/Java_23_1/GetFavCheck?'
                    + 'userid=' + encodeURIComponent('<%= ID %>')
                    + '&title=' + encodeURIComponent(bookTitle)
            	fetch(checkUrl)
                .then(response => response.json())
                .then(data => {
                    if (data === true) {
                        favStar.innerHTML = '<img src="img/star_filled.png">';
                    } else {
                        favStar.innerHTML = '<img src="img/star_none.png">';
                    }
                })
                .catch(error => console.error('오류:', error));
            }
            
            function loadData() {

                fetch(dataUrl, {
                    headers: {
                        Accept: 'application/json',  // JSON 데이터 요청
                    },
                })
                .then(response => {
                    
                    return response.json();
                })
                .then(data => {
                    // 데이터로부터 동적으로 HTML 요소 생성
                    data.forEach(book => {
                        // 책 컨테이너 div 생성
                        var bookContainer = document.createElement('div');
                        bookContainer.className = 'book_container';

                        // 책 내용 div 생성
                        var contents = document.createElement('div');
                        contents.className = 'contents';

                        // 책 이미지 div 생성
                        var bookPic = document.createElement('div');
                        bookPic.className = 'book_pic';
                        bookPic.innerHTML = '<img src="' + book.image + '" alt="book" width="120">';

                        var favStar = document.createElement('div');
                        favStar.className = 'fav_star';
                        
                        checkFavoriteStatus(book.title, favStar);

                        favStar.addEventListener('click', function (e) {

                            if (userID == 'null') {
                                alert("로그인이 필요합니다.");
                                return 0;
                            }
                        	
                        	if (favStar.getAttribute('data-processing') == 'true') {
                                return;
                            }
                        	
                        	var imgElem = favStar.querySelector('img');
                        	
                        	console.log(imgElem);
                        	
                        	var newSrc = imgElem.src.includes('img/star_filled.png') ? 'img/star_none.png' : 'img/star_filled.png';
                            imgElem.src = newSrc;

                        	
                            var bookTitleElement = bookInfo.querySelector('.book_title');
                            var bookTitle = bookTitleElement ? bookTitleElement.textContent : "요소가 존재하지 않음";
                            
                            var url = 'http://localhost:8090/Java_23_1/ChangeFavorite?'
                                + 'userid=' + encodeURIComponent('<%= ID %>')
                                + '&title=' + encodeURIComponent(bookTitle)
                            
                               fetch(url, {
                                   headers: {
                                       'Content-Type': 'application/json'
                                   },
                               })
                               .then(response => {
                               		checkFavoriteStatus(book.title, favStar);
                               })
                               .catch(error => console.error('오류:', error))
                               .finally(() => {
                                   favStar.removeAttribute('data-processing');
                               });
                            
                        })
                        
                        
                        // 책 정보 div 생성
                        var bookInfo = document.createElement('div');
                        bookInfo.className = 'book_information';

                        // 책 제목 li 생성
                        var bookTitle = document.createElement('li');
                        bookTitle.className = 'book_title';
                        bookTitle.textContent = book.title;

                        // 키워드 ul 생성
                        var bookKeywords = document.createElement('ul');
                        bookKeywords.className = 'book_keywords';
                        // 각 키워드 li 생성
                        book.keywords.forEach(keyword => {
                            var kw = document.createElement('li');
                            kw.className = 'keyword';
                            kw.textContent = "#" + keyword;
                            bookKeywords.appendChild(kw);
                        });

                        // 저자 및 출판사 정보 li 생성
                        var bookMake = document.createElement('li');
                        bookMake.className = 'book_make';
                        bookMake.innerHTML = '<span class="book_author">' + book.author + '</span> | ' +
                                               '<span class="book_publisher">' + book.publisher + '</span>';

                        // 각 요소를 부모 div에 삽입
                        bookInfo.appendChild(bookTitle);
                        bookInfo.appendChild(bookMake);
                        bookInfo.appendChild(bookKeywords);
                        
                        contents.appendChild(bookPic);
                        contents.appendChild(bookInfo);

                        bookPic.appendChild(favStar);

                        bookContainer.appendChild(contents);

                        // 최종적으로 생성된 bookContainer를 문서의 지정된 위치에 삽입
                        document.body.appendChild(bookContainer);
                        
                        // checkBookData(favStar, userID, book.title);
                    });
                })
                .catch(error => console.error('Error:', error));
                
            }

            // 페이지 로드 시 loadData 함수 호출
            window.onload = loadData;

            
            
            

        </script>
    </body>
</html>
