package com.anchex.profilemapper;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.services.FriendsService;

public class Test {
	
	private String sessionKey;
	private RenrenApiClient client;
	
	public Test(String key)
	{
		sessionKey = key;
		client = new RenrenApiClient(sessionKey);
		
		test();
		
	}

	public void test()
	{
		FriendsService friends = client.getFriendsService();
		JSONArray f = friends.getFriends(0, 500);
		Iterator it = f.iterator();
		
		while(it.hasNext())
		{
			JSONObject people = (JSONObject) it.next();
			String name = (String) people.get("name");
			System.out.println(name);
		}
		
	}
	
}
