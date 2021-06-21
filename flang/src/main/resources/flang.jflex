package ch.ffhs.fac.flang.parser;

/*
 FLang Scanner
*/

import java_cup.runtime.*;
import java.math.BigDecimal;

/**
 * Autogenerated lexer for Flang - see flang.jflex
 */

%%

%class Scanner
%public
%final

%cup
%cupsym Symbols

%unicode
%line
%column
%ignorecase

/* additional code for lexer */
%{
	StringBuffer string = new StringBuffer();
	
	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%eofval{
	new Symbol(Symbols.EOF);
%eofval}
%eofclose

/* macros */
LINE                           = \r|\r\n|\n
SPACE                          = [\f\t\ ]+
WHITESPACE                     = {SPACE}|{LINE}
COMMENT                        = #[^\n]*\n?

IDENTIFIER                     = [:jletter:](\w|\d)*
DECIMAL                        = (([1-9][\d_]*\d)|\d)(\.(_*\d)+)?

/* different states */

%state STRING

%%


<YYINITIAL> {
	/* keywords */
	"true"             { return symbol(Symbols.TRUE); }
	"false"            { return symbol(Symbols.FALSE); }
	"undefined"        { return symbol(Symbols.UNDEFINED); }
	"for"              { return symbol(Symbols.FOR); }
	"from"             { return symbol(Symbols.FROM); }
	"to"               { return symbol(Symbols.TO); }
	"by"               { return symbol(Symbols.BY); }
	"while"            { return symbol(Symbols.WHILE); }
	"if"               { return symbol(Symbols.IF); }
	"else"             { return symbol(Symbols.ELSE); }
	"end"              { return symbol(Symbols.END); }
	"return"           { return symbol(Symbols.RETURN); }
	"and"              { return symbol(Symbols.AND); }
	"or"               { return symbol(Symbols.OR); }
	
	/* brackets */
	"("               { return symbol(Symbols.ROUND_OPEN); }
	")"               { return symbol(Symbols.ROUND_CLOSED); }
	"{"               { return symbol(Symbols.CURLY_OPEN); }
	"}"               { return symbol(Symbols.CURLY_CLOSED); }
	
	/* arithmetic */
	"="               { return symbol(Symbols.ASSIGN); }
	":"               { return symbol(Symbols.COLON); }
	","               { return symbol(Symbols.COMMA); }
	"+"               { return symbol(Symbols.PLUS); }
	"-"               { return symbol(Symbols.MINUS); }
	"*"               { return symbol(Symbols.ASTERISK); }
	"/"               { return symbol(Symbols.SLASH); }
	"=="              { return symbol(Symbols.EQUAL); }
	"!="              { return symbol(Symbols.NOT_EQUAL); }
	"<"               { return symbol(Symbols.LESS); }
	"<="              { return symbol(Symbols.LESS_EQUAL); }
	">"               { return symbol(Symbols.GREATER); }
	">="              { return symbol(Symbols.GREATER_EQUAL); }
	
	/* literals */
	{IDENTIFIER}      { return symbol(Symbols.IDENTIFIER, new String(yytext())); }
	{DECIMAL}         { return symbol(Symbols.DECIMAL, new BigDecimal(new String(yytext()).replace("_", ""))); }
	\"                { string.setLength(0); yybegin(STRING); }
	
	/* comments & whitespaces */
	{COMMENT}         { /* ignore */ }
	{WHITESPACE}      { /* ignore */ }
	<<EOF>>           { return symbol(Symbols.EOF); }
}


<STRING> {
 	\"                {
 	                      yybegin(YYINITIAL);
                          return symbol(Symbols.STRING, string.toString());
                      }
 	[^\n\r\"\\]+      { string.append(yytext()); }
 	\\t               { string.append('\t'); }
 	\\n               { string.append('\n'); }

 	\\r               { string.append('\r'); }
 	\\\"              { string.append('\"'); }
 	\\                { string.append('\\'); }
}

/* error fallback */
[^]                   { throw new IllegalSymbolException(yytext(), new Location(yyline + 1, yycolumn)); }