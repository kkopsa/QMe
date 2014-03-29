import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import facebook4j.Friend;


public class TopFriend {
	private Friend friend;
	private String picture;
	private int rank;
	
	TopFriend(Friend friend) {
		setFriend(friend);
		setRank(0);
	}
	
	TopFriend(Friend friend, int rank) {
		setFriend(friend);
		setRank(rank);
	}
	
	public void setFriend(Friend friend) {
		this.friend = friend;
		setPicture();
	}
	public void setRank(int rank) { this.rank = rank; }
	
	public String getId() { return friend.getId(); }
	public String getName() { return friend.getName(); }
	public String getPicture() { return picture; }
	public int getRank() { return rank; }
	
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
}