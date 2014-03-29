<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<%@ page import="java.net.URL" %>
<%@ page import="java.util.*" %>
<%@ page import="facebook4j.Friend" %>
<%@ page import="facebook4j.ResponseList" %>

<%@ page import="com.fasterxml.jackson.core.JsonParseException" %>
<%@ page import="com.fasterxml.jackson.databind.JsonMappingException" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Home</title>
</head>
<body>
Something here for testing purposes<br />
<main>
	<script type="text/javascript">
		
		//if no top friends, show add friends button
		var topFriendSet=new Boolean();
		topFriendSet = '${topFriendSet}';
		if (topFriendSet == 'false')
			{
			document.write("<button>Select Top Friends</button><br />");
			}
		else
			{
			var videoID=""; 
			var friendName="";
			for (var i = 0; i < 6; i++)
				{
				videoID="J---aiyznGQ";    //TODO get first videoID in friends playlist
				friendName="Kevin Kopsa"; //TODO get friends name based on ranking
				document.write("<div class=\"tile\"><img src=\"http://img.youtube.com/vi/" + videoID + "/default.jpg\"><br />");
				document.write(friendName + "<br /></div>");
				}
			}	
	
	
		var videoID="";
		var friendName="";
		for (var i = 0; i < 6; i++)
		{
			videoID="QlwilbVYvUg";       //TODO get first videoID in friends playlist
			friendName="Taylor Skinner"; //TODO get friends name based on random order facebook returns(for now)
			document.write("<div class=\"tile\"><img src=\"http://img.youtube.com/vi/" + videoID + "/default.jpg\"><br />");
			document.write(friendName + "<br /></div>");
		}
	</script>
	
</main>
</body>
</html>