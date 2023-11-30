package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeywordDataGet {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "0000";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/test";
	
	String searchWord = "";
	
	public KeywordDataGet(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public void setWord(String word) {
		this.searchWord = word;
	}
	
	public String getWord() {
		return this.searchWord;
	}
	
	public List<Integer> search() {
		Connection connect = null;
		List<Integer> bookNumList = new ArrayList<>(); 
		
		try {
			Class.forName(DRIVER);
			connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from ";
			String table = "booksearch.booklist INNER JOIN booksearch.keywords ON booksearch.booklist.bookNum = booksearch.keywords.bookNum ";
			String where = "Where ";
			String condition = "booksearch.keywords.keyword LIKE ?";
			String semi = ";";
			
			
			try {
				ps = connect.prepareStatement(sql+table+where+condition+semi);
				ps.setString(1, "%" + this.getWord() + "%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int bookNum = rs.getInt("bookNum");
					bookNumList.add(bookNum);
				}
				
				return bookNumList;
				
				}
			 catch (Exception e) {}
		} catch(ClassNotFoundException e) {
			System.out.println("driver 오류");
		} catch(SQLException error) {
			System.out.println("db 접속 오류");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (connect != null && !connect.isClosed()) connect.close();
	        } catch (Exception e) {}
	        //System.out.println("db 연결 해제 성공");
		}
		return null;
	
	}
}
