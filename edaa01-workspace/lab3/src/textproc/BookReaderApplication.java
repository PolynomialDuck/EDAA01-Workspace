package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopword = new HashSet<String>();
		while(scan.hasNext()) {
			stopword.add(scan.next().toLowerCase());
		}
		
		GeneralWordCounter generalCounter = new GeneralWordCounter(stopword);
		scan.close();
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			generalCounter.process(word);
		}
		s.close();
		BookReaderController bookController = new BookReaderController(generalCounter);
	}

}