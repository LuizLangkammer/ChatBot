package invertedFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class InvertedFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Map<String, ArrayList<Appearance>> invertedFile;
	
	public InvertedFile(Map<String, ArrayList<Appearance>> invertedFile) {
		this.invertedFile = invertedFile;
	}

	public Map<String, ArrayList<Appearance>> getInvertedFile() {
		return invertedFile;
	}
	
	

}
