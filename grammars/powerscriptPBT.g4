/*
*	Original Author: Darío Ureña
*	E-Mail: darioaxel@gmail.com
*/

grammar powerscriptPBT;

@header {
package org.darioaxel.grammar.powerscript.pbt;
}

prog: header libraries*? EOF;

libraries
	: header
	| projects
	| appname
	| applib
	| listProjects
	| liblist
	| EndPbt
	;	

header
	: 'Save Format v3.0(' NUMBER ')' #headers
	;

projects
	: BeginProject listProjects+ EndProject 
	;

appname	
	: APPNAME QUOTE ID QUOTE SEMICOL
	;

applib
	: APPLIB QUOTE path QUOTE SEMICOL
	;

listProjects
	: NUMBER QUOTE path QUOTE SEMICOL
	;

liblist
	:  LIBLIST QUOTE (path SEMICOL)*?  QUOTE SEMICOL
	;

path
	: (ID DOUBLESLASH)*? file
	;

file
	: ID DOT ID
	; 

EndPbt	
	: TYPEPB SEMICOL
	;

BeginProject
	: '@begin Projects'
	;

EndProject
	: '@end'SEMICOL
	;

HEADER_BEGIN : '@begin Libraries';
OBJECTS_BEGIN : '@begin Objects';
APPNAME : 'appname';
APPLIB: 'applib';
TYPEPB: 'type "pb"';
LIBLIST: 'LibList';
NUMBER : [0-9]+;
ID  :   [a-zA-Z0-9_]+ ;
QUOTE : '"';
DOT: '.';
DOUBLESLASH : '\\\\';
SEMICOL : ';' ;
WS: [ \t\n\r]+ -> skip;
