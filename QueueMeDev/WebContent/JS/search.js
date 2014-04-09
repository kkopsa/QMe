function search() {
	var search = $("#searchString").val();
	search = search.replace(/\s/g, '+');
	var url = 'https://www.googleapis.com/youtube/v3/search?type=video&part=snippet&maxResults=50&q=' + search + '&key=AIzaSyASNhaeu2hjaS8Pckm4SzuNGPQVkMW2N7I';
	
	$.getJSON(url)
		.done(function(json) {
			var videos = '';
			$.each(json.items, function(i, item) {
				videos += '<div>';
				videos += '<a href="/QMe/watch?v='+item.id.videoId+'">';
				videos += '<img src="'+item.snippet.thumbnails.high.url+'" style="width: 200px;" />';
				videos += '<b>'+item.snippet.title+'</b><br />';
				videos += item.snippet.description;
				videos += '</a>';
				videos += '</div>';
			});
		
			$("main").html(videos);
		})
		.error(function(xhr) {
			alert(xhr.responseText);
		});
}