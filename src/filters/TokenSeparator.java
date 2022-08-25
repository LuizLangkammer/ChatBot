package filters;

import java.util.ArrayList;

public class TokenSeparator {

	
	
	public static ArrayList<String> getTokensList(String input) {
		
		ArrayList<String> tokens = new ArrayList<String>();
		StringBuilder stringBuilder = new StringBuilder();
		char character;
		
		for (int i = 0; i < input.length(); i++) {
			character = input.charAt(i);
			if (verifySeparator(character)) {
				if (stringBuilder.length() != 0) {
					tokens.add(stringBuilder.toString());
				}
				stringBuilder.setLength(0);
				continue;
			}

			stringBuilder.append(input.charAt(i));
		}
		if (stringBuilder.length() != 0) {
			tokens.add(stringBuilder.toString());
		}
		return tokens;
	}
	
	private static boolean verifySeparator(char character) {
		if (character >= 32 && character <= 47) {
			return true;
		}
		if (character >= 58 && character <= 64) {
			return true;
		}
		if (character >= 91 && character <= 96) {
			return true;
		}
		if (character >= 123 && character <= 126) {
			return true;
		}
		return false;
	}
}
