import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import facebook4j.Friend;


public class User {
	private String username;
	private String id;
	private String picture;
	private String videoID;
		
	User(Friend pFriend) {		
		id = pFriend.getId();
		username = pFriend.getName();
		//setPicture();
	}
	
	User(String pUsername, String pId) {
		id = pId;
		username = pUsername;
		//setPicture();
	}
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPicture() {
		return picture;
	}



	private void setPicture() {
		URL url;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = null;
		try {
			url = new URL("http://graph.facebook.com/"+getId()+"?fields=picture.width(200).type(square)");
			map = mapper.readValue(url, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String json = map.get("picture").toString();
		String prefix = "{data={url=";
		String noPrefix = json.substring(json.indexOf(prefix) + prefix.length());
		String parts[] = noPrefix.split(",");
		picture = parts[0];
	}	

	public String getVideoID() {
		return videoID;
	}

	public void setThumbnail(String pUserID) {
		QueueDatabase qdb = new QueueDatabase();
		videoID = qdb.getThumbnailVideoID(pUserID, id);
	}
	
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

}
