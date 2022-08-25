package filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LexemsFilter {
	
	private Map<String,String> lexems;
	
	public LexemsFilter() throws IOException {
		
		FileReader file = new FileReader("resources/lexemas.txt");
		BufferedReader reader = new BufferedReader(file);
		
		Map<String,String>  lexems = new HashMap<String,String>();
		String[] keyValue;
		String read="";
		do {
			try {
				read = reader.readLine();
				if (read != "") {
					keyValue = read.split(":");
					lexems.put(keyValue[0], keyValue[1]);
				}
			} catch (Exception e) {
				
				read = "";
			}
		} while (read != "" && read != null);
		reader.close();
		this.lexems = lexems;
	}
	
	
	public void filterLexems(ArrayList<String> tokens) {
		
		for (int i=0; i<tokens.size();i++) {
			if(lexems.get(tokens.get(i))!=null) {
				
				tokens.set(i, lexems.get(tokens.get(i)));
				
			}
		}
	
	}
	

}
