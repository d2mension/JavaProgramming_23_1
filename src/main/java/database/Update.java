package database;

import java.sql.SQLException;

public class Update extends Connect {
	final String update="UPDATE ";
	final String set="SET ";
	final String where="WHERE ";
	
	public Update(){
		super();
	}
	
	public int modify(String tab, String newData, String cond){
		int success=0;
		
		st=update+tab+set+newData+where+cond+semicolon;
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
				System.out.println("update error");
			}
		}
		return success;
	}

}
