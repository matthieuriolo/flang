package ch.ffhs.fac.flang.tests;


import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;

public class LiteralDecimal extends TestParserBase {
	@Test
	public void testAddition() throws Throwable {
		evaluateAssert("return 3+1", 4);
		evaluateAssert("return -3+1", -2);
		evaluateAssert("return 987654321+987654321", 987654321 * 2);
		evaluateAssert("return -987654321+987654320", -987654321 + 987654320);
		evaluateAssert("return 1_0+1_0", 20);
		evaluateAssert("return 1.11+1.1", new BigDecimal("2.21"));
	}
	
	@Test
	public void testSubstraction() throws Throwable {
		evaluateAssert("return 3-1", 2);
	}
}
