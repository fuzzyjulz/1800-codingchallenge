package au.com.aconnex.codingchallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DigitTranslatorService {
	Map<Character, Character> digitToLetterTranslation;
	Map<Character, char[]> letterToDigitTranslation;

	private static DigitTranslatorService instance = new DigitTranslatorService();
	
	public String translateWordToDigit(String word) throws DigitTranslationException {
		StringBuffer translation = new StringBuffer();
		for (Character letter : word.toCharArray()) {
			Character digit = digitToLetterTranslation.get(letter);
			if (digit == null)
				throw new DigitTranslatorService.DigitTranslationException("The letter '"+letter+"' could not be translated");
			translation.append(digit);
		}
		return translation.toString();
	}

	private DigitTranslatorService() {
		addMappings();
	}
	
	public static DigitTranslatorService getInstance() {
		return instance;
	}
	
	private void addMappings() {
		letterToDigitTranslation = new HashMap<Character, char[]>();
		letterToDigitTranslation.put('2', new char[]{'A','B','C'});
		letterToDigitTranslation.put('3', new char[]{'D','E','F'});
		letterToDigitTranslation.put('4', new char[]{'G','H','I'});
		letterToDigitTranslation.put('5', new char[]{'J','K','L'});
		letterToDigitTranslation.put('6', new char[]{'M','N','O'});
		letterToDigitTranslation.put('7', new char[]{'P','Q','R','S'});
		letterToDigitTranslation.put('8', new char[]{'T','U','V'});
		letterToDigitTranslation.put('9', new char[]{'W','X','Y','Z'});
		
		generateDigitToLetterTranslation();
	}

	private void generateDigitToLetterTranslation() {
		digitToLetterTranslation = new HashMap<Character, Character>();
		for (Entry<Character, char[]> translation : letterToDigitTranslation.entrySet()) {
			for (char value : translation.getValue()) {
				digitToLetterTranslation.put(value, translation.getKey());
			}
		}
	}

	public static class DigitTranslationException extends RuntimeException {
		private static final long serialVersionUID = 6062282009704918272L;

		public DigitTranslationException(String message){
			super(message);
		}
	}
}
