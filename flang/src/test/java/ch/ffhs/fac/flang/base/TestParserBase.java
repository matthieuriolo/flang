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

public class TestParserBase {
	public Document createDocument(final String sourceCode) throws Exception {
		final var reader = new StringReader(sourceCode);
		final var lexer = new Scanner(reader);
		@SuppressWarnings("deprecation")
		final var parser = new Parser(lexer);
		return parser.parseDocument();
	}
	
	public Literal evaluate(final String sourceCode) throws Throwable {
		return createDocument(sourceCode).execute();
	}
	
	public void evaluateAssert(final String sourceCode, final int expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	public void evaluateAssert(final String sourceCode, final double expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	public void evaluateAssert(final String sourceCode, final BigDecimal expected) throws Throwable {
		assertEquals(new Decimal(expected), evaluate(sourceCode));
	}
	
	public void evaluateAssert(final String sourceCode, final String expected) throws Throwable {
		assertEquals(new ch.ffhs.fac.flang.runtime.literals.String(expected), evaluate(sourceCode));
	}
	
	public void evaluateAssertTrue(final String sourceCode) throws Throwable {
		assertEquals(Boolean.TRUE, evaluate(sourceCode));
	}
	
	public void evaluateAssertFalse(final String sourceCode) throws Throwable {
		assertEquals(Boolean.FALSE, evaluate(sourceCode));
	}
	
	public void evaluateAssertUndefined(final String sourceCode) throws Throwable {
		assertEquals(Undefined.UNDEFINED, evaluate(sourceCode));
	}
	
	public void evaluateAssertNotUndefined(final String sourceCode) throws Throwable {
		assertNotEquals(Undefined.UNDEFINED, evaluate(sourceCode));
	}
}
