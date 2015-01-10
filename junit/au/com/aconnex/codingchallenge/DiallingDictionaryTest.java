package au.com.aconnex.codingchallenge;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import au.com.aconnex.codingchallenge.dictionary.DiallingDictionary;



public class DiallingDictionaryTest {
	@Test
	public void getWordsForDigits() {
		DiallingDictionary dict = new DiallingDictionary();
		List<String> words;
		
		assertNull(dict.getWordsForDigits("4283"));
		assertNull(dict.getWordsForDigits("386"));

		//A word in the dictionary
		dict.addWord("HAVE");
		words = dict.getWordsForDigits("4283");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "HAVE");
		words = dict.getWordsForDigits("386");
		assertNull(words);

		//>1 word in the dictionary
		dict.addWord("FUN");
		words = dict.getWordsForDigits("4283");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "HAVE");
		words = dict.getWordsForDigits("386");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "FUN");

		//>1 word in the dictionary for the same digits
		dict.addWord("DUM");
		words = dict.getWordsForDigits("4283");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "HAVE");
		words = dict.getWordsForDigits("386");
		assertEquals(words.size(), 2);
		assertEquals(words.get(0), "FUN");
		assertEquals(words.get(1), "DUM");
	}
}
