package textproc;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor{
	private Map<String, Integer> wordMap = new TreeMap<String, Integer>();
	private Set<String> badWords = new HashSet<String>();
	
	public GeneralWordCounter(Set<String> words) {
		badWords = words;
	}
	@Override
	public void process(String w) {
		if(wordMap.containsKey(w)) {
			wordMap.put(w, (wordMap.get(w)+1));
		}
		else if(!badWords.contains(w)) {
			wordMap.put(w, 1);
		}
		
	}

	@Override
	public void report() {
		//wordMap.forEach((x,y) -> 
		//{if(y>=200) {System.out.println(x+": "+y);} });
		Set<Map.Entry<String, Integer>> wordSet = wordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i < 100; i++) {
			System.out.println(wordList.get(i));
		}
	}
	public List<Map.Entry<String, Integer>> getWordList() {
		Set<Map.Entry<String, Integer>> wordSet = wordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		return wordList;
	}
}