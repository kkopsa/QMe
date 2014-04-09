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
	<script src="JS/sendVideo.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			var empty = ${playlistEmpty};
			if (empty) {
				$("#player").addClass("empty");
			}
		});
	</script>
	<style>
		body > nav {
			padding-left: 8px;
		}
		body > nav a {
			display: block;
			margin-right: 12px;
			width: 50px;
			height: 100%;
			float: left;
		}
	</style>
</head>
<body>
	<nav>
		<a href="/QMe/Home"></a>
		<form id="search" onsubmit="search(); return false;">
			<input type="search" id="searchString" placeholder="Search YouTube">
		</form>
	</nav>
	<main>
		<div id="player">
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
					<c:forEach items="${videos}" var="video">
						<li><span onclick="load('${video}')">${video}</span></li>
					</c:forEach>
				</ul>
			</nav>
		</div>
		<form onsubmit="sendVideo(); return false;">
			<input id="videoId" type="hidden" name="videoId" value="${v}">
			<select id="friends" name="friends" data-placeholder="Search Facebook Friends" style="width: 500px;" multiple class="chosen-select" tabindex="8">
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