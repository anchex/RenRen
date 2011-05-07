package com.anchex.profilemapper.sql;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

public class MySQL
{
	private final String utf = "?useUnicode=true&characterEncoding=utf8";
	private String url = "jdbc:mysql://";
	private String user;
	private String password ;
	private String e;
	private Connection connection;
	private Statement statement;
	private ResultSet result;
	private boolean useUTF;
	
	
	public MySQL(){}
	
	public MySQL(String u, String m, String p)
	{
		url += u + utf;
		user = m;
		password = p;
		useUTF = true;
	}
	
	public MySQL(String u, String m, String p, boolean useutf)
	{
		if (useutf)
			url += u + utf;
		else 
			url += u;
		user = m;
		password = p;
		useUTF = useutf;
	}
	
	public MySQL getConnected() throws SQLException
	{
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement();
		return this;
	}
	
	public MySQL setCommand(String c)
	{
		e = c;
		return this;
	}
	
	public void excute() throws SQLException
	{
		statement.execute(e);
	}
	
	public ResultSet excuteQuery() throws SQLException
	{
		result = statement.executeQuery(e);
		return result;
	}
	
	public ResultSet getResultSet()
	{
		return result;
	}
	
	public void insert(String tableName,Map<String,String> map) throws SQLException
	{
		String title = "INSERT INTO "+tableName+" ";
		String key = "(";
		String value = "(";
		Iterator<String> itKey = map.keySet().iterator();
		while(itKey.hasNext())
		{
			String k = itKey.next();
			key += k+", ";
			value += map.get(k) + ", ";
		}
		key = key.substring(0, key.length()-2);
		key += ")";
		value = value.substring(0, value.length()-2);
		value += ")";
		
		String e = title + key + " VALUES " + value;
		statement.execute(e);
		
	}
	
	
	
	
	
	public static void main(String arg[])
	{
		MySQL sql = new MySQL("anchex.com/anchexco_test?useUnicode=true&characterEncoding=utf8",
				"anchexco_test","123");
		try {
			String s = "INSERT INTO renren VALUES (29681660, \"李博\", \"http://hdn.xnimg.cn/photos/hdn121/20110424/1720/head_TNkn_13916a019117.jpg\", \"http://hdn.xnimg.cn/photos/hdn121/20110424/1720/tiny_gQjY_13917a019117.jpg\");";
			String str = new String(s.getBytes("UTF8"), "UTF8");
			sql.getConnected().setCommand(s);
			sql.excute();
//			System.out.print(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}