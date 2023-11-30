package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetFavCheck")
public class FavCheck extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userID = request.getParameter("userid");
        String bookTitle = request.getParameter("title");
        
        boolean isInFavorite = false;
        if (userID != "null") {
            isInFavorite = ifFavorite(userID, bookTitle);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String json = new Gson().toJson(isInFavorite);
        
        out.print(json);
        
        out.flush();
        out.close();
    }
	
	public boolean ifFavorite (String userID, String bookTitle) {
		boolean result = false;
		
		try {
            // 데이터베이스 연결 정보
            String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/booksearch";
            String dbUser = "root";
            String dbPassword = "0000";

            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                // 즐겨찾기 데이터를 가져오는 SQL 쿼리
                String sql = "SELECT * FROM favorite, booksearch.booklist "
                		+ "WHERE favorite.bookNum = booksearch.booklist.bookNum "
                		+ "and userID = ? and booksearch.booklist.bookTitle = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, userID);
                    statement.setString(2, bookTitle);

                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                    	result = true;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
		return result;
		
	}
}
