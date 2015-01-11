package au.com.aconnex.codingchallenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.aconnex.codingchallenge.dictionary.DiallingDictionary;



/** The main entry class into the project*/
public class Main {
	private DiallingDictionary dictionary = new DiallingDictionary();
	private List<String> phoneNumberFiles = new ArrayList<String>();
	private DigitToWordProcessor dwp = new DigitToWordProcessor(dictionary);
	private static final String DEFAULT_DICTIONARY = "testfiles/dictionary.txt";
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.processArguments(args);
		

		if (main.phoneNumberFiles.isEmpty()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String commandStr;
			out("Please enter phone numbers to process into words(a empty line will exit):");
			while (!(commandStr = reader.readLine()).isEmpty()) {
				commandStr = FileImportUtils.clensePhoneNumberLine(commandStr);
				main.processPhoneNumber(commandStr);
			}
		} else {
			main.processPhoneNumberFiles();
		}
	}
	
	/** Processes the arguments and performs the actions requested*/
	private void processArguments(String[] args) throws Exception {
		boolean foundDictionary = false;
		for (int index = 0; index < args.length; index++) {
			if (args[index].equals("-d")) {
				index++;
				if (index == args.length) {
					throw new Exception("You must provide a dictionary with the -d option.");
				}
				FileImportUtils.importDictionaryFromFile(dictionary, args[index]);
				foundDictionary = true;
			} else {
				phoneNumberFiles.add(args[index]);
			}
		}
		
		if (!foundDictionary) {
			out("Using default dictionary: "+DEFAULT_DICTIONARY);
			FileImportUtils.importDictionaryFromFile(dictionary, DEFAULT_DICTIONARY );
		}
	}

	/** Process the phone numbers and display the digits returned for all phone numbers requested. */
	private void processPhoneNumberFiles() throws FileNotFoundException, IOException {
		for (String phoneNumberFile : phoneNumberFiles) {
			for (String phoneNumber : FileImportUtils.importDigitsFromFile(phoneNumberFile)) {
				processPhoneNumber(phoneNumber);
			}
		}
	}
	
	/** Outputs the letters returned for a single set of digits.*/
	private void processPhoneNumber(String phoneNumber) {
		out("Processing digits:"+phoneNumber);
		for (String word : dwp.getWordsForDigits(phoneNumber)) {
			out(" "+word);
		}
	}

	/** Output the given message to the user */
	private static void out(String string) {
		System.out.println(string);
	}
}
