package com.anchex.profilemapper;
import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.anchex.profilemapper.frames.LoginFrame;
import com.renren.api.client.RenrenApiConfig;
import com.renren.api.client.utils.HttpURLUtils;


public class Main 
{
	private static final String API_KEY = RenrenApiConfig.renrenApiKey;
	private static final String API_SEC = RenrenApiConfig.renrenApiSecret;
	private LoginFrame loginFrame;
	private String token, sessionKey;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    new Main().bootStrap();
	}

	
	public void bootStrap(){
		NativeInterface.open();
	    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginFrame = new LoginFrame(Main.this);
				String url = authorizedURL();
				loginFrame.setNavigate(url);
				loginFrame.setVisible(true);
			}
		});
	     NativeInterface.runEventPump(); 
	}
	
	public static String authorizedURL()
	{
		String type = "&response_type=token&";
		String aURL = "https://graph.renren.com/oauth/authorize?";
		String rURL = "redirect_uri=http://anchex.com/renren/profilemapper/";
		String id = "client_id=" + API_KEY;
		String url = aURL+id+type+rURL;
		return url;
	}
	
	public void setToken(String t) throws UnsupportedEncodingException
	{
		token = t;
		token = URLDecoder.decode(token, "UTF-8");
		System.out.println("token = "+token);
		loginFrame.dispose();
		JOptionPane.showMessageDialog(null, "token has been setted.\n"+token);
		setSessionKey();
	}
	
	public void setSessionKey()
	{
		String link = "https://graph.renren.com/renren_api/session_key?oauth_token="+token;
		String response = HttpURLUtils.doGet(link);
		
		System.out.println(response);
		JSONObject jObj = (JSONObject)JSONValue.parse(response);
		JSONObject renren = (JSONObject) jObj.get("renren_token");
		sessionKey = (String) renren.get("session_key");
		
		System.out.println(sessionKey+"\n\n");
		
		new Test(sessionKey);
		
	}
}
