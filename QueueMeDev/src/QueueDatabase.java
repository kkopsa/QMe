import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueueDatabase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/qme";

	static final String USER = "queue_admin";
	static final String PASS = "googleorbust";

	public QueueDatabase() {
		// default constructor
	}

	public void addVideo(String pUserID, String pFriendID, String pVideoID,
			String pVideoTitle) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3.5: Insert dummy data
			stmt = conn.createStatement();
			String insert;
			insert = "INSERT INTO video (to_facebook_id, from_facebook_id, youtube_video_id, youtube_video_title, date_added, watched) VALUES ("
					+ pFriendID
					+ ", "
					+ pUserID
					+ ", \'"
					+ pVideoID
					+ "\', \'"
					+ pVideoTitle + "\', UTC_DATE(), 0)";
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
	}

	public List<String> getVideoID(String pUserID, String pFriendID) {
		Connection conn = null;
		Statement stmt = null;
		List<String> videoID = new ArrayList<String>();
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT youtube_video_id FROM video WHERE watched=0 AND from_facebook_id="
					+ pFriendID
					+ " AND to_facebook_id="
					+ pUserID
					+ " ORDER BY date_added DESC";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			while (rs.next()) {
				videoID.add(rs.getString("youtube_video_id"));
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
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT DISTINCT from_facebook_id FROM video WHERE to_facebook_id="
					+ pUserID;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			while (rs.next()) {
				friendsWithQueueID.add(rs.getString("from_facebook_id"));
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
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT youtube_video_id FROM video WHERE watched=0 AND from_facebook_id="
					+ pFriendID
					+ " AND to_facebook_id="
					+ pUserID
					+ " ORDER BY date_added DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			// Retrieve by column name
			rs.next();
			videoID = rs.getString("youtube_video_id");

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
		return videoID;
	}
}// end FirstExample
