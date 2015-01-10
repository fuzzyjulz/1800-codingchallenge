package au.com.aconnex.codingchallenge;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import au.com.aconnex.codingchallenge.dictionary.DiallingDictionary;



public class FileImportUtilsTest {
	@Test
	public void clenseDirectoryLine() {
		assertEquals("ABCDABCD",FileImportUtils.clenseDictionaryLine("ABCD0123456789  -'- :.abcd"));
		assertEquals("",FileImportUtils.clenseDictionaryLine(""));
	}
	
	@Test
	public void clensePhoneNumberLine() {
		assertEquals("0123456789",FileImportUtils.clensePhoneNumberLine("ABCD0123456789  -'- :.abcd"));
		assertEquals("",FileImportUtils.clensePhoneNumberLine(""));
	}

	
	@Test
	public void importDictionaryFromFile() throws Exception {
		DiallingDictionary dict = new DiallingDictionary();
		List<String> words;
		
		words = dict.getWordsForDigits("8378");
		assertNull(words);

		words = dict.getWordsForDigits("3453");
		assertNull(words);

		words = dict.getWordsForDigits("8447");
		assertNull(words);

		words = dict.getWordsForDigits("746853");
		assertNull(words);

		words = dict.getWordsForDigits("9675");
		assertNull(words);

		
		FileImportUtils.importDictionaryFromFile(dict, "junit/testdata/dict1.txt");
		
		words = dict.getWordsForDigits("8378");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "TEST");

		words = dict.getWordsForDigits("3453");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "FILE");

		words = dict.getWordsForDigits("8447");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "THIS");

		words = dict.getWordsForDigits("746853");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "SHOULD");

		words = dict.getWordsForDigits("9675");
		assertEquals(words.size(), 1);
		assertEquals(words.get(0), "WORK");
	}

	@Test
	public void importDigitsFromFile() throws Exception {
		List<String> digits = FileImportUtils.importDigitsFromFile("junit/testdata/dict1.txt");
		assertEquals(digits.size(), 3);
		assertEquals(digits.get(0), "1");
		assertEquals(digits.get(1), "21");
		assertEquals(digits.get(2), "5678");
	}
}
