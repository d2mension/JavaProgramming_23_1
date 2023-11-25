package login;

import java.sql.SQLException;

import database.Select;

public class Identify {
	String ID;
	String PW;
	String table;
	String cond;
	
	public Identify(String i, String p) {
		this.ID=i;
		this.PW=p;
		this.table="user ";
		this.cond="userID = \""+i+"\"";
	}
	
	public boolean idExist() throws SQLException {
		Select se=new Select();
		
		if(se.selection(table, cond, 0)=="1") {
			return true;
		}
		else if(se.selection(table, cond, 0)=="0"){
			return false;
		}
		else {
			System.out.println("idExist database select error");
			return false;
		}
	}
	
	public boolean match() throws SQLException{
		Select se=new Select();
		String pw=se.selection(table, cond, 2);
		if(pw=="-1") {
			System.out.println("match database select error");
			return false;
		}
		else {
			if(pw.equals(this.PW)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}
