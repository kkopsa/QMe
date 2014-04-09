

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Friend;
import facebook4j.ResponseList;

/**
 * Servlet implementation class watch
 */
@WebServlet("/watch")
public class watch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public watch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("v");
		if (videoId == null) {
			QueueDatabase qdb = new QueueDatabase();
			List<String> videoList = new ArrayList<String>();			
			// Use from to get the playlist from the database
			String to = (String) request.getSession().getAttribute("userId");
			String from = request.getParameter("from");
			videoList = qdb.getVideoID(to, from);
			// Get the first video from the playlist and assign it to videoId
			videoId = videoList.get(0);			
			// Create a list to send to the jsp file
			request.setAttribute("videos", videoList);
			request.setAttribute("playlistEmpty", false);
		} else {
			request.setAttribute("playlistEmpty", true);
		}
		
		ResponseList<Friend> friends = (ResponseList<Friend>)request.getSession().getAttribute("friends");
		
		request.setAttribute("v", videoId);
		request.setAttribute("friends", friends);
		request.getRequestDispatcher("watch.jsp").forward(request, response);
	}
}
