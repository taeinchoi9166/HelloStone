package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DBCPInit extends HttpServlet{
	@Override
	public void init() throws ServletException{
		loadJDBCDriver();
		InitPoolConnection();
	}
	private void loadJDBCDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버연결 실패");
		}	
	}
	private void InitPoolConnection() {
		try {
			String str = "jdbc:mysql://localhost/hearthstone?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
			String user = "root";
			String pw = "";
			
			ConnectionFactory cFact = new DriverManagerConnectionFactory(str,user,pw);
			PoolableConnectionFactory pFact = new PoolableConnectionFactory(cFact,null);
			pFact.setValidationQuery("select 1");
			GenericObjectPoolConfig pConfig = new GenericObjectPoolConfig();
			pConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			pConfig.setTestWhileIdle(true);
			pConfig.setMinIdle(4);
			pConfig.setMaxIdle(60);
			GenericObjectPool<PoolableConnection> conPool = new GenericObjectPool<>(pFact,pConfig);
			
			pFact.setPool(conPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver pDriver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			pDriver.registerPool("tkfrn",conPool);
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
