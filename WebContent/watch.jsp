<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!doctype html>
<html>
<head>
	<title>Watch | QMe</title>
	<link rel="stylesheet" href="style/index.css" />
	<link rel="stylesheet" href="style/playlist.css" />
	<script src="JS/jquery.min.js" type="text/javascript"></script>
	<script src="JS/search.js" type="text/javascript"></script>
	<script src="JS/controls.js" type="text/javascript"></script>
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
	</main>
</body>
</html>