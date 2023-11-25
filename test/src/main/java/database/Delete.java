package database;

import java.sql.SQLException;

public class Delete extends Connect{
	final String delete="DELETE FROM ";
	final String where="WHERE ";
	
	public Delete(){
		super();
	}
	
	public int del(String tab, String cond) {
		int success=0;
		
		st=delete+tab+where+cond+semicolon;
		try {
			ps=con.prepareStatement(st);
			success=ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			}catch(Exception e) {
				System.out.println("delete error");
			}
		}
		return success;
	}

}
