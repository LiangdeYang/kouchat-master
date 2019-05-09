package net.usikkert.kouchat.util;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class My_Tools_shorten_Test {
	/*
	 * The shorten method takes an String: word and a Integer: length as input.
	 * Tests below go through all the independent paths 
	 *  
	 *  @author Liangde Yang 
	 */

	/*path 1,2,8
	 * 
	 * 1 -> 2 : word == null
	 * 2 -> 8 : return null (End)
	 * 
	 */
	@Test 
	public void NullWordShouldReturnNull() {			
		assertNull(Tools.shorten(null, 10)) ;				
	}
	
	/*path 1,3,4,8
	 * 
	 * 1 -> 3 : word != null
	 * 3 -> 4 : length < 0
	 * 3 -> 8 : return  "" (End)
	 * 
	 */
	@Test
	public void LengthSmallerThanZeroShouldReturnEmptyString() {		
		assertEquals("", Tools.shorten("anything", -1));						
	}
	
	/*path 1,3,5,6,8
	 * 
	 * 1 -> 3 : word != null
	 * 3 -> 5 : length >= 0
	 * 5 -> 6 : word.length() <= length
	 * 6 -> 8 : return word (End)
	 * 
	 */
	@Test
	public void WordLengthSmallerOrEqualLengthShouldReturnWord() {
		assertEquals("word", Tools.shorten("word", 10));						
	}
	
	/*path 1,3,5,7,8
	 * 
	 * 1 -> 3 : word != null
	 * 3 -> 5 : length >= 0
	 * 5 -> 7 : word.length() > length
	 * 7 -> 8 : return word (End)
	 * 
	 */
	@Test
	public void WordLengthNoLargerThanLengthShouldReturnSubstring() {
		assertEquals("wo", Tools.shorten("word", 2));			
	}

}
