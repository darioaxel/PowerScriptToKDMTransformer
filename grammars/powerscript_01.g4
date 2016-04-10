/**
*	Original Author: Darío Ureña
*	E-Mail: darioaxel@gmail.com
*/

grammar powerscript_01;

@header {
package src.grammar.powerscript;
}

compilationUnit
    :  memberDeclaration* EOF
    ;

memberDeclaration
    : forwardDeclaration            	
    | typeDeclaration					
    | variableDeclaration
    ;

// 1. Forward Declaration
forwardDeclaration
	: forwardDeclarationBegin forwardDeclarationBody* forwardDeclarationEnd	  
	;
	
forwardDeclarationBegin
	: 'forward' delimiter
	;
	
forwardDeclarationEnd
	: 'end' 'forward' delimiter
	;
	
forwardDeclarationBody
	: variableDeclaration
	| typeDeclaration
	;

// 2. Type Declaration
typeDeclaration
	: typeDeclarationBegin typeDeclarationBody*? typeDeclarationEnd
	;

typeDeclarationBegin
	: scopeModificator? typeDeclarationBeginIdentifier  typeDeclarationParent delimiter
	;

typeDeclarationBeginIdentifier
	: 'type' Identifier 'from'
	;

typeDeclarationParent
	: typeDeclarationParentExpecification? Identifier 
	;

typeDeclarationParentExpecification
	: typeDeclarationParentExpecificationId 'within' 
	;

typeDeclarationParentExpecificationId
	: Identifier '`' Identifier
	| Identifier
	;	

typeDeclarationBody							
	: typeDeclarationDescriptor
	| variableDeclaration
	;

typeDeclarationDescriptor
	: 'descriptor' quotedIdentifier '=' quotedIdentifier delimiter
	;

quotedIdentifier
	: QUOTE Identifier QUOTE
	;

typeDeclarationEnd
	: 'end' 'type' delimiter
	;

// 5. Variable Declaration
variableDeclaration
    :  scopeModificator? extendedAccessType? type variableDeclarators delimiter
    ;

variableDeclarators
    :   variableDeclarator (',' variableDeclarator)*
    ;

variableDeclarator
    :   Identifier arrayLengthDeclarator? ('=' variableInitializer)? 
    ;

variableInitializer 
    : expression
    ;

scopeModificator
    : 'global'
    | 'local'
    | 'shared'
    ;
	
statementBlock
    :   variableDeclaration
    |   statement
    ;

statement
    : expression
    ;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;

expression
    :   primary
    |   expression '.' Identifier
	|   expression '=' 'CREATE' 'USING'? Identifier	
    |   expression '(' expressionList? ')'
    |   '(' type ')' expression
    |   expression ('+=' | '-=')
    |   ('+'|'-'|'++'|'--') expression
    |   ('~'|'!') expression
    |   expression ('*'|'/'|'%') expression
    |   expression ('+'|'-') expression
    |   expression ('<' '<' | '>' '>' '>' | '>' '>') expression
    |   expression ('<=' | '>=' | '>' | '<') expression
    |   expression ('==' | '!=') expression
    |   expression '&' expression
    |   expression '^' expression
    |   expression '|' expression
    |   expression 'AND' expression
    |   expression 'OR' expression
    |   expression '?' expression ':' expression
    |   <assoc=right> expression
        (   '='
        |   '+='
        |   '-='
        |   '*='
        |   '/='
        |   '&='
        |   '|='
        |   '^='
        |   '>>='
        |   '>>>='
        |   '<<='
        |   '%='
        )
        expression
    ;
	
expressionList
    :   expression (',' expression)*
    ;

primary
    :  '(' expression ')'
    |  literal	
    |  Identifier
    ;

literal
    :   IntegerLiteral
    |   BooleanLiteral
    |	StringLiteral
    |   CharacterLiteral
    | 	DateTimeLiteral
    |   'null'
    ;

modifier
    :   'PUBLIC' ':'
    |   'PRIVATE' ':'
    |   'PROTECTED' ':'
    ;

accessType
	: primaryAccessType
	| extendedAccessType
	;

primaryAccessType
    :   'PUBLIC'
    |   'public'
    |   'PRIVATE'
    |   'private'
    |   'PROTECTED'
    |   'protected'
    ;

extendedAccessType
    :   'PROTECTEDREAD'
    |   'protectedread'
    |   'PRIVATEREAD'
    |   'privateread'
    |   'PROTECTEDWRITE'
    |   'protectedwrite'
    |   'PRIVATEWRITE'
    |   'privatewrite'
    ;

dataTypeName
    :   'ANY'
    |   'BLOB'
    |   'boolean'
    |   'byte'
    |   'character'
    |   'char'
    |   'date'
    |   'datetime'
    |   'decimal'
    |   'dec'
    |   'double'
    |   'integer'
    |   'int'
    |   'long'
    |   'longlong'
    |   'real'
    |   'string'
    |   'TIME'
    |   'UNSIGNEDINTEGER'
    |   'UINT'
    |   'UNSIGNEDLONG'
    |   'ULONG'
    |	'WINDOW'
    ;

type
    :   primitiveType 
    |   objectType
    ;

objectType
    :	Identifier ( '.' Identifier )*
    ;

arrayLengthDeclarator
    : '[' arrayLengthValue* ']'
    ;

arrayLengthValue
    : arrayLengthRange (',' arrayLengthRange)*
    ;

arrayLengthRange
    :  IntegerLiteral ('TO' IntegerLiteral)*
    ;

primitiveType
    :   'boolean'
    |   'char'
    |   'byte'
    |   'short'
    |   'integer'
    |   'long'
    |   'float'
    |   'double'
    |   'real'
    |	'string'
    | 	'date'
    ;

// Integer Literals

IntegerLiteral
    :   DecimalIntegerLiteral
    ;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral IntegerTypeSuffix?
    ;

fragment
IntegerTypeSuffix
    :   [lL]
    ;

// Boolean Literals

BooleanLiteral
    :   'true'
    |   'false'
    ;

// Character Literals

CharacterLiteral
    :   '\'' SingleCharacter '\''
    |   '\'' EscapeSequence '\''
    ;

fragment
SingleCharacter
    :   ~['\\]
    ;
	
// String Literals

StringLiteral
    :   '"' StringCharacters? '"'
    ;
fragment
StringCharacters
    :   StringCharacter+
    ;
fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    ;

fragment
EscapeSequence
    :   '\\' [btnfr"'\\]
    |   OctalEscape
    |   UnicodeEscape
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    :   Digit (DigitOrUnderscore* Digit)?
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
OctalEscape
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment
HexDigits
    :   HexDigit (HexDigitOrUnderscore* HexDigit)?
    ;

fragment
HexDigitOrUnderscore
    :   HexDigit
    |   '_'
    ;

fragment
HexDigit
    :   [0-9a-fA-F]
    ;

fragment
OctalDigits
    :   OctalDigit (OctalDigitOrUnderscore* OctalDigit)?
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
OctalDigitOrUnderscore
    :   OctalDigit
    |   '_'
    ;

fragment
DigitOrUnderscore
    :   Digit
    |   '_'
    ;

fragment
Underscores
    :   '_'+
    ;

// DateTimeLiteral
DateTimeLiteral
	: DateTimeYear '-' DateTimeMonth '-' DateTimeDay
	;

fragment
DateTimeYear
	: ZeroToTwo Digit Digit Digit
	;

fragment
DateTimeMonth
	: ZeroToOne Digit
	;

fragment
DateTimeDay
	: ZeroToThree Digit
	;

fragment
ZeroToThree
	: [0-3]
	;
	
fragment
ZeroToTwo
	: [0-2]
	;

fragment
ZeroToOne
	: [0-1]
	;

delimiter
    :   '\n'+?
    ;

// SEPARATORS

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';
QUOTE			: '"';

// §3.12 OPERATORS

ASSIGN          : '=';
GT              : '>';
LT              : '<';
BANG            : '!';
TILDE           : '~';
QUESTION        : '?';
COLON           : ':';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : 'AND';
OR              : 'OR';
INC             : '++';
DEC             : '--';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
BITAND          : '&';
BITOR           : '|';
CARET           : '^';
MOD             : '%';

ADD_ASSIGN      : '+=';
SUB_ASSIGN      : '-=';
MUL_ASSIGN      : '*=';
DIV_ASSIGN      : '/=';
AND_ASSIGN      : '&=';
OR_ASSIGN       : '|=';
XOR_ASSIGN      : '^=';
MOD_ASSIGN      : '%=';
LSHIFT_ASSIGN   : '<<=';
RSHIFT_ASSIGN   : '>>=';
URSHIFT_ASSIGN  : '>>>=';

// § INDENTIFIERS (must appear after all keywords in the grammar)

Identifier
    :   PBLetter PBLetterOrDigit*
    ;

fragment
PBLetter
    :   [a-zA-Z$-_%] 
    ;

fragment
PBLetterOrDigit
    :   [a-zA-Z0-9$-_%] 
    ;

// § COMMENTS & WHITESPACES
COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
	
WS: [ \t\r]+ -> skip;
