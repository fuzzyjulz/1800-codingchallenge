package au.com.aconnex.codingchallenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.aconnex.codingchallenge.dictionary.Dictionary;



class FileImportUtils {
	static void importDictionaryFromFile(Dictionary dict, String filename) throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = clenseDictionaryLine(line);
				if (!line.isEmpty()) {
					dict.addWord(line);
				}
			}
		}
	}
	
	static String clenseDictionaryLine(String line) {
		return line.trim().toUpperCase().replaceAll("[^A-Z]", "");
	}
	
	static List<String> importDigitsFromFile(String filename) throws FileNotFoundException, IOException {
		List<String> digits = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = clensePhoneNumberLine(line);
				if (!line.isEmpty()) {
					digits.add(line);
				}
			}
		}
		return digits;
	}
	static String clensePhoneNumberLine(String line) {
		return line.trim().replaceAll("[^0-9]", "");
	}
}