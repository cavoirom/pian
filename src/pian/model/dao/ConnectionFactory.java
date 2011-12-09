package pian.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {
	
//	public static Connection getConnection(){
//		Connection connection = null;
//		InitialContext ctx;
//		try {
//			ctx = new InitialContext();
//			Context envContext  = (Context)ctx.lookup("java:comp/env"); 
//			DataSource ds = (DataSource)envContext.lookup("jdbc/PianDB");
//			connection = ds.getConnection();
//			System.out.println("-----------------ok-------------------");
//		} catch (Exception e) {
//			//System.out.println("Can't connect as connection pool. System will create a normal connection.");
//			try {
//				Class.forName("org.sqlite.JDBC");
//				connection = DriverManager.getConnection("jdbc:sqlite:pian.sqlite");
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		} 
//		return connection;
//	}
	
	private static DataSource dataSource;

	public static String dataSourceName;

	public static String driverName;

	public static String password;

	public static String url;

	public static String username;

	static {
		ConnectionFactory.dataSourceName = "jdbc/PianDB";
		ConnectionFactory.driverName = "com.mysql.jdbc.Driver";
		ConnectionFactory.url = "jdbc:mysql://127.0.0.1:3306/pian";
		ConnectionFactory.username = "root";
		ConnectionFactory.password = "";
		try {
			Context envCtx = (Context) new InitialContext()
					.lookup("java:comp/env");
			ConnectionFactory.dataSource = (DataSource) envCtx
					.lookup(ConnectionFactory.dataSourceName);
		} catch (Exception e) {}
	}

	public static Connection getConnection() {
		Connection result = null;
		try {
			result = ConnectionFactory.dataSource.getConnection();
			result.setAutoCommit(true);
		} catch (Exception e) {
			try {
				Class.forName(driverName);
				result = DriverManager.getConnection(ConnectionFactory.url, ConnectionFactory.username, ConnectionFactory.password);
				result.setAutoCommit(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(ConnectionFactory.getConnection());
	}
}
