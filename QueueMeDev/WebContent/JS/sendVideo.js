function getTitle(videoId) {
	var url = 'https://www.googleapis.com/youtube/v3/search?type=video&part=snippet&maxResults=1&q=' + videoId + '&key=AIzaSyASNhaeu2hjaS8Pckm4SzuNGPQVkMW2N7I&jsoncallback=?';
	
	$.getJSON(url)
		.done(function(json) {
			var title = json.items[0].snippet.title;
//			$.each(json.items, function(i, item) {
//				title = item.snippet.title;
//				alert(title);
//			});
			return title;
		})
		.error(function(xhr) {
			//alert(xhr.responseText);
		});
	
	return "";
}

function sendVideo() {
	var id = $("#videoId").val();
	var title = getTitle(videoId);
	
	var friendList = new Array();
	$("#friends option:selected").each(function() {
		friendList.push($(this).val());
	});	
	
	$.post("/QMe/AddVideo", {videoId: id, videoTitle: title, friends: friendList});
	
}