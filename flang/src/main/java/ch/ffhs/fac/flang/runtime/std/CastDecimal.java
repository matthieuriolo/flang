package ch.ffhs.fac.flang.runtime.std;

import java.util.List;
import java.util.stream.Collectors;

import ch.ffhs.fac.flang.runtime.Closure;
import ch.ffhs.fac.flang.runtime.Closure.FunctionInterface;
import ch.ffhs.fac.flang.runtime.Literal;

public class CastDecimal implements FunctionInterface {
	public static final String NAME = "cast_decimal";

	@Override
	public Literal execute(final Closure closure, final List<Literal> parameters) throws Throwable {
		final var str = parameters.stream()
				.map(literal -> literal.toString(closure))
				.collect(Collectors.joining());
		return new ch.ffhs.fac.flang.runtime.literals.Decimal(str);
	}
}
