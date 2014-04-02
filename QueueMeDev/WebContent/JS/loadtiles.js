/**
 * 
 */
function showThumbnails() {
	var videoID = "";
	var friendName = "";
	var imagehtml = "";
	for (var i = 0; i < 6; i++) {
		videoID = "J---aiyznGQ"; // TODO get first videoID in friends
									// playlist
		friendName = "Kevin Kopsa"; // TODO get friends name based on ranking
		friendName += "<br /></div>";
		imagehtml = "<div class=\"tile\">";
		imagehtml += "<a href=\"/QueueMeDev/watch?v=" + videoID + "\">";
		imagehtml += "<img src=\"http://img.youtube.com/vi/" + videoID
				+ "/default.jpg\">";
		imagehtml += "</a><br />";
		document.write(imagehtml);
		document.write(friendName);
	}
}

function otherFriends() {
	var videoID = "";
	var friendName = "";
	for (var i = 0; i < 6; i++) {
		videoID = "QlwilbVYvUg"; // TODO get first videoID in friends
									// playlist
		friendName = "Taylor Skinner"; // TODO get friends name based on random
										// order facebook returns(for now)
		document
				.write("<div class=\"tile\"><img src=\"http://img.youtube.com/vi/"
						+ videoID + "/default.jpg\"><br />");
		document.write(friendName + "<br /></div>");
	}
}