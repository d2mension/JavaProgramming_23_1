package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ChangeFavorite")
public class Favorite extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 클라이언트에서 전달된 bookId 파라미터를 가져옵니다.
		String userId = request.getParameter("userid");
        String bookTitle = request.getParameter("title");
        
        FavCheck isInFav = new FavCheck();
        
        boolean result = isInFav.ifFavorite(userId, bookTitle);
        
        if (userId != "null") {
        	System.out.println(result+" "+userId+" "+bookTitle);
        	
            if (!result) 
            	addFav(userId, bookTitle);
            else
            	delFav(userId, bookTitle);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
        
        /*
        PrintWriter out = response.getWriter();
        String jsonResponse = "{\"success\":" + result + "}";
        out.print(jsonResponse);

        out.flush();
        out.close();
        */
    }

    private boolean addFav(String userId, String bookTitle) {
    	try {
	        // 데이터베이스 연결 정보
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/booksearch";
	        String dbUser = "root";
	        String dbPassword = "0000";

	        // JDBC 드라이버 로드
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // 데이터베이스 연결
	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
	        	String sql = "Select bookNum From booksearch.booklist Where bookTitle = ?";
	        	
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            	statement.setString(1, bookTitle);
	            	
	            	ResultSet rs = statement.executeQuery();
	            	
	            	String bookNum = null;
	            	if (rs.next()) {
	            		bookNum = rs.getString("bookNum");
	            	}
	            	
	            	String checkSql = "SELECT * FROM favorite WHERE userID = ? AND bookNum = ?";
	                PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	                checkStatement.setString(1, userId);
	                checkStatement.setString(2, bookNum);
	                ResultSet resultSet = checkStatement.executeQuery();

	                if (resultSet.next()) {
	                    // 이미 해당 항목이 즐겨찾기에 있다면 중복 저장 방지
	                    return false;
	                }
	            	
	            	String inputSql = "Insert INTO favorite values(?, ?)";
	            	PreparedStatement inputSt = connection.prepareStatement(inputSql);
	            	inputSt.setString(1, userId);
	            	inputSt.setString(2, bookNum);
	            	
	            	int result = inputSt.executeUpdate();
	            	if (result == 1) {
	            		System.out.println("Favorite List Add Success");
	            	}
	                
	            }
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }

        return true;
    }
    
    
    private boolean delFav(String userId, String bookTitle) {
    	try {
	        // 데이터베이스 연결 정보
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/booksearch";
	        String dbUser = "root";
	        String dbPassword = "0000";

	        // JDBC 드라이버 로드
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // 데이터베이스 연결
	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
	            // 이미지를 가져오는 SQL 쿼리
	        	String sql = "Select bookNum From booksearch.booklist Where bookTitle = ?";
	        	
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            	statement.setString(1, bookTitle);
	            	
	            	ResultSet rs = statement.executeQuery();
	            	
	            	String bookNum = null;
	            	
	            	if(rs.next()) {
	            		bookNum = rs.getString("bookNum");
	            	}
	            	
	            	
	            	connection.prepareStatement("set SQL_SAFE_UPDATES = 0;").executeUpdate();
	            	
	            	String inputSql = "Delete From favorite where userID = ? and bookNum = ?";
	            	PreparedStatement inputSt = connection.prepareStatement(inputSql);
	            	inputSt.setString(1, userId);
	            	inputSt.setString(2, bookNum);
	            	
	            	int result = inputSt.executeUpdate();
	            	if (result == 1) {
	            		System.out.println("Favorite List Delete Success");
	            	}
	                
	            }
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

        return true;
    }
}
