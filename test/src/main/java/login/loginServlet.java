package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public loginServlet() {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		
		String ID=request.getParameter("id");
		String PW=request.getParameter("pw");
		if(ID!=null&&PW!=null) {
			Identify ident=new Identify(ID, PW);
			try {
				if(ident.idExist()) {
					try {
						if(ident.match()) {
							out.println("login success<br>");
							
                            session = request.getSession(true);
                            session.setAttribute("loggedInUser", ID);
							session.setMaxInactiveInterval(-1);
                            
							out.println("Go to index page after 3 seconds");
							response.addHeader("Refresh", "3;url=index.jsp");
						}
						else {
							out.println("ID and PASSWORD do not match.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					out.println("ID does not exist.");
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