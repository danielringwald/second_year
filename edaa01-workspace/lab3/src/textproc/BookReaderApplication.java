package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while(scan.hasNext()) {
			String word = scan.next();
			stopwords.add(word);
		}	//Hittar alla undantagsord
		
		scan.close();
		
		GeneralWordCounter gwc = new GeneralWordCounter(stopwords);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

//			GeneralWordCounter
			gwc.process(word);
			
		}

		s.close();
		
		BookReaderController brc = new BookReaderController(gwc);
		
	}

}
