package ch.ffhs.fac.flang.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.StringReader;
import java.math.BigDecimal;

import ch.ffhs.fac.flang.parser.Parser;
import ch.ffhs.fac.flang.parser.Scanner;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.literals.Decimal;
import ch.ffhs.fac.flang.runtime.literals.Undefined;
import ch.ffhs.fac.flang.runtime.literals.Boolean;
/**
 * Helper class for unit tests. Provides an easy way of parsing a string and assert the return value
 * @author matthieuriolo
 *
 */
public abstract class TestParserBase {
	/**
	 * Parse the string an returns a document
	 * @param sourceCode
	 * @return
	 * @throws Exception
	 */
	public Document createDocument(final String sourceCode) throws Exception {
		try(final var reader = new StringReader(sourceCode)) {
			final var lexer = new Scanner(reader);
			@SuppressWarnings("deprecation")
			final var parser = new Parser(lexer);
			return parser.parseDocument();
		}
	}
	
	/**
	 * Executes the code. Useful for testing if an exception gets thrown
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public Literal evaluate(final String sourceCode) throws Throwable {
		return createDocument(sourceCode).execute();
	}
	
	/**
	 * Executes the code and compares the returned value against an int
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssert(final String sourceCode, final int expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and compares the returned value against a double
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssert(final String sourceCode, final double expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and compares the returned value against a BigDecimal
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssert(final String sourceCode, final BigDecimal expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and compares the returned value against a String
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssert(final String sourceCode, final String expected) throws Throwable {
		assertEquals(new ch.ffhs.fac.flang.runtime.literals.String(expected), evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and tests if the returned value is TRUE
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssertTrue(final String sourceCode) throws Throwable {
		assertEquals(Boolean.TRUE, evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and tests if the returned value is FALSE
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssertFalse(final String sourceCode) throws Throwable {
		assertEquals(Boolean.FALSE, evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and tests if the returned value is UNDEFINED
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssertUndefined(final String sourceCode) throws Throwable {
		assertEquals(Undefined.UNDEFINED, evaluate(sourceCode));
	}
	
	/**
	 * Executes the code and tests if the returned value is not UNDEFINED
	 * @param sourceCode
	 * @return
	 * @throws Throwable
	 */
	public void evaluateAssertNotUndefined(final String sourceCode) throws Throwable {
		assertNotEquals(Undefined.UNDEFINED, evaluate(sourceCode));
	}
}
