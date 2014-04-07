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

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<link rel="stylesheet" href="style/index.css"/>
	<title>Home</title>
	<script src="JS/jquery.min.js" type="text/javascript"></script>
	<script src="JS/search.js" type="text/javascript"></script>
	<script src="JS/loadtiles.js" type="text/javascript"></script>
</head>
<body>
	<nav>
		<form id="search" onsubmit="search(); return false;">
			<input type="search" id="searchString" placeholder="Search YouTube">
		</form>
	</nav>
<main>	
	<c:forEach items = "${friendTiles}" var = "friend">
		<div class="tile">
			<a href="/QMe/watch?from=${friend.id}">
			<img src="http://img.youtube.com/vi/${friend.videoID}/hqdefault.jpg"><br />
			${friend.username}</a>
		</div>
	</c:forEach>
</main>
</body>
</html>