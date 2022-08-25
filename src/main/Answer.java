package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Answer {
	
	Map<Integer, String> cache;
	
	
	public Answer() {
		cache = new HashMap<Integer, String>();
	}
	
	
	public String getAnswer(int fileNumber) throws IOException {
		
		String answerInCache = cache.get(fileNumber);
		
		if(answerInCache==null) {
			
			FileReader file = new FileReader("resources/answers/"+fileNumber+".txt");
			
			BufferedReader reader = new BufferedReader(file);
			String read = reader.readLine();
			StringBuilder sb = new StringBuilder();
			
			while(read!="" && read!=null) {
				sb.append(read+"\n");
				read = reader.readLine();
			}
			reader.close();
			answerInCache = sb.toString();
			cache.put(fileNumber, answerInCache);
		}
		
		return answerInCache;
		
	}

	
	
}
