package invertedFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import filters.*;
import serializer.Serialyze;

public class InvertedFileCreator {
	
	


	public static Map<String, WordDetails>  createFile(StopWordsFilter stopwordsFilter, LexemsFilter lexemsFilter) {
		
		Map<String, WordDetails> invertedFileBuilder = new HashMap<String, WordDetails>();
		
		
		boolean finishedFiles = false;
		int fileIterator = 1;
		
		while(!finishedFiles) {
			try {
				
				FileReader arq = new FileReader("resources/answers/"+fileIterator+".txt");
				BufferedReader reader = new BufferedReader(arq);
				
				String read = reader.readLine();
				
				while(read!="" && read!=null) {
					
					read = read.toLowerCase();
					
					// Create list of words
					// ===================================================================
					ArrayList<String> words = TokenSeparator.getTokensList(read);

					// remove stopWords
					// ====================================================================
					words = stopwordsFilter.removeStopWords(words);

					// Lexems
					// ===============================================================================
				    lexemsFilter.filterLexems(words);
					
				    
				    //Build inverted file
					for(int i=0;i<words.size();i++) {
						String word = words.get(i);
						
						WordDetails details = invertedFileBuilder.get(word);
						if(details==null) {
							details = new WordDetails();
							invertedFileBuilder.put(word, details);
						}
						
						boolean existsFileAppearance = details.exists(fileIterator); 
						
						if(!existsFileAppearance) {
							details.addAppearance(fileIterator);
						}else {
							details.incrementAppearance(fileIterator);
						}
						
					}
					
					read = reader.readLine();
				}
				reader.close();
				fileIterator++;
			}catch(FileNotFoundException e) {
				finishedFiles = true;
			} catch (IOException e1) {
				continue;
			}	
		}
		
		try {
			Serialyze.saveObject(invertedFileBuilder, "inverted-file.txt");
			System.out.println("Saved!");
			return invertedFileBuilder;
		}catch(Exception e) {
			System.out.println("Falha ao criar arquivo invertido");
			System.exit(0);
			return null;
		}
	
	}
	
	
	
	
	
	
}
