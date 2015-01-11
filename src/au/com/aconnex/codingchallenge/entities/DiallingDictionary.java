package au.com.aconnex.codingchallenge.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.aconnex.codingchallenge.services.DigitTranslatorService;

/** Generates a dictionary of words for the digits on a phone */
public class DiallingDictionary implements Dictionary {
	private Map<String, List<String>> digitsToWord = new HashMap<String, List<String>>();
	
	@Override
	public void addWord(String word) throws DigitTranslatorService.DigitTranslationException{
		String digits = DigitTranslatorService.getInstance().translateWordToDigit(word);
		List<String> wordsList = digitsToWord.get(digits);
		if (wordsList == null) {
			wordsList = new ArrayList<String>();
			digitsToWord.put(digits, wordsList);
		}
		wordsList.add(word);
	}

	/** Returns the list of words that match the given digits.*/
	public List<String> getWordsForDigits(String wordDigits) {
		return digitsToWord.get(wordDigits);
	}
}