package ch.ffhs.fac.flang.parser.interfaces;

import java.util.List;

import ch.ffhs.fac.flang.runtime.Context;

/**
 * The Literal interface represents Types/object in the language flang.
 * 
 * The literals can define themself how to handle operations
 * 
 * @author matthieuriolo
 *
 */
public interface Literal extends Expression {
	/**
	 * Computes a new literal when the operation OR is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an OR operation
	 */
	public Literal computeOr(final Literal right);
	/**
	 * Computes a new literal when the operation AND is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an AND operation
	 */
	public Literal computeAnd(final Literal right);
	/**
	 * Computes a new literal when the operation PLUS is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an PLUS operation
	 */
	public Literal computePlus(final Literal right);
	/**
	 * Computes a new literal when the operation MINUS is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an MINUS operation
	 */
	public Literal computeMinus(final Literal right);
	/**
	 * Computes a new literal when the operation ASTERISK is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an ASTERISK operation
	 */
	public Literal computeAsterisk(final Literal right);
	/**
	 * Computes a new literal when the operation SLASH is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an SLASH operation
	 */
	public Literal computeSlash(final Literal right);
	/**
	 * Computes a new literal when the operation EQUAL is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an EQUAL operation
	 */
	public Literal computeEqual(final Literal right);
	/**
	 * Computes a new literal when the operation NOTEQUAL is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an NOTEQUAL operation
	 */
	public Literal computeNotEqual(final Literal right);
	/**
	 * Computes a new literal when the operation LESS is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an LESS operation
	 */
	public Literal computeLess(final Literal right);
	/**
	 * Computes a new literal when the operation LESSOREQUAL is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an LESSOREQUAL operation
	 */
	public Literal computeLessEqual(final Literal right);
	/**
	 * Computes a new literal when the operation GREATER is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an GREATER operation
	 */
	public Literal computeGreater(final Literal right);
	/**
	 * Computes a new literal when the operation GREATEROREQUAL is applied to this literal
	 * @param right is the right sided literal
	 * @return resulting {@link Literal} from an GREATEROREQUAL operation
	 */
	public Literal computeGreaterEqual(final Literal right);
	/**
	 * Computes a new literal when the unary operation MINUS is applied to this literal
	 * @return resulting {@link Literal} from an unary MINUS operation
	 */
	public Literal computeUnaryMinus();
	
	/**
	 * Invoked when the literal is called like an function
	 * @param context is the scope in which the literal gets called from
	 * @param arguments list of {@link Literal} which was passed as arguments to the function call
	 * @return the returned {@link Literal} from the function
	 * @throws Throwable
	 */
	public Literal functionalCall(final Context context, final List<Literal> arguments) throws Throwable;
	
	/**
	 * Outboxing to a boolean value
	 * @return boolean representation of this literal
	 */
	public boolean toBoolean();
	/**
	 * Outboxing to a string value which can be displayed to the user
	 * @return textual representation of this literal
	 */
	public String toHumanReadableString();
	/**
	 * Outboxing to a Decimal {@link Literal}
	 * @return Decimal representation of this literal
	 */
	public Literal toDecimal(final Context context);
	/**
	 * Name of the literal
	 * @return the name of the literal
	 */
	public String getTypeName();
	
	@Override
	public Literal compute(Context context) throws Throwable;
	/**
	 * Gets called when the literal is accessed like an array
	 * @param idx is the index as a {@link Literal}
	 * @return the {@link Literal} at the position idx
	 */
	public Literal getAccess(Literal idx);
	/**
	 * Gets called when the literal will assign a variable to it like an array
	 * @param idx is the index as a {@link Literal}
	 * @param value is a {@link Literal} which should be stored inside this literal
	 */
	public void setAccess(Literal idx, Literal value);
}
