package au.com.aconnex.codingchallenge;

import static org.junit.Assert.*;

import org.junit.Test;

import au.com.aconnex.codingchallenge.DigitTranslatorService;

public class DigitTranslatorServiceTest{
	@Test
	public void translateWordToDigit() {
		DigitTranslatorService trans = DigitTranslatorService.getInstance();
		
		assertEquals(trans.translateWordToDigit(""),"");
		
		//All valid letters
		assertEquals(trans.translateWordToDigit("A"),"2");
		assertEquals(trans.translateWordToDigit("B"),"2");
		assertEquals(trans.translateWordToDigit("C"),"2");
		
		assertEquals(trans.translateWordToDigit("D"),"3");
		assertEquals(trans.translateWordToDigit("E"),"3");
		assertEquals(trans.translateWordToDigit("F"),"3");
		
		assertEquals(trans.translateWordToDigit("G"),"4");
		assertEquals(trans.translateWordToDigit("H"),"4");
		assertEquals(trans.translateWordToDigit("I"),"4");

		assertEquals(trans.translateWordToDigit("J"),"5");
		assertEquals(trans.translateWordToDigit("K"),"5");
		assertEquals(trans.translateWordToDigit("L"),"5");
		
		assertEquals(trans.translateWordToDigit("M"),"6");
		assertEquals(trans.translateWordToDigit("N"),"6");
		assertEquals(trans.translateWordToDigit("O"),"6");
		
		assertEquals(trans.translateWordToDigit("P"),"7");
		assertEquals(trans.translateWordToDigit("Q"),"7");
		assertEquals(trans.translateWordToDigit("R"),"7");
		assertEquals(trans.translateWordToDigit("S"),"7");

		assertEquals(trans.translateWordToDigit("T"),"8");
		assertEquals(trans.translateWordToDigit("U"),"8");
		assertEquals(trans.translateWordToDigit("V"),"8");

		assertEquals(trans.translateWordToDigit("W"),"9");
		assertEquals(trans.translateWordToDigit("X"),"9");
		assertEquals(trans.translateWordToDigit("Y"),"9");
		assertEquals(trans.translateWordToDigit("Z"),"9");
		
		//Invalid values
		try {trans.translateWordToDigit("a"); fail();} catch (Exception e) {}
		try {trans.translateWordToDigit("0"); fail();} catch (Exception e) {}
		try {trans.translateWordToDigit(" "); fail();} catch (Exception e) {}
		try {trans.translateWordToDigit("-"); fail();} catch (Exception e) {}

		//Some full words
		assertEquals(trans.translateWordToDigit("HAPPY"),"42779");
		assertEquals(trans.translateWordToDigit("FUN"),"386");
	}
}
