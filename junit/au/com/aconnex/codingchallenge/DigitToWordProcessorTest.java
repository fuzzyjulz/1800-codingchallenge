package au.com.aconnex.codingchallenge;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import au.com.aconnex.codingchallenge.entities.DiallingDictionary;

public class DigitToWordProcessorTest{
	@Test
	public void getWordsForDigits() {
		DiallingDictionary dict = new DiallingDictionary();
		DigitToWordProcessor proc = new DigitToWordProcessor(dict);
		List<String> words;
		
		words = proc.getWordsForDigits("");
		assertEquals("", words.get(0));
		assertEquals(1, words.size());
		words = proc.getWordsForDigits("4283");
		assertEquals(0, words.size());
		words = proc.getWordsForDigits("14283");
		assertEquals(0, words.size());
		
		dict.addWord("HAVE");
		words = proc.getWordsForDigits("4283");
		assertEquals(1, words.size());
		assertEquals("HAVE", words.get(0));

		//Should be able to have single digits in front or behind a word
		words = proc.getWordsForDigits("14283");
		assertEquals(1, words.size());
		assertEquals("1-HAVE", words.get(0));
		words = proc.getWordsForDigits("42831");
		assertEquals(1, words.size());
		assertEquals("HAVE-1", words.get(0));

		//Should be able to have single digits in front, in the middle or behind a words
		dict.addWord("ME");
		words = proc.getWordsForDigits("1428363");
		assertEquals(1, words.size());
		assertEquals("1-HAVE-ME", words.get(0));
		words = proc.getWordsForDigits("4283163");
		assertEquals(1, words.size());
		assertEquals("HAVE-1-ME", words.get(0));
		words = proc.getWordsForDigits("4283631");
		assertEquals(1, words.size());
		assertEquals("HAVE-ME-1", words.get(0));

		//Should not be able to have double digits in front, in the middle or behind a words
		words = proc.getWordsForDigits("11428363");
		assertEquals(0, words.size());
		words = proc.getWordsForDigits("42831163");
		assertEquals(0, words.size());
		words = proc.getWordsForDigits("42836311");
		assertEquals(0, words.size());

		//Should be able to have double digits in front, in the middle or behind a words
		// if double digits are allowed
		proc.setConsecutiveDigits(2);
		words = proc.getWordsForDigits("11428363");
		assertEquals(1, words.size());
		assertEquals("11-HAVE-ME", words.get(0));
		words = proc.getWordsForDigits("42831163");
		assertEquals(1, words.size());
		assertEquals("HAVE-11-ME", words.get(0));
		words = proc.getWordsForDigits("42836311");
		assertEquals(1, words.size());
		assertEquals("HAVE-ME-11", words.get(0));

		//Should be able to have multiple words that fulfil the criteria
		dict.addWord("OF");
		words = proc.getWordsForDigits("1428363");
		assertEquals(2, words.size());
		assertEquals("1-HAVE-ME", words.get(0));
		assertEquals("1-HAVE-OF", words.get(1));
		words = proc.getWordsForDigits("4283163");
		assertEquals(2, words.size());
		assertEquals("HAVE-1-ME", words.get(0));
		assertEquals("HAVE-1-OF", words.get(1));
		words = proc.getWordsForDigits("4283631");
		assertEquals(2, words.size());
		assertEquals("HAVE-ME-1", words.get(0));
		assertEquals("HAVE-OF-1", words.get(1));

	}
}
