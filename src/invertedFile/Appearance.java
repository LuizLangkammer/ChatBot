package invertedFile;

import java.io.Serializable;

public class Appearance implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fileNumber;
	private int howMany;
	
	
	public Appearance(int fileNumber) {
		this.fileNumber = fileNumber;
		howMany=1;
	}
	
	
	public void incrementAppearance() {
		howMany++;
	}


	public int getFileNumber() {
		return fileNumber;
	}


	public int getHowMany() {
		return howMany;
	}


	@Override
	public boolean equals(Object obj) {
		return ((Appearance)obj).fileNumber == fileNumber;
	}
	
	
	
}
