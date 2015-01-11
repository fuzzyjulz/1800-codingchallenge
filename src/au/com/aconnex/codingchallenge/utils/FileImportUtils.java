package au.com.aconnex.codingchallenge.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.aconnex.codingchallenge.entities.Dictionary;


/** Stand alone helper methods relating to reading from a file*/
public class FileImportUtils {
	/** Reads a dictionay from a file and adds it into the given dictionary. Uses the clenseDictionaryLine method */
	public static void importDictionaryFromFile(Dictionary dict, String filename) throws FileNotFoundException, IOException {
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
	
	/** Clenses the dictionary line, ignoring anything but letters*/
	public static String clenseDictionaryLine(String line) {
		return line.trim().toUpperCase().replaceAll("[^A-Z]", "");
	}
	
	/** Reads a list of digits from a file. Uses the clensePhoneNumberLine method*/
	public static List<String> importDigitsFromFile(String filename) throws FileNotFoundException, IOException {
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
	
	/** Clenses a line of a phone number list. Returns only digits, ignoring any non digit characters.*/
	public static String clensePhoneNumberLine(String line) {
		return line.trim().replaceAll("\\D", "");
	}
}