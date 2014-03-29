

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadHome
 */
@WebServlet("/LoadHome")
public class LoadHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QueueMeDatabase qmdb = new QueueMeDatabase();
		
//		Map<Integer, Integer> friendRankings = qmdb.getTopFriends(1801872291);
//		request.setAttribute("friendRankings", friendRankings);
		
//		List<Integer> friends = qmdb.getAllFriends(1801872291);
//		request.setAttribute("friends", friends);
		
		boolean topFriendSet = qmdb.checkTopFriends(1801872290);
		request.setAttribute("topFriendSet", topFriendSet);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/home.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
