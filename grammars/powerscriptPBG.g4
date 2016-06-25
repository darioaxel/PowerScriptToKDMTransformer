/*
*	Original Author: Darío Ureña
*	E-Mail: darioaxel@gmail.com
*/

grammar powerscriptPBG;

@header {
package org.darioaxel.grammar.powerscript.pbg;
}


prog
	: header libraries objects EOF
	;

header
	: 'Save Format v3.0(' NUMBER ')' #headers
	;

libraries
	: HEADER_BEGIN pathsFromTo+ ENDS SEMICOL  #Intolibrarires 
	;

objects
	: OBJECTS_BEGIN pathsFromTo+ ENDS SEMICOL #Intoobjects
	;

pathsFromTo
	: path path SEMICOL
	;
	
path
	: (ID DOUBLESLASH)*? file
	;

file
	: ID DOT ID
	; 

HEADER_BEGIN : '@begin Libraries';
OBJECTS_BEGIN : '@begin Objects';
ENDS : '@end' ;

NUMBER : [0-9]+;
ID  :   [a-zA-Z0-9_]+ ;
QUOTE : '"';
DOT: '.';
DOUBLESLASH : '\\\\';
SEMICOL : ';' ;
WS: [ \t\n\r]+ -> skip;
