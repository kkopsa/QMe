import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QueueMeDatabase {	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/queue_me";
	
	static final String USER = "qmadmin";
	static final String PASS = "googleorbust";
	
	Connection conn = null;
	
	public QueueMeDatabase() 
	{		
/*	    try 
	    {
	    	//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
	        //Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
	    catch (ClassNotFoundException | SQLException e) 
	    {
			e.printStackTrace();
		}*/
	}
	
	public boolean checkTopFriends(int pUserID) 
	{
		boolean topFriends = false;
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	//STEP 2: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");
	
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT friendID, position FROM friend_ranking WHERE userID=" + pUserID + " AND position IS NOT NULL";
			  ResultSet rs = stmt.executeQuery(sql);
			
			  //STEP 5: Extract data from result set
			  topFriends = rs.next();			 
	  //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	  try{
	     if(stmt!=null)
	        stmt.close();
	  }catch(SQLException se2){
	  }// nothing we can do
	  try{
	     if(conn!=null)
	        conn.close();
	  }catch(SQLException se){
	     se.printStackTrace();
	  }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	   return topFriends;
	}//end main
	
	public boolean checkTopFriendsTest(int pUserID)
	{
		boolean topFriends = true;
		Statement stmt = null;
		try
		{
			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT friendID, position FROM friend_ranking WHERE userID=" + pUserID + " AND position IS NOT NULL";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

  
			if (!rs.next())
			{
				topFriends = false;
			}
      
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return topFriends;
}
	
	public Map<Integer, Integer> getTopFriends(int pUserID) 
	{
		Map<Integer, Integer> friendRankings = new HashMap<Integer, Integer>();
			
		Statement stmt = null;
		try
		{	    
	      //Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT friendID, position FROM friend_ranking WHERE userID=" + pUserID + " AND position IS NOT NULL";
	      System.out.println(sql);
	      ResultSet rs = stmt.executeQuery(sql);

	      // Extract data from result set
	      while(rs.next())
	      {
	         //Retrieve by column name	         
	         int friendID = rs.getInt("friendID");
	         int position = rs.getInt("position");	
	         
	         friendRankings.put(position, friendID);

	         //Display values
	         System.out.print("UserID: " + pUserID);
	         System.out.print(", FriendID: " + friendID);
	         System.out.println(", Rank: " + position);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
		}
		catch(SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
	      //finally block used to close resources
			try
	      	{
				if(stmt!=null)
	            stmt.close();
	      	}
			catch(SQLException se2)
			{
			}// nothing we can do
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
	         se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		
		return friendRankings;	
	}
	
	public List<Integer> getAllFriends(int pUserID) {
		List<Integer> friends = new ArrayList<Integer>();
		
		Connection conn = null;
	    Statement stmt = null;
	    try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT friendID FROM friend_ranking WHERE userID=" + pUserID;
	      System.out.println(sql);
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name	         
	         int friendID = rs.getInt("friendID");
	         
	         friends.add(friendID);

	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
		
		return friends;
		
	}
	
	public void test() {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
	  Class.forName("com.mysql.jdbc.Driver");
	
	  //STEP 3: Open a connection
	  System.out.println("Connecting to database...");
	  conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	  //STEP 4: Execute a query
	  System.out.println("Creating statement...");
	  stmt = conn.createStatement();
	  String sql;
	  sql = "SELECT * FROM friend_ranking";
	  ResultSet rs = stmt.executeQuery(sql);
	
	  //STEP 5: Extract data from result set
	  while(rs.next()){
	     //Retrieve by column name
	 int userID  = rs.getInt("userID");
	 int friendID = rs.getInt("friendID");
	 int position = rs.getInt("position");		         
	
	 //Display values
	 System.out.print("UserID: " + userID);
	 System.out.print(", FriendID: " + friendID);
	 System.out.println(", Rank: " + position);
	  }
	  //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	  try{
	     if(stmt!=null)
	        stmt.close();
	  }catch(SQLException se2){
	  }// nothing we can do
	  try{
	     if(conn!=null)
	        conn.close();
	  }catch(SQLException se){
	     se.printStackTrace();
	  }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	}//end FirstExample
