package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {
	static long t0 = System.nanoTime();
	public static ArrayList<TextProcessor> textProcessors = new ArrayList<TextProcessor>();
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static MultiWordCounter counter = new MultiWordCounter(REGIONS);
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//textProcessors.add(new SingleWordCounter("nils"));
		//textProcessors.add(new SingleWordCounter("norge"));
		//textProcessors.add(new MultiWordCounter(REGIONS));
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopword = new HashSet<String>();
		while(scan.hasNext()) {
			stopword.add(scan.next().toLowerCase());
		}
		
		textProcessors.add(new GeneralWordCounter(stopword));
		scan.close();
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor t : textProcessors) {
				t.process(word);
			}
		}

		s.close();

		for(TextProcessor t : textProcessors) {
			t.report();
		}
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		// Median (HashMap): 466.2803 ms
		// Median (TreeMap): 524.6052 ms
	}
}