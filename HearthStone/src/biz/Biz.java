package biz;

import java.util.List;

import dao.Dao;
import domain.LoginEntity;
import domain.MyDeckItem;

public class Biz {
	public int insertMyDeckItem(MyDeckItem item) {
		Dao dao = new Dao();
		int n = dao.insertMyDeckData(item);
		return n;
	}
	public List<MyDeckItem> selectMyDeckItem(String user){
		Dao dao = new Dao();
		List<MyDeckItem> list = dao.selectMyDeckItem(user);
		return list;
	}
	public int registerUser(String id,String pw) {
		Dao dao = new Dao();
		int n = dao.userRegister(id,pw);
		return n;
	}
	public LoginEntity loginUser(String id,String pw) {
		Dao dao = new Dao();
		LoginEntity ob = dao.userLogin(id, pw);
		return ob;
	}
}
