package net.usikkert.kouchat.util;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

public class My_Tools_getDoubleDigit_Test {
	
	/*
	 * the getDoubleDigit method requires to take a number smaller than 10 as input.
	 * the equivalence partitions are deducted consist of input that:
	 *  1) smaller than 10 (valid case)
	 *  2) equal or larger than 10 (invalid case)
	 *  
	 */

	@Test
	public void LowerThanTenDigitShouldAddZeroAtTheStart() {
		Random rand = new Random();
		
		int number = rand.nextInt(10);
		String verifier = "0" + number;
		String result = Tools.getDoubleDigit(number);
		assertEquals(verifier, result);				
	}
	
	@Test
	public void EqualOrLargerThanTenDigitShouldNotAddZeroAtTheStart() {
		int number = ThreadLocalRandom.current().nextInt(10, 100 + 1);
	
		String verifier = "0" + number;
		String result = Tools.getDoubleDigit(number);
		assertNotSame(verifier, result);				
	}
	
	

}
