package com.anchex.profilemapper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.anchex.profilemapper.sql.MySQL;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.services.FriendsService;
import com.renren.api.client.utils.HttpURLUtils;

public class Test {
	
	private String sessionKey;
	private RenrenApiClient client;
	private MySQL sql;
	
	public Test(String key)
	{
		sessionKey = key;
		client = new RenrenApiClient(sessionKey);
		sql = new MySQL("anchex.com",
				"anchexco_test","123",true);
		try {
			sql.getConnected();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test();
		
	}

	public void test()
	{
		FriendsService friends = client.getFriendsService();
		JSONArray f = friends.getFriends(0, 500);
//		Iterator it = f.iterator();
//		
//		while(it.hasNext())
//		{
//			JSONObject people = (JSONObject) it.next();
//			String name = "\""+(String) people.get("name")+"\"";
//			Long id = (Long)people.get("id");
//			String headurl = "\""+(String) people.get("headurl")+"\"";
//			String tinyurl = "\""+(String) people.get("tinyurl")+"\"";
//			
//			String c = "INSERT INTO renren VALUES ("+id+", "+name+", "+headurl+", "+tinyurl+");";
//			
//			
//			sql.setCommand(c);
//			try {
//				sql.excute();
//				System.out.println(c);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put("data", f.toJSONString());
		String url = "http://anchex.com/renren/profilemapper/receiver.php";
		String get = HttpURLUtils.doPost(url, map);
		System.out.println(get);
//		System.out.println(f.toJSONString());
		System.exit(0);
		
	}
	
}
