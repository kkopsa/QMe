import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Friendlist;
import facebook4j.ResponseList;

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
		
		Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
		String oauthCode = request.getParameter("code");
		
		try {
			facebook.setOAuthAccessToken(facebook.getOAuthAccessToken(oauthCode));
		} catch (FacebookException e) {
			e.printStackTrace();
		}

		
		try {
			String userID = facebook.getId();
			ResponseList<Friend> friends = facebook.getFriends();
			request.getSession().setAttribute("friends", friends);
			request.getSession().setAttribute("userId", userID);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/QMe/Home");
		//new LoadHome().doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}