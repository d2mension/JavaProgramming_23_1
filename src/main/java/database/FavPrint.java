package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/GetFavData")
public class FavPrint extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 클라이언트에서 전달된 userID 파라미터를 가져옵니다.
        String userID = request.getParameter("userid");

        // 즐겨찾기 데이터를 가져오는 메서드 호출
        BookData[] favData = getFavData(userID);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (favData != null) {
            // 가져온 즐겨찾기 데이터를 JSON으로 변환
            String json = new Gson().toJson(favData);
            out.print(json);
        } else {
            // 즐겨찾기 데이터가 없을 경우 "false" 문자열을 반환
            out.print("false");
        }

        out.flush();
        out.close();
    }

    private BookData[] getFavData(String userID) {
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
                // 즐겨찾기 데이터를 가져오는 SQL 쿼리
                String sql = "SELECT * FROM favorite WHERE userID = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, userID);

                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        String url = rs.getString("ImgUrl");
                        String title = rs.getString("bookTitle");

                        BookData bookData = new BookData();
                        bookData.setImg(url);
                        bookData.setTitle(title);

                        bookList.add(bookData); // 책 데이터를 리스트에 추가
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (bookList.isEmpty()) {
            return null; // 즐겨찾기 데이터가 없을 경우 null 반환
        }

        // 리스트를 배열로 변환하여 반환
        return bookList.toArray(new BookData[0]);
    }
}