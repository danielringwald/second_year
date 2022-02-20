package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	Map<String,Integer> words = new TreeMap<String, Integer>();
	Set<String> stopwords = new HashSet<String>();
	
	public GeneralWordCounter(Set<String> set) {
		stopwords = set;
	}
	
	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		if (stopwords.contains(w)) {
			
		} else {
			if (words.containsKey(w)) {
				words.put(w, words.get(w)+1);
			} else {
				words.put(w, 1);
			}
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
//		for(String ord : words.keySet()) {
//			if(words.get(ord) >= 200) {
//				System.out.println(ord + " : " + words.get(ord));
//			}
//		}
		
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 50; i++) {
			System.out.println(wordList.get(i));
		}
		
	}

	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
		}
	
}
