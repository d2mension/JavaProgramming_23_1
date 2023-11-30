package signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import database.Insert;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Signup")
public class SignupSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignupSevlet() {
        // TODO Auto-generated constructor stub
    	super();
    }


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("signup destroy");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String ID=request.getParameter("id");
		String PW=request.getParameter("pw");
		String EMAIL=request.getParameter("em");
		System.out.println(ID);
		System.out.println(PW);
		System.out.println(EMAIL);
		
		if(ID!=null&&PW!=null&&EMAIL!=null) {
			Check c= new Check(ID, PW, EMAIL);
			
			try {
				if(c.isValidID()&&c.isValidPW()&&c.isValidEM()) {
					out.println("ID: "+ID+"<br>");
					out.println("PW: "+PW+"<br>");
					out.println("EMAIL: "+EMAIL+"<br>");
					
					Insert ins=new Insert();
					ins.signUpInput(ID, PW, EMAIL);
					if(ins.result==1) {
						out.println("Sign Up success");
						out.println("Return to Login Page");
						response.addHeader("Refresh", "3;url=login.html");
					}
				}
				else if(!c.isValidID()) {
					out.println("ID not available");
				}
				else if(!c.isValidPW()) {
					out.println("PASSWORD not available");
				}
				else {
					out.println("EMAIL not available");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			out.println("null");
		}
		
	}

}