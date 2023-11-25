package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Select extends Connect{
	final String select="SELECT * FROM ";
	final String where="WHERE ";
	static ResultSet rs = null;
	
	public Select(){
		super();
	}
	
	public String selection(String tab, String cond, int n) {
		String existance="-1";
		String getData="-1";
		st=select+tab+where+cond+semicolon;
		try {
			ps=con.prepareStatement(st);
			rs=ps.executeQuery();

			if(rs.next()) {
				if(n>0) {
					getData=rs.getString(n);
				}
				existance="1";
			}
			else {
				existance="0";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			}catch(Exception e) {
				System.out.println("selection error");
			}
		}
		if(n==0) {
			return existance;
		}
		else {
			return getData;
		}
		
	}
	
}