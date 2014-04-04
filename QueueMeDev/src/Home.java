

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

import facebook4j.ResponseList;

/**
 * Servlet implementation class LoadHome
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QueueDatabase qdb = new QueueDatabase();	
		
//		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		
		int userID = Integer.parseInt((String) request.getSession().getAttribute("userId"));
//		int friendID = 1076890682;
//		String videoID = "J---aiyznGQ";
		
		//qdb.testInsert(userID, friendID, videoID);
		//qdb.testUpdate(userID, videoID);
		//qdb.testSelect(userID);
		//videoID = qdb.getThumbnailVideoID(userID, friendID);
		
		//request.getSession().setAttribute("v", videoID);
		
		List<Integer> friendsWithQueue = qdb.getFriendIDs(userID);
		request.getSession().setAttribute("friendID", friendsWithQueue);
		
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
