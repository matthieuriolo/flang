package ch.ffhs.fac.flang.tests;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;

public class LiteralString extends TestParserBase {
	@Test
	public void testAddition() throws Throwable {
		evaluateAssertUndefined("return \"\"+1");
		evaluateAssertUndefined("return \"\"+true");
		evaluateAssertUndefined("return \"\"+false");
		evaluateAssertUndefined("return \"\"+undefined");
		evaluateAssert("return \"a\"+\"a\"", "aa");
	}
	
	@Test
	public void testSubstraction() throws Throwable {
		evaluateAssertUndefined("return \"\"-1");
		evaluateAssertUndefined("return \"\"-true");
		evaluateAssertUndefined("return \"\"-false");
		evaluateAssertUndefined("return \"\"-undefined");
		evaluateAssertUndefined("return \"a\"-\"a\"");
	}
	
	@Test
	public void testMultiplication() throws Throwable {
		evaluateAssert("return \"a\"*3", "aaa");
		evaluateAssertUndefined("return \"a\"*-3");
		evaluateAssert("return \"a\"*3.4", "aaa");
		evaluateAssert("return \"a\"*3.9", "aaa");
		evaluateAssertUndefined("return \"\"*true");
		evaluateAssertUndefined("return \"\"*false");
		evaluateAssertUndefined("return \"\"*undefined");
		evaluateAssertUndefined("return \"a\"*\"a\"");
	}
	
	@Test
	public void testDivison() throws Throwable {
		evaluateAssertUndefined("return \"\"/1");
		evaluateAssertUndefined("return \"\"/true");
		evaluateAssertUndefined("return \"\"/false");
		evaluateAssertUndefined("return \"\"/undefined");
		evaluateAssertUndefined("return \"a\"/\"a\"");
	}
	
	@Test
	public void testCompareEqual() throws Throwable {
		evaluateAssertTrue("return \"a\"==\"a\"");
		evaluateAssertFalse("return \"A\"==\"a\"");
		evaluateAssertUndefined("return \"a\"==true");
		evaluateAssertUndefined("return \"a\"==false");
		evaluateAssertUndefined("return \"a\"==undefined");
	}
	
	@Test
	public void testCompareNotEqual() throws Throwable {
		evaluateAssertFalse("return \"a\"!=\"a\"");
		evaluateAssertTrue("return \"A\"!=\"a\"");
		evaluateAssertUndefined("return \"a\"!=true");
		evaluateAssertUndefined("return \"a\"!=false");
		evaluateAssertUndefined("return \"a\"!=undefined");
	}
	
	@Test
	public void testCompareLess() throws Throwable {
		evaluateAssertUndefined("return \"a\"<\"a\"");
		evaluateAssertUndefined("return \"A\"<\"a\"");
		evaluateAssertUndefined("return \"a\"<true");
		evaluateAssertUndefined("return \"a\"<false");
		evaluateAssertUndefined("return \"a\"<undefined");
	}
	
	@Test
	public void testCompareLessOrEqual() throws Throwable {
		evaluateAssertUndefined("return \"a\"<=\"a\"");
		evaluateAssertUndefined("return \"A\"<=\"a\"");
		evaluateAssertUndefined("return \"a\"<=true");
		evaluateAssertUndefined("return \"a\"<=false");
		evaluateAssertUndefined("return \"a\"<=undefined");
	}
	
	@Test
	public void testCompareGreater() throws Throwable {
		evaluateAssertUndefined("return \"a\">\"a\"");
		evaluateAssertUndefined("return \"A\">\"a\"");
		evaluateAssertUndefined("return \"a\">true");
		evaluateAssertUndefined("return \"a\">false");
		evaluateAssertUndefined("return \"a\">undefined");
	}
	
	@Test
	public void testCompareGreaterOrEqual() throws Throwable {
		evaluateAssertUndefined("return \"a\">=\"a\"");
		evaluateAssertUndefined("return \"A\">=\"a\"");
		evaluateAssertUndefined("return \"a\">=true");
		evaluateAssertUndefined("return \"a\">=false");
		evaluateAssertUndefined("return \"a\">=undefined");
	}
	
	@Test
	public void testComplex() throws Throwable {
		evaluateAssert("return \"a\" + \"b\" * 3 ", "abbb");
		evaluateAssert("return \"a\" * 3 + (\"c\"+ \"b\")", "aaacb");
	}
}
