import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;

/**
 * Servlet implementation class CallBack
 */
@WebServlet("/CallBack")
public class CallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		if (request.getSession().getAttribute("facebook") != null)
		{
			Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
			String oauthCode = request.getParameter("code");
			
			try {
				facebook.setOAuthAccessToken(facebook.getOAuthAccessToken(oauthCode));
			} catch (FacebookException e) {
				e.printStackTrace();
			}
		}
		//response.sendRedirect(request.getContextPath() + "/home.jsp");
		new LoadHome().doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}