package domain;


public class MetaItem {
	private String deck_name;
	private int deck_no;
	private int tier;
	private String deck_class;
	private int changed_no;
	private String deck_link;
	public String getDeck_name() {
		return deck_name;
	}
	public void setDeck_name(String deck_name) {
		this.deck_name = deck_name;
	}
	public int getDeck_no() {
		return deck_no;
	}
	public void setDeck_no(int deck_no) {
		this.deck_no = deck_no;
	}
	public int getTier() {
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
	}
	public String getDeck_class() {
		return deck_class;
	}
	public void setDeck_class(String deck_class) {
		this.deck_class = deck_class;
	}
	public int getChanged_no() {
		return changed_no;
	}
	public void setChanged_no(int changed_no) {
		this.changed_no = changed_no;
	}
	public String getDeck_link() {
		return deck_link;
	}
	public void setDeck_link(String deck_link) {
		this.deck_link = deck_link;
	}
	
}
