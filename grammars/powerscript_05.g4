/**
*	Original Author: Darío Ureña
*	E-Mail: darioaxel@gmail.com
*/

grammar powerscript_05;

@header {
package org.darioaxel.grammar.powerscript;
}


compilationUnit
    :  memberDeclaration*? EOF
    ;

memberDeclaration
    : forwardDeclaration            	
    | typeDeclaration					
    | localVariableDeclarationBlock
    | globalVariableDeclarationBlock
    | variableDeclaration
	| objectDeclaration
    | constantDeclaration
    | functionDeclaration
    | functionDeclarationBlock
    | functionImplementation
    | onImplementation
    | eventImplementation
    ;

// 1. Forward Declaration
forwardDeclaration
	: forwardDeclarationBegin forwardDeclarationBody* forwardDeclarationEnd	  
	;
	
forwardDeclarationBegin
	: 'forward' delimiter?
	;
	
forwardDeclarationEnd
	:  'end forward' delimiter?
	;
	
forwardDeclarationBody
	: objectDeclaration
	| typeDeclaration
	;

// 2. Type Declaration	
typeDeclaration
	: typeDeclarationBegin typeDeclarationBody*? typeDeclarationEnd
	;

typeDeclarationBegin
	: scopeModificator? typeDeclarationBeginIdentifier typeDeclarationBeginParent? delimiter?
	;
	
typeDeclarationBeginIdentifier
	: 'type' Identifier 'from' Identifier
	;
	
typeDeclarationBeginParent
	: '`' Identifier typeDeclarationWithin?
	| typeDeclarationWithin
	;
	
typeDeclarationWithin
	: 'within' Identifier
	;
	
typeDeclarationBody							
	: typeDeclarationDescriptor
	| variableDeclaration
	| eventDeclaration
	;

typeDeclarationDescriptor
	: 'descriptor' '"' Identifier '"' '=' '"' Identifier '"' delimiter
	;
	
typeDeclarationEnd
	: 'end type' delimiter?
	;

// 3. Local Variable Declaration Block
localVariableDeclarationBlock
	: localVariableDeclarationBegin  localVariableDeclarationBody* variableDeclarationEnd
	;

localVariableDeclarationBody
	: variableDeclaration
    | constantDeclaration  
	| modifier delimiter?
	;
	
localVariableDeclarationBegin
	: 'type variables' delimiter?
	;

variableDeclarationEnd
	: 'end variables' delimiter?
	;

// 4. Global Variable Declaration Block
globalVariableDeclarationBlock
    : globalVariableDeclarationBlockBegin globalVariableDeclarationBlockBody*? variableDeclarationEnd
    ;

globalVariableDeclarationBlockBegin
    : 'variables' delimiter?
	| 'global variables' delimiter?
	| 'shared variables' delimiter?
    ;

globalVariableDeclarationBlockBody
    : variableDeclaration
    | constantDeclaration
    ;

// 5. Variable Declaration
variableDeclaration
    :   extendedAccessType? scopeModificator? type variableDeclarators delimiter? 
    ;

variableDeclarators
    :   variableDeclarator (',' variableDeclarator)* delimiter?
    ;

variableDeclarator
    :   Identifier '=' literal delimiter?
	|   Identifier arrayLengthDeclarator arrayValueInstantiation? delimiter?
	|   Identifier delimiter?
    ;

// 6. Constants Declaration
constantDeclaration
    :   'constant' type constantDeclarator (',' constantDeclarator)* delimiter?
    ;

constantDeclarator
    : Identifier '=' literal delimiter?
	| Identifier arrayLengthDeclarator arrayValueInstantiation? delimiter?
    ;
	
arrayValueInstantiation
	:  '=' '{' literal (',' literal)*? '}'
	;

// 6.1 Object Declaration
objectDeclaration
	:  Identifier Identifier objectValueInstantiation? delimiter?
	|  'this' '.' Identifier objectValueInstantiation delimiter?
	;
	
objectValueInstantiation
	: '=' Identifier
	| '=' literal
	;

// 8. Function Declaration Block
functionDeclarationBlock
    : functionDeclarationBlockHeader functionDeclaration* functionDeclarationBlockEnd
    ;

functionDeclarationBlockHeader
    : 'forward prototypes' delimiter?
	| 'type prototypes' delimiter?
    ;
	
functionDeclarationBlockBody
    : functionDeclaration
	| eventDeclaration
    ;

functionDeclarationBlockEnd
    : 'end prototypes' delimiter?
    ;

// 7. Function Declaration
functionDeclaration
    : functionDeclarationHeader parametersList functionDeclarationEnd delimiter
    ;

functionDeclarationHeader
    : accessType? scopeModificator? functionHeaderIdentification 
    ;

functionDeclarationEnd
    : functionDeclarationEndLibrary? functionDeclarationEndRPC? functionDeclarationEndThrows?
    ;

functionDeclarationEndLibrary
    : 'library' Identifier ('alias for' Identifier)?
    ;

functionDeclarationEndRPC
    : 'rpcfunc alias for' Identifier
    ;
  
// 9. Function Implementation
functionImplementation
    : functionImplementationHeader functionImplementationBody* functionImplementationEnd delimiter?
    ;
	
functionImplementationHeader
    : primaryAccessType scopeModificator? functionHeaderIdentification parametersList functionDeclarationEndThrows? ';' delimiter?
    ;

functionHeaderIdentification
    : 'function' dataTypeName Identifier
	| 'subroutine' Identifier
    ;

functionDeclarationEndThrows
    : 'throws' Identifier
    ;

functionImplementationBody
    : statementBlock 
    ;

functionImplementationEnd
    : 'end function'
    | 'end subroutine'
    ;

// 10. On Implementation
onImplementation
    : onImplementationHead onImplementationBody onImplementationEnd
    ;

onImplementationHead
    : 'on' onImplementationIdentifier delimiter?
    ;

onImplementationIdentifier
    : expression '.' creatorType delimiter?
    ;

onImplementationBody
    : onImplementationBodyStatement*
    ;

onImplementationBodyStatement
    : statementBlock
    ;

onImplementationEnd
    : 'end on' delimiter?
    ;

// 11. Event Declaration
eventDeclaration
    : 'event' Identifier parametersList ';' delimiter?
    ;

creatorType
    : 'create'
    | 'destroy'
	| 'open'
	| 'close'
    ;

// 12. Event Implementation
eventImplementation
    : eventImplementationHead eventImplementationBody* eventImplementationEnd
    ;

eventImplementationHead
    : eventDeclaration
	| 'event' creatorType ';' delimiter?
	| 'event' Identifier ('::' Identifier)? ';' delimiter?
    ;

eventImplementationBody 
    : statementBlock
    ;

eventImplementationEnd
    : 'end event' delimiter?
    ;    
	
parametersList
    : '(' parametersDeclarators? ')'
    ;
	
parametersDeclarators 
    : parameterDeclarator (',' parameterDeclarator)*?
    ;

parameterDeclarator
    : 'readonly'? 'ref'? primitiveType Identifier arrayType?
    ;

arrayType
	: '[ ]'
	;

scopeModificator
    : 'global'
    | 'local'
    | 'shared'
    ;
	
statementBlock
    : variableDeclaration
	| objectDeclaration
	| statement
    ;
    
statement
    : expression
	| ifStatement
	| callStatement
	| tryCatchStatement
	| doLoopWhileStatement
	| forStatement
	| returnStatement
	| destroyStatement
	| superStatement
	| throwStatement
	| goToStatement
	| excapeStatement
	| chooseStatement
	| sqlStatement
    ;

doLoopWhileStatement
    :   doWhileUntilLoop
    |   doLoopWhileUntil
    ;

doWhileUntilLoop
    :   'DO' ('UNTIL' | 'WHILE') expression delimiter? statementBlock* delimiter 'LOOP' delimiter?
    ;

doLoopWhileUntil
    :   'DO' delimiter statementBlock* delimiter? 'LOOP' ('WHILE' | 'UNTIL') expression delimiter?
    ;

tryCatchStatement
    : tryStatement catchStatement*? finallyStatement? endTryStatement
    ;

tryStatement 
    : 'TRY' delimiter statementBlock* delimiter?
    ;

catchStatement
    : 'CATCH' '(' variableDeclaration ')' delimiter? statementBlock* delimiter?
    ;

finallyStatement
    : 'FINALLY' delimiter statementBlock* delimiter?
    ;

endTryStatement
    : 'END TRY' delimiter?
    ;

forStatement
	: forStatementBegin forStatementBody forStatementEnd
	;

forStatementBody
	: statementBlock* delimiter?
	;

forStatementEnd
	: 'NEXT' delimiter?
	;

forStatementBegin
	: 'FOR' expression delimiter? forStatementBeginTo?
	;

forStatementBeginTo
	: 'TO' expression forStatementBeginToStep? delimiter?
	;

forStatementBeginToStep
	: 'STEP' IntegerLiteral
	;

ifStatement
	: 'IF' expression ifStatementThen ifStatementEnd 
	;

ifStatementBody
	: statementBlock*
	;

ifStatementElseIf
	: 'ELSEIF' expression ifStatementThen ifStatementEnd
	| 'ELSE' delimiter? ifStatementBody
	;

ifStatementEnd
	: 'END IF' delimiter?
	;
	
ifStatementThen
	: 'THEN' delimiter? ifStatementBody ifStatementElseIf*
	;

goToStatement
	: 'GOTO' Identifier
	;

destroyStatement
	: 'destroy' expression
	;
	
chooseStatement
	: chooseStatementBegin chooseStatementCase+ chooseStatementEnd delimiter?
	;
	
chooseStatementBegin
	: 'CHOOSE CASE' expression delimiter?
	;

chooseStatementCase
	: 'CASE' literal statementBlock*
	| 'CASE ELSE' statementBlock*
	;

chooseStatementEnd
	: 'END CHOOSE' delimiter?
	;

createStatement
    : 'create' expression delimiter?
    ;
	
returnStatement
	: 'RETURN' expression? delimiter?
	;

superStatement
	: 'SUPER' expression? delimiter?
	;

throwStatement
	: 'THROW' expression
	;

callStatement
	: 'call' Identifier callStatementSubControl? '::' creatorType delimiter?
	| 'call' Identifier callStatementSubControl? '::' Identifier delimiter?
	;

callStatementSubControl
	: '`' Identifier
	;	

excapeStatement
	: 'EXIT'
	| 'HALT'
	| 'CONTINUE'
	;

sqlStatement
	: commitStatement
	| connectStatement
	| rollbackStatement
	| disconnectStatement
	| selectStatement
	| updateStatement
	| insertStatement
	| deleteStatement	
	| openCursorStatement
	| closeCursorStatement
	| declareCursorStatement
	| fetchCursorStatement
	;

openCursorStatement
	: 'OPEN' Identifier ';' delimiter?
	;

closeCursorStatement
	: 'CLOSE' Identifier ';' delimiter?
	;

declareCursorStatement
	: 'DECLARE' expression 'CURSOR FOR' selectStatement delimiter?
	;
	
commitStatement
	: 'COMMIT;' delimiter?
	;

updateStatement
	: 'UPDATE' Identifier 'SET' expression+ 'WHERE' expression+ sqlUsingStatement? ';' delimiter?
	;

deleteStatement
	: 'DELETE FROM' Identifier 'WHERE' expression+ sqlUsingStatement? ';' delimiter?
	;

insertStatement
	: 'INSERT INTO' Identifier '(' tableFields ')' 'VALUES' '(' variablesSelected ')' sqlUsingStatement? ';' delimiter?
	;

sqlUsingStatement
	: 'USING' expression
	;

fetchCursorStatement
	: 'FETCH' Identifier 'INTO' variablesSelected ';' delimiter?
	;
	
connectStatement
	: 'CONNECT USING' expression ';' delimiter?
	| 'CONNECT' ';' delimiter? 
	;

disconnectStatement
	: 'DISCONNECT' expression ';' delimiter?
	;

rollbackStatement
	: 'ROLLBACK;' delimiter
	;

selectStatement
	: 'SELECT' tableFields 'INTO' variablesSelected 'FROM' tableFields 'WHERE' expression+ sqlUsingStatement? ';' 
	;

tableFields
	: qualifiedName qualifiedName? (',' qualifiedName qualifiedName?)*? delimiter?
	;

variablesSelected
	: variableSelected (',' variableSelected)*? delimiter?
	;

variableSelected
	: ':' Identifier delimiter?		
	;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;

expression 
    :   primary delimiter? #literalEndExpression
	|   variableSelected   #variableSelectedExpression
	|   expression '.' Identifier delimiter? #objectVariableExpression
    |   expression '[' expression? ']' #arrayValuesExpression
	|   expression '=' 'create' 'using'? Identifier delimiter? #createUsingExpression	
    |   expression '(' expressionList? ')' delimiter? #subParentExpression
    |   expression ('+=' | '-=')  #AsingWithIncrementDecrementationExpression
    |   ('+'|'-') expression 		#NegativePositiveValueExpression
    |   expression ( '++' | '--') #PostIncrementDecrementExpression
    |   expression ('*'|'/'|'%'|'+'|'-') expression # MathExpression
    |   expression '<' expression     #LessThanExpression
	|   expression '>' expression     #GreaterThanExpression
	|   expression '<=' expression    #LessOrEqualThanExpression
	|   expression '>=' expression    #BiggerThanExpression
    |   expression ('==' | '!=') expression #EqualsDistintcExpression
    |   expression 'AND' expression   #AndExpression
    |   expression 'OR' expression	  #OrExpression
	|   'NOT' expression              #NotExpression
    |   expression '?' expression ':' expression #TriconditionalExpression
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
		| 	
        )
        expression #rightAssocExpression
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
	|   'this'
    ;

modifier
    :   'PUBLIC'':'
    |   'PRIVATE'':'
    |   'PROTECTED'':'
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

decimalLengthDeclarator
	: '{' IntegerLiteral '}'
	;
	
arrayLengthValue
    : arrayLengthRange (',' arrayLengthRange)*
    ;

arrayLengthRange
    :  IntegerLiteral ('TO' IntegerLiteral)*
    ;

delimiter
    :   '\n'+
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
    |   'int'
    |   'long'
	|   'decimal' decimalLengthDeclarator
    ;

// Integer Literals

IntegerLiteral
    :   ('-')? DecimalNumeral 
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
    |   NonZeroDigit Digit+
	|   NonZeroDigit
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

// § COMMENTS & WHITESPACES
COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
	
WS  : [ \n\t\r]+ -> skip;

// § INDENTIFIERS (must appear after all keywords in the grammar)

Identifier
    :   PBLetter PBLetterOrDigit*
    ;

fragment
PBLetter
    :   [a-zA-Z$_%] 
    ;

fragment
PBLetterOrDigit
    :   [a-zA-Z0-9$_%] // these are the "java letters or digits" below 0xFF
    ;


