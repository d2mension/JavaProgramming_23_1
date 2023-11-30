package signup;

public abstract class Check_ab {
	
	boolean space(String s) {
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==' ') {
				return true;
			}
		}
		return false;
	}
	
	boolean num(String s, int i) {
		if((int)(s.charAt(i))>47&&(int)(s.charAt(i))<58) {
			return true;
		}
		return false;
	}
	
	boolean alpha(String s, int i) {
		if(((int)(s.charAt(i))>64&&(int)(s.charAt(i))<91)||((int)(s.charAt(i))>96&&(int)(s.charAt(i))<123)) {
			return true;
		}
		return false;
	}
		
	boolean at(String s) {
		int atNum = 0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='@') {
				atNum++;
			}
		}
		return (atNum==1);
	}

}
