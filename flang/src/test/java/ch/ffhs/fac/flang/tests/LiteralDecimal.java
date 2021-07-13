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
		evaluateAssert("return -3-1", -4);
		evaluateAssert("return 987654321-987654321", 0);
		evaluateAssert("return -987654321-987654320", -987654321 - 987654320);
		evaluateAssert("return 1_0-1_0", 0);
		evaluateAssert("return 1.11-1.1", new BigDecimal("0.01"));
	}
	
	@Test
	public void testMultiplication() throws Throwable {
		evaluateAssert("return 3 * 2", 6);
		evaluateAssert("return -3 * 2", -6);
		evaluateAssert("return 987654321*987654321", new BigDecimal("987654321").multiply(new BigDecimal("987654321")));
		evaluateAssert("return -987654321*-987654321", new BigDecimal("-987654321").multiply(new BigDecimal("-987654321")));
		evaluateAssert("return 1_4 * 1_3", 14 * 13);
		evaluateAssert("return 1.41*5.01", new BigDecimal("7.0641"));
	}
	
	@Test
	public void testDivison() throws Throwable {
		evaluateAssert("return 6 / 2", 3);
		evaluateAssert("return -6 / 2", -3);
		evaluateAssert("return 987654321/329218107", 3);
		evaluateAssert("return -987654321/329218107", -3);
		evaluateAssert("return 9_9 / 3_3", 3);
		evaluateAssert("return 2.4165/1.79", new BigDecimal("1.35"));
	}
	
	@Test
	public void testUndefined() throws Throwable {
		evaluateAssertUndefined("return 1+true");
		evaluateAssertUndefined("return 1+false");
		evaluateAssertUndefined("return 1+undefined");
		evaluateAssertUndefined("return 1+\"\"");
		evaluateAssertUndefined("return 1+:(){}");
		evaluateAssertUndefined("return 1+thisIsAnNotDefinedVariable");
		evaluateAssertUndefined("return 1+thisIsAnNotDefinedFunction()");
	}
	
	@Test
	public void testCompareEqual() throws Throwable {
		evaluateAssertTrue("return 1==1");
		evaluateAssertFalse("return 1==2");
		evaluateAssertFalse("return 1==-1");
		evaluateAssertUndefined("return 1==true");
		evaluateAssertUndefined("return 1==false");
		evaluateAssertUndefined("return 1==undefined");
		evaluateAssertUndefined("return 1==\"\"");
		evaluateAssertUndefined("return 1==\"a\"");
	}
	
	@Test
	public void testCompareNotEqual() throws Throwable {
		evaluateAssertFalse("return 1!=1");
		evaluateAssertTrue("return 1!=2");
		evaluateAssertTrue("return 1!=-1");
		evaluateAssertUndefined("return 1!=true");
		evaluateAssertUndefined("return 1!=false");
		evaluateAssertUndefined("return 1!=undefined");
		evaluateAssertUndefined("return 1!=\"\"");
		evaluateAssertUndefined("return 1!=\"a\"");
	}
	
	@Test
	public void testCompareLess() throws Throwable {
		evaluateAssertFalse("return 1<1");
		evaluateAssertTrue("return 1<2");
		evaluateAssertFalse("return 1<-1");
		evaluateAssertUndefined("return 1<true");
		evaluateAssertUndefined("return 1<false");
		evaluateAssertUndefined("return 1<undefined");
		evaluateAssertUndefined("return 1<\"\"");
		evaluateAssertUndefined("return 1<\"a\"");
	}
	
	@Test
	public void testCompareLessOrEqual() throws Throwable {
		evaluateAssertTrue("return 1<=1");
		evaluateAssertTrue("return 1<=2");
		evaluateAssertFalse("return 1<=-1");
		evaluateAssertUndefined("return 1<=true");
		evaluateAssertUndefined("return 1<=false");
		evaluateAssertUndefined("return 1<=undefined");
		evaluateAssertUndefined("return 1<=\"\"");
		evaluateAssertUndefined("return 1<=\"a\"");
	}
	
	@Test
	public void testCompareGreater() throws Throwable {
		evaluateAssertFalse("return 1>1");
		evaluateAssertFalse("return 1>2");
		evaluateAssertTrue("return 1>-1");
		evaluateAssertUndefined("return 1>true");
		evaluateAssertUndefined("return 1>false");
		evaluateAssertUndefined("return 1>undefined");
		evaluateAssertUndefined("return 1>\"\"");
		evaluateAssertUndefined("return 1>\"a\"");
	}
	
	@Test
	public void testCompareGreaterOrEqual() throws Throwable {
		evaluateAssertTrue("return 1>=1");
		evaluateAssertFalse("return 1>=2");
		evaluateAssertTrue("return 1>=-1");
		evaluateAssertUndefined("return 1>=true");
		evaluateAssertUndefined("return 1>=false");
		evaluateAssertUndefined("return 1>=undefined");
		evaluateAssertUndefined("return 1>=\"\"");
		evaluateAssertUndefined("return 1>=\"a\"");
	}
	
	@Test
	public void testComplex() throws Throwable {
		evaluateAssert("return 2*4+1", 9);
		evaluateAssert("return (3+7)*2/5", 4);
	}
}
