package logicUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import crawl.Crawler;
import domain.ClassCount;
import domain.CustomDeckItem;
import domain.MetaItem;

public class LogicUtil {
//	public Map<String,Integer> getClassCount(List<MetaItem> list){
//		Map<String,Integer> class_map = new HashMap<String,Integer>();
//		
//		for(MetaItem item : list) {
//			if(!class_map.containsKey(item.getDeck_class())) {
//				class_map.put(item.getDeck_class(), 1);
//				
//			}else {
//				class_map.put(item.getDeck_class(),(class_map.get(item.getDeck_class())+1));
//			}
//		}
//		return class_map;
//	}
	public int getClassCount(String deck_class) {
		int n = 0;
		Crawler crl = new Crawler();
		List<MetaItem> list = crl.getMetaDataByDB();
		for(MetaItem item : list) {
			if(item.getDeck_class().trim().equals(deck_class)) {
				n++;
			}
		}
		return n;
	}
	public int getModeCount(String deck_type) {
		int n = 0;
		Crawler crl = new Crawler();
		List<CustomDeckItem> list = crl.getCustomDeckDataByDB();
		for(CustomDeckItem item : list) {
			if(item.getDeckType().trim().equals(deck_type)) {
				n++;
			}
		}
		return n;
	}
}
