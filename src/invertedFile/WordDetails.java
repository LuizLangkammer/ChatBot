package invertedFile;

import java.io.Serializable;
import java.util.ArrayList;

public class WordDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Appearance> appearances;
	private int totalAppearances;
	private Appearance appearanceCache;
	public WordDetails() {
		appearances = new ArrayList<Appearance>();
		appearanceCache = null;
	}

	public ArrayList<Appearance> getAppearances() {
		return appearances;
	}

	public int getTotalApearances() {
		return totalAppearances;
	}
	
	public boolean exists(int file) {
		Appearance appearance=null;
		for(int j=0;j<appearances.size();j++) {
			if(appearances.get(j).getFileNumber()==file) {
				appearance = appearances.get(j);
			}
		}
		appearanceCache=appearance;
		return appearance!=null;
	}
	
	public void addAppearance(int file) {
		appearances.add(new Appearance(file));
		totalAppearances++;
	}
	
	public void incrementAppearance(int file) {
		if(appearanceCache.getFileNumber()==file) {
			appearanceCache.incrementAppearance();
			totalAppearances++;
		}else {
			for(int i=0;i<appearances.size();i++) {
				if(appearances.get(i).getFileNumber()==file) {
					appearances.get(i).incrementAppearance();
					totalAppearances++;
					break;
				}
			}
		}
		
	}
	
	
	
	
	
}
