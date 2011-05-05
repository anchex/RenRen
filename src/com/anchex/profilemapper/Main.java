package com.anchex.profilemapper;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.renren.api.client.RenrenApiConfig;


public class Main 
{
	private static String API_KEY = RenrenApiConfig.renrenApiKey;
	private static String API_SEC = RenrenApiConfig.renrenApiSecret;
	private static LoginFrame loginFrame;
	private static String token;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
//		NativeSwing.initialize();
		NativeInterface.open();
		
		System.out.print(authorizedURL());
	    
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginFrame = new LoginFrame();
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
//		String rURL = "redirect_uri=http://graph.renren.com/oauth/login_success.html";
//		String state = "state=abc&";
		String id = "client_id=" + API_KEY;
		String url = aURL+id+type+rURL;
		
		return url;
	}
	
	public static void setToken(String t)
	{
		token = t;
		System.out.println("token = "+token);
		JOptionPane.showMessageDialog(null, "token has been setted.\n"+token);
	}
}
