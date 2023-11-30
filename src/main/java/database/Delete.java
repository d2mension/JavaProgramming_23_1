package database;

import java.sql.*;

public class Delete extends Connect{
	final String delete="DELETE FROM ";
	final String where="WHERE ";
	
	public Delete(){
		super();
	}
	
	public int del(String tab, String cond) {
		int success=0;
		
		String favList = "favorite ";
		
		String favSt=delete+favList+where+cond+semicolon;
		st=delete+tab+where+cond+semicolon;

		try {
			PreparedStatement favPs = con.prepareStatement(favSt);
			favPs.executeUpdate();
			
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
