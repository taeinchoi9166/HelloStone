package domain;

import java.sql.Timestamp;

public class MyDeckItem {
	private int id;
	private String user;
	private String deck_name;
	private String deck_class;
	private String deck_type;
	private String deck_link;
	private Timestamp saved_date;
	
	public MyDeckItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyDeckItem(int id, String user, String deck_name, String deck_class, String deck_type, String deck_link,
			Timestamp saved_date) {
		super();
		this.id = id;
		this.user = user;
		this.deck_name = deck_name;
		this.deck_class = deck_class;
		this.deck_type = deck_type;
		this.deck_link = deck_link;
		this.saved_date = saved_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDeck_name() {
		return deck_name;
	}
	public void setDeck_name(String deck_name) {
		this.deck_name = deck_name;
	}
	public String getDeck_class() {
		return deck_class;
	}
	public void setDeck_class(String deck_class) {
		this.deck_class = deck_class;
	}
	public String getDeck_type() {
		return deck_type;
	}
	public void setDeck_type(String deck_type) {
		this.deck_type = deck_type;
	}
	public String getDeck_link() {
		return deck_link;
	}
	public void setDeck_link(String deck_link) {
		this.deck_link = deck_link;
	}
	public Timestamp getSaved_date() {
		return saved_date;
	}
	public void setSaved_date(Timestamp saved_date) {
		this.saved_date = saved_date;
	}
	
}
