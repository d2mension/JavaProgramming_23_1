package database;

import java.sql.Connection;

import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;



@WebServlet("/GetData")
public class CategoryPrint extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        // 키워드 파라미터 받기
        String country = request.getParameter("country");
        String category = request.getParameter("category");
        
        // 데이터베이스에서 데이터 가져오기
        BookData[] jsonData = getData(country, category);
        
        // JSON으로 변환하여 응답
        String jsonResponse = new Gson().toJson(jsonData);
        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
        
	private BookData[] getData(String country, String category) {
	    List<BookData> bookList = new ArrayList<>();
	    
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
	            String sql = "SELECT * FROM booksearch.booklist WHERE bookNation = ? AND bookCategory = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, country);
	                statement.setString(2, category);
	                
	                ResultSet rs = statement.executeQuery();
	                while (rs.next()) {
	                    int bookNum = rs.getInt("bookNum");
	                    String url = rs.getString("ImgUrl");
	                    String title = rs.getString("bookTitle");
	                    String author = rs.getString("bookAuthor");
	                    String publisher = rs.getString("bookPublisher");
	                    List<String> keywords = new ArrayList<>();
	                    
	                    String subSql = "SELECT keyword FROM booksearch.keywords WHERE bookNum = ?";
	                    try (PreparedStatement subSt = connection.prepareStatement(subSql)) {
	                        subSt.setInt(1, bookNum);
	                        ResultSet subRs = subSt.executeQuery();
	                        while (subRs.next()) {
	                            keywords.add(subRs.getString("keyword"));
	                        }
	                    }
	                    
	                    BookData bookData = new BookData();
	                    bookData.setImg(url);
	                    bookData.setTitle(title);
	                    bookData.setAuthor(author);
	                    bookData.setPublisher(publisher);
	                    bookData.setKws(keywords);
	                    bookList.add(bookData);
	                }
	            }
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }

	    return bookList.toArray(new BookData[0]);
	}

    
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
    
    
}
