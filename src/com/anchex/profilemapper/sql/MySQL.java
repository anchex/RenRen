package com.anchex.profilemapper.sql;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL
{
	public String url = "jdbc:mysql://";
	public String user;
	public String password ;
	public String e;
	public Connection connection;
	public Statement statement;
	public ResultSet result;
	
	
	public MySQL(){}
	
	public MySQL(String u, String m, String p)
	{
		url += u;
		user = m;
		password = p;
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
//		result = statement.executeQuery(e);
//		return result;
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