package filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StopWordsFilter {

	
	ArrayList<String> stopWords;
	
	public StopWordsFilter() throws IOException{
		
		FileReader file = null;
		
		file = new FileReader("resources/stopwords.txt");
		
		BufferedReader reader = new BufferedReader(file);
		String read = "";
		ArrayList<String> stopWords = new ArrayList<String>();
		do {

			try {
				read = reader.readLine();
				if (read != "" && read!=null) {
					stopWords.add(read);
				}
			} catch (Exception e) {
				read = "";
			}
		} while (read != "" && read != null);
		reader.close();
		this.stopWords = stopWords;
	}
	
	
	
	public ArrayList<String> removeStopWords(ArrayList<String> tokens) {
		
		ArrayList<String> newTokens = new ArrayList<String>();
		for (int i = 0; i < tokens.size(); i++) {
			if (!stopWords.contains(tokens.get(i))) {
				newTokens.add(tokens.get(i));
			};
		}
	
		return newTokens;
	}
	
	
	
}
