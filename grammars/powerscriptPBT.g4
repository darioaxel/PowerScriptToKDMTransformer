/*
*	Original Author: Darío Ureña
*	E-Mail: darioaxel@gmail.com
*/

grammar powerscriptPBT;

prog: header libraries objects EOF;

header: 'Save Format v3.0(' NUMBER ')' #headers;

libraries: HEADER_BEGIN pathsFromTo+ ENDS SEMICOL  #Intolibrarires ;

objects: OBJECTS_BEGIN pathsFromTo+ ENDS SEMICOL #Intoobjects;

pathsFromTo: path path SEMICOL;

path: '"' (( ID '\\\\')* file )* '"';
file: ID '.' ID; 
HEADER_BEGIN : '@begin Libraries';
OBJECTS_BEGIN : '@begin Objects';
ENDS : '@end' ;

NUMBER : [0-9]+;
ID  :   [a-zA-Z0-9_]+ ;
SEMICOL : ';' ;
WS: [ \t\n\r]+ -> skip;
