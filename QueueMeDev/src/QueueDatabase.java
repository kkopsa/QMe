import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueueDatabase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/queue_me_dev";

	static final String USER = "queue_admin";
	static final String PASS = "googleorbust";

	Connection conn = null;

	public QueueDatabase() {
		// default constructor
	}	
	
	public void addVideo(String pUserID, String pFriendID, String pVideoID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3.5: Insert dummy data
			System.out.println("Inserting row...");
			stmt = conn.createStatement();
			String insert;
			insert = "INSERT INTO video (to_facebook_id, from_facebook_id, youtube_video_id, date_added, watched) VALUES ("
					+ pFriendID
					+ ", "
					+ pUserID
					+ ", \'"
					+ pVideoID
					+ "\', UTC_DATE(), 0)";
			System.out.println(insert);
			stmt.executeUpdate(insert);

			// STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}

	public List<String> getVideoID(String pUserID, String pFriendID) {
		Connection conn = null;
		Statement stmt = null;
		List<String> videoID = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT youtube_video_id FROM video WHERE watched=0 AND from_facebook_id="
					+ pFriendID
					+ " AND to_facebook_id="
					+ pUserID
					+ " ORDER BY date_added DESC";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			while(rs.next())
			{
				videoID.add(rs.getString("youtube_video_id"));	
			}

			// Display values
			for (int i = 0; i < videoID.size(); i++)
				System.out.println("VideoID: " + videoID.get(i));			

			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();			

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try		
		System.out.println("Goodbye!");		
		return videoID;
	}

	
	public List<String> getFriendIDs(String pUserID) {
		Connection conn = null;
		Statement stmt = null;
		List<String> friendsWithQueueID = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT DISTINCT from_facebook_id FROM video WHERE to_facebook_id="
					+ pUserID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			while(rs.next())
			{
				friendsWithQueueID.add(rs.getString("from_facebook_id"));
			}

			// Display values
			for (int i = 0; i < friendsWithQueueID.size(); i++)
				System.out.println("FriendID: " + friendsWithQueueID.get(i));			

			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();			

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try		
		System.out.println("Goodbye!");		
		return friendsWithQueueID;
	}

	public String getThumbnailVideoID(String pUserID, String pFriendID) {
		Connection conn = null;
		Statement stmt = null;
		String videoID = new String();
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT youtube_video_id FROM video WHERE watched=0 AND from_facebook_id="
					+ pFriendID
					+ " AND to_facebook_id="
					+ pUserID
					+ " ORDER BY date_added DESC LIMIT 1";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			rs.next();
			videoID = rs.getString("youtube_video_id");

			// Display values
			System.out.println("VideoID: " + videoID);			

			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();			

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try		
		System.out.println("Goodbye!");		
		return videoID;
	}

	public void testSelect(int pUserID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM video WHERE to_facebook_id=" + pUserID;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int userID = rs.getInt("to_facebook_id");
				int friendID = rs.getInt("from_facebook_id");
				String videoID = rs.getString("youtube_video_id");
				String dateAdded = rs.getString("date_added");
				String watched = rs.getString("watched");

				// Display values
				System.out.print("UserID: " + userID);
				System.out.print(", FriendID: " + friendID);
				System.out.print(", VideoID: " + videoID);
				System.out.print(", Date Added: " + dateAdded);
				System.out.println(", Watched?: " + watched);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}

	public void testInsert(int pUserID, int pFriendID, String pVideoID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3.5: Insert dummy data
			System.out.println("Inserting row...");
			stmt = conn.createStatement();
			String insert;
			insert = "INSERT INTO video (to_facebook_id, from_facebook_id, youtube_video_id, date_added, watched) VALUES ("
					+ pUserID
					+ ", "
					+ pFriendID
					+ ", \'"
					+ pVideoID
					+ "\', UTC_DATE(), 0)";
			System.out.println(insert);
			stmt.executeUpdate(insert);

			// STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}

	public void testUpdate(int pUserID, String pVideoID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3.5: Insert dummy data
			System.out.println("Inserting row...");
			stmt = conn.createStatement();
			String update;
			update = "UPDATE video SET watched=1 WHERE to_facebook_id="
					+ pUserID + " AND youtube_video_id=\"" + pVideoID + "\"";
			System.out.println(update);
			stmt.executeUpdate(update);

			// STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}
}// end FirstExample
