package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String jdbcDriver = "jdbc:apache:commons:dbcp:tkfrn";
		
		try{
			conn = DriverManager.getConnection(jdbcDriver);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(Exception e) {}
		}
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(Exception e) {}
		}
	}
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			}catch(Exception e) {}
		}
	}
}
