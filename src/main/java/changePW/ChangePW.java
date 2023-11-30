package changePW;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.Identify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import database.Update;

@WebServlet("/ChangePW")
public class ChangePW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session=null;
	String ID=null;
	
    public ChangePW() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        String ID = (String) session.getAttribute("loggedInUser");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String PW=request.getParameter("pw");
		String new_PW=request.getParameter("new_pw");
		if(ID!=null&&PW!=null) {
			Identify ident=new Identify(ID, PW);
			try {
				if(ident.idExist()) {
					try {
						if(ident.match()) {
							out.println("identified<br>");
							Update upd=new Update();
							if(upd.modify("user ", "userPW=\""+new_PW+"\"", "userID=\""+ID+"\"")==1) {
								out.println("password changed<br>");
							}
							else {
								out.println("password change failed");
							}

							out.println("Go to index page after 3 seconds");
							response.addHeader("Refresh", "3;url=index.jsp");
						}
						else {
							out.println("PASSWORD do not match.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					out.println("failed to import ID");
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
