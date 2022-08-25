package filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KeyWordsFilter {

	private ArrayList<String> keywords;
	
	public KeyWordsFilter() throws IOException {
		FileReader file = new FileReader("resources/key-words.txt");
		BufferedReader reader = new BufferedReader(file);
		String read = "";
		ArrayList<String> keywords = new ArrayList<String>();
		do {
			try {
				read = reader.readLine();
				if (read != "" && read!=null) {
					keywords.add(read);
				}
			} catch (Exception e) {
				read = "";
			}
		} while (read != "" && read != null);
		
	
		reader.close();
		this.keywords = keywords;
	}
	
	public ArrayList<String> buildSymbolTable(ArrayList<String> tokens){
		ArrayList<String> newTokens = new ArrayList<String>();
		ArrayList<String> symbols = new ArrayList<String>();
		for(int i=0; i < tokens.size();i++) {
			if(keywords.contains(tokens.get(i))) {
				newTokens.add(tokens.get(i));
			}else {
				symbols.add(tokens.get(i));
			}
		}
		tokens.clear();
		tokens.addAll(newTokens);
		return symbols;
	}
	
}
