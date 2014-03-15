<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="java.net.URL" %>
<%@ page import="java.util.*" %>
<%@ page import="facebook4j.Friend" %>
<%@ page import="facebook4j.ResponseList" %>

<%@ page import="com.fasterxml.jackson.core.JsonParseException" %>
<%@ page import="com.fasterxml.jackson.databind.JsonMappingException" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
	<%
		ResponseList<Friend> friends = (ResponseList<Friend>)request.getSession().getAttribute("friends");
		for(Friend friend : friends) {
			out.write(friend.getName()+"<br />");
			String id = friend.getId();
			
			URL url = new URL("http://graph.facebook.com/"+id+"?fields=picture.width(500)");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(url, Map.class);
			
			String json = map.get("picture").toString();
			String prefix = "{data={url=";
			String noPrefix = json.substring(json.indexOf(prefix) + prefix.length());
			String parts[] = noPrefix.split(",");
			String picUrl = parts[0];
			out.write("<img src=\""+picUrl+"\"/><br />");
		}
	%>
</body>
</html>