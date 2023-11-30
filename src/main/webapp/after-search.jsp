<%@ 
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html lang = "ko">
    <meta charset = "UTF-8">
    <head>
        <link rel = 'stylesheet' href = 'inquiry.css'>
		<title>조회 - 검색</title>
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet&display=swap" rel="stylesheet">
    
	</head>
    
	<body>
		<div class = "logo">
            <a href = "index.jsp"><img src = "img/logo.png" alt = "logo" width = 300> <!--로고 사이즈 정하고 넣기-->
        	</a>
        </div>
	    <script>
		    var search = "<%= request.getParameter("search") %>";
	        var encodedSearchWord = "<%= request.getParameter("search_word") %>";
			var searchWord = decodeURIComponent(encodedSearchWord);
			
	        console.log(search);
	        console.log(searchWord);
	    	
	        var serverURL = "http://localhost:8090/Java_23_1/";
	        var url = serverURL + "GetSearchedData?search=" + search + "&search_word=" + searchWord;
	        
	        function displayData(data) {
	        	fetch(url, {
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
			
			                bookContainer.appendChild(contents);
			
			                // 최종적으로 생성된 bookContainer를 문서의 지정된 위치에 삽입
			                document.body.appendChild(bookContainer);
			            });
			        })
			        .catch(error => console.error('Error:', error));
			}
	
	        // 페이지 로드 시 displayData 함수 호출
	        window.onload = displayData;
	    </script>
	</body>
</html>