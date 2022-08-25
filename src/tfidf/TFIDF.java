package tfidf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import invertedFile.Appearance;
import invertedFile.WordDetails;

public class TFIDF {

	
	private Map<Integer, Double> files;
	
	
	public void avaliate(ArrayList<String> tokens, Map<String, WordDetails> invertedFile, boolean resetAvaliation) {
		
		if(resetAvaliation || files==null) {
			files = new HashMap<Integer, Double>();
		}
		
		for(int i=0; i<tokens.size();i++) {
			
			WordDetails details = invertedFile.get(tokens.get(i));
			if(details==null) continue;
			
			
			ArrayList<Appearance> appearances = details.getAppearances();
			
			Appearance appearance;
			for(int j=0; j<appearances.size(); j++) {
				appearance = appearances.get(j);
				Double avaliation = files.get(appearance.getFileNumber());
				if(avaliation == null) {
					avaliation = 0.0;
				}
				avaliation += (double)appearance.getHowMany()/details.getTotalApearances();
				files.put(appearance.getFileNumber(),avaliation);
			}
		}
	}
	
	public int getBestDocument() {
		int bestFile=0;
		for (Map.Entry<Integer, Double> entry : files.entrySet()) {
			if(bestFile==0) bestFile = entry.getKey();
		    if(entry.getValue()>files.get(bestFile)) {
		    	bestFile = entry.getKey();
		    }
		}
		return bestFile;
	}
	
	
}
