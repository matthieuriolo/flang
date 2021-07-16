package ch.ffhs.fac.flang.tests;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.ffhs.fac.flang.base.TestParserBase;


/**
 * Unit tests for literal boolean
 * @author matthieuriolo
 *
 */
public class LiteralBoolean extends TestParserBase {
	@Test
	public void testAddition() throws Throwable {
		evaluateAssertUndefined("return true+true");
		evaluateAssertUndefined("return true+false");
		evaluateAssertUndefined("return false+false");
		evaluateAssertUndefined("return false+true");
	}
	
	@Test
	public void testSubstraction() throws Throwable {
		evaluateAssertUndefined("return true-true");
		evaluateAssertUndefined("return true-false");
		evaluateAssertUndefined("return false-false");
		evaluateAssertUndefined("return false-true");
	}
	
	@Test
	public void testMultiplication() throws Throwable {
		evaluateAssertUndefined("return true*true");
		evaluateAssertUndefined("return true*false");
		evaluateAssertUndefined("return false*false");
		evaluateAssertUndefined("return false*true");
	}
	
	@Test
	public void testDivison() throws Throwable {
		evaluateAssertUndefined("return true/true");
		evaluateAssertUndefined("return true/false");
		evaluateAssertUndefined("return false/false");
		evaluateAssertUndefined("return false/true");
	}
	
	@Test
	public void testUndefined() throws Throwable {
		evaluateAssertUndefined("return true+1");
		evaluateAssertUndefined("return true+undefined");
		evaluateAssertUndefined("return true+\"\"");
		evaluateAssertUndefined("return true+:(){}");
		evaluateAssertUndefined("return false+1");
		evaluateAssertUndefined("return false+undefined");
		evaluateAssertUndefined("return false+\"\"");
		evaluateAssertUndefined("return false+:(){}");
	}
	
	@Test
	public void testCompareEqual() throws Throwable {
		evaluateAssertTrue("return true==true");
		evaluateAssertFalse("return true==false");
		evaluateAssertTrue("return false==false");
		evaluateAssertFalse("return true==1");
		evaluateAssertFalse("return true==undefined");
		evaluateAssertFalse("return true==\"\"");
		evaluateAssertFalse("return true==:(){}");
	}
	
	@Test
	public void testCompareNotEqual() throws Throwable {
		evaluateAssertFalse("return true!=true");
		evaluateAssertTrue("return true!=false");
		evaluateAssertFalse("return false!=false");
		evaluateAssertTrue("return true!=1");
		evaluateAssertTrue("return true!=undefined");
		evaluateAssertTrue("return true!=\"\"");
		evaluateAssertTrue("return true!=:(){}");
	}
	
	@Test
	public void testCompareLess() throws Throwable {
		evaluateAssertUndefined("return true<true");
		evaluateAssertUndefined("return true<false");
		evaluateAssertUndefined("return false<false");
		evaluateAssertUndefined("return true<1");
		evaluateAssertUndefined("return true<undefined");
		evaluateAssertUndefined("return true<\"\"");
		evaluateAssertUndefined("return true<:(){}");
	}
	
	@Test
	public void testCompareLessOrEqual() throws Throwable {
		evaluateAssertUndefined("return true<=true");
		evaluateAssertUndefined("return true<=false");
		evaluateAssertUndefined("return false<=false");
		evaluateAssertUndefined("return true<=1");
		evaluateAssertUndefined("return true<=undefined");
		evaluateAssertUndefined("return true<=\"\"");
		evaluateAssertUndefined("return true<=:(){}");
	}
	
	@Test
	public void testCompareGreater() throws Throwable {
		evaluateAssertUndefined("return true>true");
		evaluateAssertUndefined("return true>false");
		evaluateAssertUndefined("return false>false");
		evaluateAssertUndefined("return true>1");
		evaluateAssertUndefined("return true>undefined");
		evaluateAssertUndefined("return true>\"\"");
		evaluateAssertUndefined("return true>:(){}");
	}
	
	@Test
	public void testCompareGreaterOrEqual() throws Throwable {
		evaluateAssertUndefined("return true>=true");
		evaluateAssertUndefined("return true>=false");
		evaluateAssertUndefined("return false>=false");
		evaluateAssertUndefined("return true>=1");
		evaluateAssertUndefined("return true>=undefined");
		evaluateAssertUndefined("return true>=\"\"");
		evaluateAssertUndefined("return true>=:(){}");
	}
}
