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
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet&display=swap" rel="stylesheet">
    
</head>

<body>
    <header>
        <div class = "logo">
            <a href = "index.jsp"><img src = "img/logo.png" alt = "logo" width = 330></a>
        </div>
    </header>
    <main>
    	<div class = "user_container">
            <div class = "user_bar">
                <div class = "user_information">
                    <div class="information">
	                <li class = "user_id">아이디: <strong><%=ID %></strong></li>
	                <li class = "user_email">이메일: <strong><%=EMAIL %></strong></li>
	            </div>
            	<div class = "caption">
	        		<button type="button" onclick="location.href='changePW.html'">
	        		비밀번호 변경</button>
        		</div>
        
		        <div class = "caption">
			        <button type = "button" onClick="location.href='delAccount.jsp'">
			        회원 탈퇴</button>
                </div>
            </div>
        </div>
        <div class="mypage_bar">
            
        </div>
        </div>
    
        <div class="wishlist">
        	<div class="wishinfo"><p>내가 좋아하는 책</p></div>
            <div class="wishlist_bar" id="wish_print">
            	
            </div>
        </div>
    </main>
    
    <script>
    	var wishPrint = document.getElementById("wish_print");
    	var userID = '<%=ID%>';
    	var wishUrl = 'http://localhost:8090/Java_23_1/GetTitleData?userid=' + encodeURIComponent(userID);
    	
    	function loadFavData() {
            fetch(wishUrl, {
                headers: {
                    Accept: 'application/json', // JSON 데이터 요청
                },
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    if (data == null || data === "false") {
                        wishPrint.innerHTML = "<p>아직 즐겨찾기 설정한 책이 없습니다.<br>즐겨찾기를 등록해보세요.</p>";
                    } else {
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

                            // 책 제목 li 생성
                            var bookTitle = document.createElement('li');
                            bookTitle.className = 'book_title';
                            bookTitle.textContent = book.title;

                            // 각 요소를 부모 div에 삽입
                            contents.appendChild(bookPic);
                            contents.appendChild(bookTitle);

                            bookContainer.appendChild(contents);

                            // 최종적으로 생성된 bookContainer를 문서의 지정된 위치에 삽입
                            wishPrint.appendChild(bookContainer);
                        })
                    }
                })
                .catch(error => console.error('Error:', error));
        }

        // 페이지 로드 시 loadData 함수 호출
        window.onload = loadFavData;
    </script>
</body>
</html>
