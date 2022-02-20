package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> map1, Entry<String, Integer> map2) {
		
		if(map1.getValue() < map2.getValue()) {
			return 1;
		} else if(map1.getValue() > map2.getValue()) {
			return -1;
		}
		
		if(map1.getKey().compareTo(map2.getKey()) < 0) {
			return -1;
		} 
		return 0;
	}

}