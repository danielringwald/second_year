package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{
	Map<String,Integer> landskap = new TreeMap<String,Integer>();
	
	public MultiWordCounter(String[] landskap) {
		
		for(int i = 0; i < landskap.length; i++) {
			this.landskap.put(landskap[i],0);
		}
		
	}
	
	@Override
	public void process(String w) {
		
		if(landskap.containsKey(w)) {
			landskap.put(w,landskap.get(w)+ 1);
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		for(String key : landskap.keySet()) {
			System.out.println(key + " : " + landskap.get(key));
		}
	}

}
