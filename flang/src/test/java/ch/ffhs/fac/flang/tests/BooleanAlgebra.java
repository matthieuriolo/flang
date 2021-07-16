package ch.ffhs.fac.flang.tests;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;

/**
 * Unit tests for boolean algebra
 * @author matthieuriolo
 *
 */
public class BooleanAlgebra extends TestParserBase {
	@Test
	public void testAnd() throws Throwable {
		evaluateAssertTrue("return true and true");
		evaluateAssertTrue("return true and true and true");
		
		evaluateAssertFalse("return true and false");
		evaluateAssertFalse("return false and true");
		evaluateAssertFalse("return false and false");
	}
	
	@Test
	public void testOr() throws Throwable {
		evaluateAssertTrue("return true or true");
		evaluateAssertTrue("return true or true or true");
		
		evaluateAssertTrue("return true or false");
		evaluateAssertTrue("return false or true");
		evaluateAssertFalse("return false and false");
	}
	
	@Test
	public void testComplex() throws Throwable {
		evaluateAssertTrue("return true or true and false");
		evaluateAssertTrue("return true or false and true");
		evaluateAssertFalse("return false or true and false");
		evaluateAssertFalse("return false or false and true");
		
		evaluateAssertTrue("return true and true or false");
		evaluateAssertTrue("return true and false or true");
		evaluateAssertFalse("return false and true or false");
		evaluateAssertTrue("return false and false or true");
	}
	
	@Test
	public void testAndAlternateType() throws Throwable {
		evaluateAssertTrue("return true and \"abc\"");
		evaluateAssertFalse("return true and \"\"");
		
		evaluateAssertTrue("return true and 1");
		evaluateAssertFalse("return true and 0");
		
		evaluateAssertTrue("return true and :(){}");
		evaluateAssertFalse("return true and undefined");
	}
}
