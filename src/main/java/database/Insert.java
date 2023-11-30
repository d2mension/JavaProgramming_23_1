package database;

public class Insert extends Connect{
	static final String insert="insert into ";
	String table=null;
	String values=null;
	public int result;
	
	public Insert() {
		super();
	}
	
	public void signUpInput(String id, String pw, String em) {
		String values= "values(?,?,?)";
		table="user ";
		st=insert+table+values+semicolon;
		
		try {
			ps=con.prepareStatement(st);
			
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, em);
			
			result=ps.executeUpdate();
			if(result==1) {
				System.out.println("success");
			}
		}catch(Exception e) {
			System.out.println("data insertion is failed");
		}finally {
			try {
				if(ps!=null&&!ps.isClosed()) {
					ps.close();
				}
			}catch(Exception f) {
				
			}
		}
	}
}
