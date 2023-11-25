package signup;

import java.sql.SQLException;

import database.Select;

public class Check extends Check_ab{
	String id;
	String pw;
	String em;
	
	public Check(String i, String p, String e) {
		super();
		this.id = i;
		this.pw = p;
		this.em = e;
	}
	
	boolean isValidID() throws SQLException {
		String cond="userID = \""+id+"\"";
		return (!isDuplicated(cond)&&isSatisfied(id));
	}
	
	boolean isDuplicated(String cond) throws SQLException {
		Select se=new Select();
		String table="user ";
		
		if(se.selection(table, cond, 0)=="1") {
			return true;
		}
		else if(se.selection(table, cond, 0)=="0") {
			return false;
		}
		else {
			System.out.println("database select error");
			return true;
		}
	}
	
	boolean isSatisfied(String id) {
		if(space(id)) {
			return false;
		}
		else {
			for(int i=0;i<id.length();i++) {
				if(!(num(id, i)||alpha(id, i))) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean isValidPW(){
		return !space(pw);
	}
	
	boolean isValidEM() throws SQLException {
		String cond="userEMAIL = \""+em+"\"";
		return !isDuplicated(cond)&&!space(em)&&at(em);
	}

}