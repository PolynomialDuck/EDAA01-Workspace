package textproc;

import java.util.TreeMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> wordMap = new TreeMap<String, Integer>();

	public MultiWordCounter(String[] words) {
		for(String s : words) {
			wordMap.put(s, 0);
		}
	}
	
	@Override
	public void process(String w) {
		if(wordMap.containsKey(w)) {
			wordMap.put(w, (wordMap.get(w)+1));
		}
		
	}

	@Override
	public void report() {
		wordMap.forEach((x,y) -> System.out.println(x+": "+y));
		
	}
}
