package com.anchex.profilemapper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationParameters;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class LoginFrame extends JFrame 
{
	private JWebBrowser browser;
	private JPanel mainPanel;
	private Main main;
	
	public LoginFrame(Main initMain)
	{
		super("Login");
		main = initMain;
		layoutGUI();
		
	}
	
	private void layoutGUI()
	{
		mainPanel  = new JPanel();
		initBrowser();
		browser.setPreferredSize(new Dimension(820, 350));
		setListener();
		mainPanel.add(browser);
		add(mainPanel, BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initBrowser()
	{
		browser = new JWebBrowser();
		browser.setBarsVisible(false);
		browser.setJavascriptEnabled(true);
	}
	
	public void setNavigate(String url)
	{
//		browser.navigate(url);
		Map<String, String> headersMap = new HashMap<String, String>();
        headersMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.60 Safari/534.24");
        WebBrowserNavigationParameters p = new WebBrowserNavigationParameters();
        p.setHeaders(headersMap);
        browser.navigate(url, p);
		
		
		
	}
	
	private void setToken(String url)
	{
		url = url.substring(url.indexOf("#")+1);
		String[] parameter = url.split("&");
		main.setToken(parameter[0].substring(parameter[0].indexOf("=")+1));
		
	}
	
	
	
	
	private void setListener()
	{
		browser.addWebBrowserListener(new WebBrowserListener() {
			
			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void locationChanged(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				String rURL = arg0.getNewResourceLocation();
				System.out.println("redirect to : " + rURL);
				if (rURL.startsWith("http://anchex.com/renren/profilemapper/"))
				{
					setToken(rURL);
				}
			}
			
			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void loadingProgressChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
