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
	private static final String API_KEY = RenrenApiConfig.renrenApiKey;
	private static final String API_SEC = RenrenApiConfig.renrenApiSecret;
	private LoginFrame loginFrame;
	private String token;

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
	
	public void setToken(String t)
	{
		token = t;
		System.out.println("token = "+token);
		JOptionPane.showMessageDialog(null, "token has been setted.\n"+token);
	}
}
