package au.com.aconnex.codingchallenge;

import java.util.ArrayList;
import java.util.List;

import au.com.aconnex.codingchallenge.entities.DiallingDictionary;


/** Processes digits into words using the given dialling dictionary*/
public class DigitToWordProcessor {
	private final DiallingDictionary dict;
	private int consecutiveDigits = 1;
	
	/** Creates a new processor */
	public DigitToWordProcessor(DiallingDictionary dict) {
		this.dict = dict;
	}

	/** Processes the given line into digits using the given dictionary.
	 *  As part of this service a number of consecutive digits can be left as
	 *  digits - if no word has been found. This is set by setConsecutiveDigits
	 */
	public List<String> getWordsForDigits(String line) {
		List<String> words = new ArrayList<>();
		int index = 0;
		
		do {
			int charactersLeft = line.length() - index;
			String wordPrefix = index > 0 ? line.substring(0, index)+"-" : "";
			
			//Look through all word lengths to find possible solutions
			for (int wordLength = 1; wordLength <= charactersLeft; wordLength++) {
				String wordDigits = line.substring(index, wordLength + index);
				List<String> possibleWords = dict.getWordsForDigits(wordDigits);
				if (possibleWords != null) {
					if (wordLength == charactersLeft) {
						//The line finishes at the end of the word
						for (String possibleWord : possibleWords) {
							words.add(wordPrefix + possibleWord);
						}
					} else {
						//The line has future unmatched digits. Find solutions for them
						List<String> matchedWords = getWordsForDigits(line.substring(index + wordLength));
						for (String matchedWord : matchedWords) {
							for (String possibleWord : possibleWords) {
								words.add(wordPrefix + possibleWord +"-"+ matchedWord);
							}
						}
					}
				}
			}
			index++;
		} while (index <= consecutiveDigits && index < line.length());

		//Allow for a word with only digits
		if (line.length() <= consecutiveDigits && words.size() == 0) {
			words.add(line);
		}
		
		return words;
	}

	/* Sets the number of consecutive digits to allow when processing a word*/
	public void setConsecutiveDigits(int consecutiveDigits) {
		this.consecutiveDigits = consecutiveDigits;
	}
}
