import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> userList = new ArrayList<User>();
		QueueDatabase qdb = new QueueDatabase();
		Map<String, String> users = new HashMap<String, String>();

		Facebook facebook = (Facebook) request.getSession().getAttribute(
				"facebook");
		String oauthCode = request.getParameter("code");

		try {
			facebook.setOAuthAccessToken(facebook
					.getOAuthAccessToken(oauthCode));
		} catch (FacebookException e) {
			e.printStackTrace();
		}

		try {
			String userID = facebook.getId();
			ResponseList<Friend> friends = facebook.getFriends();
			for (int i = 0; i < friends.size(); i++) {
				users.put(friends.get(i).getId(), friends.get(i).getName());
			}

			List<String> friendsWithQueue = qdb.getFriendIDs(userID);

			for (int i = 0; i < friendsWithQueue.size(); i++) {
				if (users.containsKey(friendsWithQueue.get(i))) {
					User newUser = new User(users.get(friendsWithQueue.get(i)),
							friendsWithQueue.get(i));
					newUser.setThumbnail(userID);
					userList.add(newUser);
				}
			}

			request.getSession().setAttribute("friendTiles", userList);
			request.getSession().setAttribute("friends", friends);
			request.getSession().setAttribute("userId", userID);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FacebookException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/QMe/Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}