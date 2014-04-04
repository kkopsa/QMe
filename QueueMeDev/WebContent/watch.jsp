<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
	<title>Watch | QMe</title>
	<link rel="stylesheet" href="style/index.css" />
	<link rel="stylesheet" href="style/playlist.css" />
	<link rel="stylesheet" href="style/chosen.css" />
	<script src="JS/jquery.min.js" type="text/javascript"></script>
	<script src="JS/search.js" type="text/javascript"></script>
	<script src="JS/controls.js" type="text/javascript"></script>
	<script src="JS/chosen.jquery.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			if ($("#player").children("nav").eq(0).children("ul").eq(0).children().length == 0) {
				$("#player").addClass("empty");
			}
		});
	</script>
</head>
<body>
	<nav>
		<form id="search" onsubmit="search(); return false;">
			<input type="search" id="searchString" placeholder="Search YouTube">
		</form>
	</nav>
	<main>
		<div id="player" class="empty">
			<div id="video"></div>
			<script type="text/javascript">
				var video = "${v}";
				var tag = document.createElement('script');
				tag.src = "https://www.youtube.com/iframe_api";
				var firstScriptTag = document.getElementsByTagName('script')[0];
				firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
				var player;
				function onYouTubeIframeAPIReady() {
					player = new YT.Player('video', {
						videoId: video,
						playerVars: { 'rel': 0 }
					});
				}
			</script>
			<nav>
				<ul>
					<!-- <li><span onclick="load('y2M8BbTfZTA')">An Unexpected Journey</span></li>
					<li><span onclick="load('OPVWy1tFXuc')">Desolation of Smaug</span></li>
					<li><span onclick="load('mllXxyHTzfg')">I See Fire</span></li> -->
				</ul>
			</nav>
		</div>
		<form method="post" action="">
			<select name="friends" data-placeholder="Search Facebook Friends" style="width: 500px;" multiple class="chosen-select" tabindex="8">
				<option value=""></option>
				<c:forEach items="${friends}" var="friend">
					<option value="${friend.id}">${friend.name}</option>
				</c:forEach>
			</select>
			<button type="submit">Send Video</button>
		</form>
		<script type="text/javascript">
			$('.chosen-select').chosen();
		</script>
	</main>
</body>
</html>