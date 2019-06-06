package dao;

import static jdbc.JdbcUtil.close;
import static jdbc.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import domain.CustomDeckItem;
import domain.LoginEntity;
import domain.MetaItem;
import domain.MyDeckItem;

public class Dao {
	public Timestamp getLastTimestamp() {
		Connection conn = getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Timestamp ts = null;
			
			String sql = "select * from page_log order by id desc limit 0,1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ts = rs.getTimestamp("log_time");
			}else{
				return null;
			}
			
			return ts;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
	}

	public int insertNewLog() {
		Connection conn = getConnection();
		if(conn==null) {
			return -1;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "insert into page_log(log_time) values(?)";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()){
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
	}
	public int insertMetaData(MetaItem item,int last_num) {
		Connection conn = getConnection();
		if(conn==null) {
			return -1;
		}
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into meta_decks(id,deck_name,deck_no,deck_changed_no,deck_class,deck_tier,deck_link) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, last_num);
			pstmt.setString(2,item.getDeck_name());
			pstmt.setInt(3,item.getDeck_no());
			pstmt.setInt(4, item.getChanged_no());
			pstmt.setString(5, item.getDeck_class());
			pstmt.setInt(6, item.getTier());
			pstmt.setString(7, item.getDeck_link());
			
			int n = pstmt.executeUpdate();
			
			if(n>0) {
				return n;
			}else{
				return 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			close(pstmt);
			close(conn);
		}
	}
	public List<MetaItem> selectMetaItem(){
		List<MetaItem> list = null;
		Connection conn = getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select id from page_log order by id desc limit 0,1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int last_id = 1;
			if(rs.next()) {
				last_id = rs.getInt("id");
			}
			
			
			sql = "select * from meta_decks where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, last_id);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				MetaItem tmp = new MetaItem();
				tmp.setDeck_name(rs.getString("deck_name"));
				tmp.setDeck_no(rs.getInt("deck_no"));
				tmp.setChanged_no(rs.getInt("deck_changed_no"));
				tmp.setDeck_class(rs.getString("deck_class"));
				tmp.setTier(rs.getInt("deck_tier"));
				tmp.setDeck_link(rs.getString("deck_link"));
				list.add(tmp);
			}
			
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
	}
	public int insertCustomData(CustomDeckItem item,int last_num) {
		Connection conn = getConnection();
		if(conn==null) {
			return -1;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "insert into custom_decks(id,deck_name,deck_type,deck_class,deck_dust,deck_link) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, last_num);
			pstmt.setString(2, item.getDeckName());
			pstmt.setString(3, item.getDeckType());
			pstmt.setString(4, item.getDeckClass());
			pstmt.setInt(5, item.getDeckNeedDust());
			pstmt.setString(6, item.getDeckLink());
			
			int n = pstmt.executeUpdate();
			
			if(n>0) {
				return 1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			close(pstmt);
			close(conn);
		}
	}
	public List<CustomDeckItem> selectCustomDeckItem(){
		Connection conn = getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CustomDeckItem> list = null;
		
		try {
			String sql = "select id from page_log order by id desc limit 0,1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int last_id = 1;
			if(rs.next()) {
				last_id = rs.getInt("id");
			}
			
			sql = "select * from custom_decks where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, last_id);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CustomDeckItem>();
			while(rs.next()) {
				CustomDeckItem item = new CustomDeckItem();
				item.setDeckName(rs.getString("deck_name"));
				item.setDeckClass(rs.getString("deck_class"));
				item.setDeckType(rs.getString("deck_type"));
				item.setDeckNeedDust(rs.getInt("deck_dust"));
				item.setDeckLink(rs.getString("deck_link"));
				list.add(item);
				
			}
			
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}		
	}
	public int insertMyDeckData(MyDeckItem item) {
		Connection conn = getConnection();
		if(conn==null) {
			return 0;
		}
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into saved_decks(user,deck_name,deck_class,deck_type,deck_link) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,item.getUser());
			pstmt.setString(2, item.getDeck_name());
			pstmt.setString(3,item.getDeck_class());
			pstmt.setString(4,item.getDeck_type());
			pstmt.setString(5, item.getDeck_link());
			int n = pstmt.executeUpdate();
			if(n>0) {
				return 1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			close(pstmt);
			close(conn);
		}
	}
	public List<MyDeckItem> selectMyDeckItem(String user){
		Connection conn = getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MyDeckItem> list = null;
		
		try {
			String sql = "select * from saved_decks where user = ? order by id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			
				
			list = new ArrayList<MyDeckItem>();
			while(rs.next()) {
				MyDeckItem item = new MyDeckItem();
				item.setId(rs.getInt("id"));
				item.setDeck_name(rs.getString("deck_name"));
				item.setDeck_class(rs.getString("deck_class"));
				item.setDeck_type(rs.getString("deck_type"));
				item.setDeck_link(rs.getString("deck_link"));
				item.setSaved_date(rs.getTimestamp("saved_date"));
				list.add(item);
				
			}
			
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}		
	}

	public LoginEntity userLogin(String id, String pw) {
		Connection conn = getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user_table where id = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			rs = pstmt.executeQuery();
			
			LoginEntity entity = new LoginEntity();	
			if(rs.next()) {
				entity.setId(rs.getString("id"));
				entity.setPw(rs.getString("pw"));	
			}
			
			return entity;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}		
	}

	public int userRegister(String id, String pw) {
		Connection conn = getConnection();
		if(conn==null) {
			return 0;
		}
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into user_table(id,pw) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,pw);
			
			int n = pstmt.executeUpdate();
			if(n>0) {
				return 1;
			}else {
				return 0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			close(pstmt);
			close(conn);
		}	
		
	}
}


