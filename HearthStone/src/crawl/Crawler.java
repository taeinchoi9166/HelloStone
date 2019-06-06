package crawl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import dao.Dao;
import domain.CustomDeckItem;
import domain.MetaItem;

public class Crawler {
	private Crawler cwl;
	
	public List<MetaItem> getMetaData(){
		List<MetaItem> meta_list  = null;
		try {
			WebClient client = new WebClient();
			client.getOptions().setJavaScriptEnabled(true);
			client.getOptions().setThrowExceptionOnScriptError(false);
			client.getCookieManager().setCookiesEnabled(true);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			
			HtmlPage page1 = client.getPage("https://www.hearthstudy.com/ranking");
			DomElement doel = page1.querySelector("#ranklist");
			Document doc = Jsoup.parse(doel.asXml());
			//System.out.println(doc);
			Elements els = doc.select("a >.tbr");
			Elements a_tags = doc.select("a[role=button]");
			int cnt = 0;
			meta_list = new ArrayList<MetaItem>();
			for(Element tmp : a_tags) {
			//	System.out.println(cnt+" "+tmp.select("div:nth-child(2)>h3").text());
				
				MetaItem item = new MetaItem();
				item.setDeck_no(Integer.parseInt(tmp.select("div:first-child>h3").text()));
				item.setDeck_name(tmp.select("div:nth-child(3)>h4>strong").text());
				String rank_text = tmp.select("div:nth-child(2)>h3").text();
				if(!((rank_text.equals("New")||rank_text.equals("")))) {
					item.setChanged_no(tmp.select("div:nth-child(2)>h3>i").attr("class").equals("fa fa-arrow-up") ? Integer.parseInt(tmp.select("div:nth-child(2)>h3").text()) : Integer.parseInt(tmp.select("div:nth-child(2)>h3").text())*-1);
				}else if(rank_text.equals("")){
					item.setChanged_no(0);
				}else {
					item.setChanged_no(100);
				}
				
				item.setDeck_link("https://www.hearthstudy.com/deck/"+tmp.attr("data-repremeta").toString());
				Elements sec_els = doc.select(".collapse");
				String el = sec_els.get(cnt).select("h4").text();
				item.setDeck_class(doc.select("a.viewrankdeck").get(cnt).select(".viewrankdeck>.tbr>div:nth-child(3)").attr("class").replace("col-xs-6 col-sm-8 text-left stroke rank","").toLowerCase());
				item.setTier(Integer.parseInt(Character.toString(el.charAt(0))));
				
				meta_list.add(item);
				cnt++;
				
			}
			
		
		}catch(Exception e) {
			//e.printStackTrace();
		}
		return meta_list;
	}
	public List<CustomDeckItem> getCustomDeckData(){
		List<CustomDeckItem> custom_list  = null;
		try {
			WebClient client = new WebClient();
			client.getOptions().setJavaScriptEnabled(true);
			client.getOptions().setThrowExceptionOnScriptError(false);
			client.getCookieManager().setCookiesEnabled(true);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			
			HtmlPage page1 = client.getPage("http://hs.inven.co.kr/dataninfo/deck/new/");
			DomElement doel = page1.querySelector(".hsDbCommonList>table");
			Document doc = Jsoup.parse(doel.asXml());
			
			//System.out.println(doc);
			Elements els = doc.select("tbody>tr");
			//System.out.println(els);
			custom_list = new ArrayList<>();
			for(Element tmp : els) {
				
				
				CustomDeckItem item = new CustomDeckItem();
				if(!tmp.attr("class").equals("list-info")) {
					String class_no =tmp.select(".icon>img").attr("src").replace("http://static.inven.co.kr/image_2011/hs/dataninfo/deck/icon_class_", "").replace(".gif?v=2","");
					switch(class_no) {
						case "10":
							class_no = "warrior";
							break;
						case "2":
							class_no = "druid";
							break;
						case "3":
							class_no = "hunter";
							break;
						case "4":
							class_no = "mage";
							break;
						case "5":
							class_no = "paladin";
							break;
						case "6":
							class_no = "priest";
							break;
						case "7":
							class_no = "rogue";
							break;
						case "8":
							class_no = "shaman";
							break;
						case "9":
							class_no = "warlock";
							break;
					}
					item.setDeckClass(class_no);
					item.setDeckName(tmp.select(".subject>a").text());
					item.setDeckType(tmp.select(".subject>span").attr("class").equals("icon-standard") ? "정규" : "야생");
					item.setDeckNeedDust(Integer.parseInt(tmp.select(".dust").text()));
					item.setDeckLink("http://hs.inven.co.kr/dataninfo/deck/new/"+tmp.select("td:nth-child(3)>a").attr("href").substring(1));
					custom_list.add(item);
				}
				
				
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  custom_list;
	}
	public int updateMetaDeta()
	{
		int n = 0;
		//SimpleDateFormat sdf = new SimpleDateFormat();
		Timestamp cur_stamp = new Timestamp(System.currentTimeMillis());
		Dao dao = new Dao();
		Timestamp last_stamp = dao.getLastTimestamp();
		if(last_stamp==null||(cur_stamp.getTime() - last_stamp.getTime())>=1000*60*5) {
		
			n = dao.insertNewLog();
			if(n>0) {
				System.out.println("로그 박기 성공"+(cur_stamp.getTime() - last_stamp.getTime()));
				
			}else{
				System.out.println("로그 박기 실패"+(cur_stamp.getTime() - last_stamp.getTime()));
				
			}
			
		}else {
			return -1;
		}
		cwl = new Crawler();
		List<MetaItem> list = cwl.getMetaData();
		List<CustomDeckItem> list2 = cwl.getCustomDeckData();
		int m = 0;
		for(MetaItem item : list) {
			//System.out.println(item);
			m+= dao.insertMetaData(item, n);
		}
		for(CustomDeckItem item : list2) {
			m+= dao.insertCustomData(item, n);
		}
		if(m>=(list.size()+list2.size())) {
			return n;
		}else{
			return 0;
		}
		
	}
	
	public List<MetaItem> getMetaDataByDB() {
		Dao dao = new Dao();
		List<MetaItem> list = dao.selectMetaItem();
		return list;
	}
	public List<CustomDeckItem> getCustomDeckDataByDB(){
		Dao dao = new Dao();
		List<CustomDeckItem> list = dao.selectCustomDeckItem();
		return list;
	}
}
