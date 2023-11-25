package object;

import java.util.ArrayList;

public class User {
	String userId;
	String userPassword;
	String userEmail;
	ArrayList<Integer> favorites=new ArrayList<>();
	
	public User(String userId, String userPassword, String userEmail) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
	}
	
}