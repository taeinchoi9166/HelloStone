package domain;

import java.util.List;

public class CustomDeckItem {
	private String deckName;
	private String deckClass;
	private String deckType;
	private int deckNeedDust;
	private String deckLink;
	public String getDeckName() {
		return deckName;
	}
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	public String getDeckClass() {
		return deckClass;
	}
	public void setDeckClass(String deckClass) {
		this.deckClass = deckClass;
	}
	public String getDeckType() {
		return deckType;
	}
	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}
	public int getDeckNeedDust() {
		return deckNeedDust;
	}
	public void setDeckNeedDust(int deckNeedDust) {
		this.deckNeedDust = deckNeedDust;
	}
	public String getDeckLink() {
		return deckLink;
	}
	public void setDeckLink(String deckLink) {
		this.deckLink = deckLink;
	}
	
}
