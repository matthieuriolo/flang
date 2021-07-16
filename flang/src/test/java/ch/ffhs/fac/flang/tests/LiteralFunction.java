package ch.ffhs.fac.flang.tests;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;

/**
 * Unit tests for literal function
 * @author matthieuriolo
 *
 */
public class LiteralFunction extends TestParserBase {
	@Test
	public void testEnd() throws Throwable {
		evaluateAssertUndefined("a = :(){"
				+ "end"
				+ "}"
				+ "return a()");
	}
	
	@Test
	public void testReturn() throws Throwable {
		evaluateAssertTrue("a = :(){"
				+ "return true"
				+ "}"
				+ "return a()");
	}
	
	@Test
	public void testScopeInner() throws Throwable {
		evaluateAssertUndefined("a = :(){"
				+ "i=1"
				+ "}"
				+ "a()"
				+ "return i");
	}
	
	@Test
	public void testScopeOuter() throws Throwable {
		evaluateAssertTrue("b=true"
				+ " "
				+ "a=:(){"
				+ "return b"
				+ "}"
				+ "return a()");
		
		evaluateAssertTrue("b=false"
				+ " "
				+ "a = :(){"
				+ "b=true"
				+ "}"
				+ "a()"
				+ "return b");
		
		evaluateAssertUndefined("a=:(){return c}"
				+ "b=:(){c=5 return a()}"
				+ "return b()");
	}
	
	@Test
	public void testFunctionalProgramming() throws Throwable {
		evaluateAssertTrue("a = :(){"
				+ "return :(){return true}"
				+ "}"
				+ "return a()()");
		
		evaluateAssertTrue("a = :(b){return b()}"
				+ "return a(:(){return true})");
	}
	
	@Test
	public void testParameters() throws Throwable {
		evaluateAssert("a = :(b,c){ return b+c}"
				+ "return a(1,2)", 3);
		
		evaluateAssert("a = :(b,c){ return b+c}"
				+ "return a(1,2,100)", 3);
		
		evaluateAssertUndefined("a = :(b,c){ return b+c}"
				+ "return a(1)");
	}
	
	@Test
	public void testCompareEqual() throws Throwable {
		evaluateAssertTrue("a = :(){}"
				+ " "
				+ "b=a"
				+ " "
				+ "return a==b");
		
		evaluateAssertFalse("a = :(){}"
				+ " "
				+ "b=:(){}"
				+ "return a==b");
	}
}
