

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			// Use from to get the playlist from the database
			int to = (int)request.getSession().getAttribute("userId");
			String from = request.getParameter("from");
			// Get the first video from the playlist and assign it to videoId
			videoId = "jR4lLJu_-wE";
			// Create a list to send to the jsp file
		}
		List<String> friends = new ArrayList<String>();
		friends.add("Kevin Kopsa");
		friends.add("Taylor Skinner");
		friends.add("Andrew Fry");
		friends.add("Dillon Chamberlin");
		friends.add("Bradley Graham");
		friends.add("Stephen Landry");
		
		request.setAttribute("v", videoId);
		request.setAttribute("friends", friends);
		request.getRequestDispatcher("watch.jsp").forward(request, response);
	}
}
