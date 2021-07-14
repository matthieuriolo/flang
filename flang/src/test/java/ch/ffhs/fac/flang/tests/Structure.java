package ch.ffhs.fac.flang.tests;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;

public class Structure extends TestParserBase {
	@Test
	public void testWhile() throws Throwable {
		evaluateAssert("a = 0 while a < 2 {a = a+1} return a", 2);
		evaluateAssertTrue("a = 0 while a < 2 {a = a+1 return True} return a");
	}
	
	@Test
	public void testIf() throws Throwable {
		evaluateAssertTrue("if true {return true}");
		evaluateAssertTrue("if true {return true}else {return false}");
		evaluateAssertFalse("if false {return true}else {return false}");
	}
	
	@Test
	public void testForIncrement() throws Throwable {
		evaluateAssert("a=0 for i to 3 {a=a+i} return a", 3);
		evaluateAssert("a=0 for i from 1 to 3 {a=a+i} return a", 3);
		evaluateAssert("a=0 for i to 5 by 2 {a=a+i} return a", 6);
		evaluateAssert("a=0 for i from 1 to 5 by 2 {a=a+i} return a", 4);
	}
	
	@Test
	public void testForDecrement() throws Throwable {
		evaluateAssert("a=0 for i to -3 {a=a+i} return a", 0);
		evaluateAssert("a=0 for i from -1 to -3 {a=a+i} return a", 0);
		evaluateAssert("a=0 for i to -5 by -2 {a=a+i} return a", -6);
		evaluateAssert("a=0 for i from -1 to -5 by -2 {a=a+i} return a", -4);
	}
	
	@Test
	public void testComplex() throws Throwable {
		evaluateAssertTrue("if true {for i to 1 {while true {return true}}}");
	}
}
