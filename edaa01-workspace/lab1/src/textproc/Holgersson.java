package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		//Hittar alla undantagsord
		while(scan.hasNext()) {
			String word = scan.next();
			stopwords.add(word);
		}	
		
		//SingleWordCounter
		TextProcessor p = new SingleWordCounter("nils");
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));

		
		//MultiWordCounter
		TextProcessor mw = new MultiWordCounter(REGIONS);
		//GeneralWordCounter
		TextProcessor gwc = new GeneralWordCounter(stopwords);
				
		
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			//SingleWordCounter med lista
			for (TextProcessor tp : list) {				
			tp.process(word);			
			}
			
			//MultiWordCounter
			mw.process(word);
			
			//GeneralWordCounter
			gwc.process(word);
			
		}

		s.close();

		
		
//		for (TextProcessor tp : list) {
//			tp.report();
//			}
		
//		mw.report();
		
		gwc.report();
		
		long t1 = System.nanoTime();
		
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		
	}
}