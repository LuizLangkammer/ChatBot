package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import filters.KeyWordsFilter;
import filters.LexemsFilter;
import filters.StopWordsFilter;
import filters.TokenSeparator;
import invertedFile.Appearance;
import invertedFile.InvertedFileCreator;
import invertedFile.WordDetails;
import serializer.Serialyze;
import tfidf.TFIDF;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Initializing chatBot
		
		Answer answerBuilder= new Answer(); 

		// Loading stopWords
		StopWordsFilter stopwordsFilter = null;
		try {
			stopwordsFilter = new StopWordsFilter();
		} catch (Exception e) {
			System.out.println("Falha na leitura do arquivo de stopWords");
			System.out.println("Encerrando...");
			System.exit(0);
		}
		System.out.println("StopWords pronto");

		// Loading keyWords
		KeyWordsFilter keywordsFilter = null;
		try {
			keywordsFilter = new KeyWordsFilter();
		} catch (Exception e) {
			System.out.println("Falha na leitura do arquivo de keywords");
			System.out.println("Encerrando...");
			System.exit(0);
		}
		System.out.println("KeyWords pronto");

		// Loading Lexems
		LexemsFilter lexemsFilter = null;
		try {
			lexemsFilter = new LexemsFilter();
		} catch (Exception e) {
			System.out.println("Falha na leitura do arquivo de lexemas");
			System.out.println("Encerrando...");
			System.exit(0);
		}
		System.out.println("Lexemas pronto");

		// Loading invertedFile
		boolean answerChanged = true;
		Map<String, WordDetails> invertedFile = null;

		try {
			invertedFile = (Map<String, WordDetails>) Serialyze.readObject("inverted-file.txt");
		} catch (Exception e) {
			System.out.println("Falha ao ler arquivo invertido");
			System.exit(0);
		}

		if (invertedFile == null || answerChanged) {
			invertedFile = InvertedFileCreator.createFile(stopwordsFilter, lexemsFilter);
		}
		System.out.println("Arquivo invertido pronto");
		//printInvertedFile(invertedFile);
		
		TFIDF tfidf = new TFIDF();

		// ----------CHATBOT--------

		Scanner in = new Scanner(System.in);

		System.out.println("============================< CHAT BOT >============================");
		System.out.println();
		System.out.println("Bem Vindo ao chat bot desenvolvido por Luiz Eduardo Langkammer Murta");
		System.out.println("Sinta-se à vontade para teclar:");
		
		System.out.print("--->");
		String question = in.nextLine();
		
		ArrayList<String> allTokens = new ArrayList<String>();
		
		while(!question.equals("sair")) {
			
			question = question.toLowerCase();
			// Verifying the alphabet of the inputed expression
			// =====================================================================
			if (!verifyAlphabet(question)) {
				System.out.println("Desculpe, não fui capaz de entender");
				return;
			}

			// Create list of tokens
			// ===================================================================
			ArrayList<String> tokens = TokenSeparator.getTokensList(question);

			// Remove stopWords
			// ====================================================================
			tokens = stopwordsFilter.removeStopWords(tokens);

			// Lexems
			// ===============================================================================
			lexemsFilter.filterLexems(tokens);
			
			allTokens.addAll(tokens);
			
			tfidf.avaliate(allTokens, invertedFile, false);
		
			int answerFile = tfidf.getBestDocument();
			
			
			System.out.println(answerFile);
			
			try {
				System.out.println("Chat Bot: "+answerBuilder.getAnswer(answerFile));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Falha ao obter resposta");
				System.out.println("Encerrando...");
				System.exit(0);
			}
			System.out.print("--->");
			question = in.nextLine();
			
		}
		
		in.close();
		System.out.println("Encerrando chatbot");
	}

	public static boolean verifyAlphabet(String input) {
		boolean alphabet = Pattern.matches(
				"[a-z0-9 çáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ!#%&,-/:;<>=@_´`~ªº\\?\\*\\.\\+\\^\\$\\(\\)\\[\\]\\{\\}\\|]*",
				input);
		return alphabet;
	}
	
	
	
	public static void printInvertedFile(Map<String, WordDetails> invertedFile) {
		for (Map.Entry<String,WordDetails> entry : invertedFile.entrySet()) {
			System.out.printf("%-20s - ",entry.getKey());
			for(Appearance appearance: entry.getValue().getAppearances()) {
				System.out.printf("%2d(%1d) ",appearance.getFileNumber(),appearance.getHowMany());	
			}
			System.out.println();
		}
	}

	
}
