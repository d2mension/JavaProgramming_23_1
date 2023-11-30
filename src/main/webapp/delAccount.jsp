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
    <link rel = 'icon' type = 'img' href = 'img/logo2.png'>
</head>
<body>
    <section class = "deleteAccount-form">
        <div class = "logo">
            <a href="index.jsp"><img src = "img/logo.png" alt = "logo" width = 400 height = 155></a></div>
        
        <%if(ID != null){ %>
        <div class = "caption">
          <button type="button" onclick = "delAccount()" >처음 페이지로</button>
        	<%Delete delAcc =new Delete();
        	cond = "userID = \"" + ID + "\"";
        	success= delAcc.del("user ", "userID = '" + ID + "'");
        	%>
        		<script>
        		var succ = <%=success%>;
        		if(succ==1){
        			alert('계정삭제 완료');
        		}else{
        			alert('계정삭제 오류');
        		}
        		
        		function delAccount() {
                    <% sess.invalidate(); %>
                    window.location.href = 'index.jsp';
                }
    			</script>
    	</div>

        <%}else{%> 
        <div class = "caption">
        <button type="button" onclick = "location.href = 'login.html'">계정 삭제하기(로그인 필요)</button>
        </div>
        <%}%>
        
        <!-- 
        <div class = "caption">
        <button type = "button" onClick="location.href = 'index.jsp';">메인 페이지로</button>
        </div>
        -->
    </section>

</body>
</html>