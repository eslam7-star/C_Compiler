grammar c_g;

program
    : (externalDeclaration)+ EOF
    ;

externalDeclaration
    : functionDefinition
    | declaration
    ;

functionDefinition
    : declarationSpecifier+ declarator compoundStatement
    ;

declaration
    : declarationSpecifier+ (initDeclaratorList)? SEMICOLON
    | declarationSpecifiers (initDeclaratorList)? SEMICOLON
    ;

declarationSpecifier
    : typeSpecifier
    | typeQualifier
    ;

typeSpecifier
    : VOID
    | CHAR
    | INT
    | FLOAT
    | DOUBLE
    | structOrUnionSpecifier
    | enumSpecifier
    | typedefName
    ;

typeQualifier
    : CONST
    | VOLATILE
    ;

structOrUnionSpecifier
    : structOrUnion (identifier)? LBRACE structDeclaration+ RBRACE
    | structOrUnion identifier
    ;

structOrUnion
    : STRUCT
    | UNION
    ;

structDeclaration
    : specifierQualifierList structDeclaratorList SEMICOLON
    ;

specifierQualifierList
    : (typeSpecifier | typeQualifier)+
    ;

structDeclaratorList
    : structDeclarator (COMMA structDeclarator)*
    ;

structDeclarator
    : declarator
    | declarator? COLON constantExpression
    ;

enumSpecifier
    : ENUM (identifier)? LBRACE enumeratorList? RBRACE
    | ENUM identifier
    ;

enumeratorList
    : enumerator (COMMA enumerator)*
    ;

enumerator
    : enumerationConstant
    | enumerationConstant ASSIGN constantExpression
    ;

declarationSpecifiers
    : declarationSpecifier+ (functionSpecifier)*
    ;


initDeclaratorList
    : initDeclarator (COMMA initDeclarator)*
    ;

initDeclarator
    : declarator (ASSIGN initializer)?
    ;

declarator
    : pointer? directDeclarator
    ;

directDeclarator
    : identifier
    | LPAREN declarator RPAREN
    | directDeclarator LBRACKET (constantExpression)? RBRACKET
    | directDeclarator LPAREN parameterTypeList RPAREN
    | directDeclarator LPAREN identifierList? RPAREN
    ;

pointer
    : '*' (typeQualifier)*
    ;


identifierList
    : identifier (COMMA identifier)*
    ;

parameterTypeList
    : parameterList (COMMA ELLIPSIS)?
    | ELLIPSIS
    ;

parameterList
    : parameterDeclaration (COMMA parameterDeclaration)*
    ;

parameterDeclaration
    : declarationSpecifier+ declarator
    ;

initializer
    : ASSIGN initializer
    | assignmentExpression
    ;

compoundStatement
    : LBRACE (declaration | statement)* RBRACE
    ;

statement
    : labeledStatement
    | expressionStatement
    | compoundStatement
    | selectionStatement
    | iterationStatement
    | jumpStatement
    ;

labeledStatement
    : identifier COLON statement
    | CASE constantExpression COLON statement
    | DEFAULT COLON statement
    ;

expressionStatement
    : expression? SEMICOLON
    ;

selectionStatement
    : IF LPAREN expression RPAREN statement (ELSE statement)?
    | SWITCH LPAREN expression RPAREN statement
    ;

iterationStatement
    : WHILE LPAREN expression RPAREN statement
    | DO statement WHILE LPAREN expression RPAREN SEMICOLON
    | FOR LPAREN expressionStatement expressionStatement? expression? RPAREN statement
    ;

jumpStatement
    : CONTINUE SEMICOLON
    | BREAK SEMICOLON
    | RETURN expression? SEMICOLON
    | GOTO identifier SEMICOLON
    ;

expression
    : assignmentExpression (COMMA assignmentExpression)*
    ;

assignmentExpression
    : conditionalExpression
    | unaryExpression assignmentOperator assignmentExpression
    ;

conditionalExpression
    : logicalOrExpression (QUESTION expression COLON conditionalExpression)?
    ;

logicalOrExpression
    : logicalAndExpression (OR logicalAndExpression)*
    ;

logicalAndExpression
    : inclusiveOrExpression (AND inclusiveOrExpression)*
    ;

inclusiveOrExpression
    : exclusiveOrExpression (BIT_OR exclusiveOrExpression)*
    ;

exclusiveOrExpression
    : andExpression (BIT_XOR andExpression)*
    ;

andExpression
    : equalityExpression (BIT_AND equalityExpression)*
    ;

equalityExpression
    : relationalExpression ((EQ | NE) relationalExpression)*
    ;

relationalExpression
    : shiftExpression ((LT | GT | LE | GE) shiftExpression)*
    ;

shiftExpression
    : additiveExpression ((LEFT_SHIFT | RIGHT_SHIFT) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((ADD | SUB) multiplicativeExpression)*
    ;

multiplicativeExpression
    : castExpression ((MUL | DIV | MOD) castExpression)*
    ;

castExpression
    : LPAREN typeName RPAREN castExpression
    | unaryExpression
    ;

unaryExpression
    : postfixExpression
    | INC unaryExpression
    | DEC unaryExpression
    | unaryOperator castExpression
    | SIZEOF unaryExpression
    | SIZEOF LPAREN typeName RPAREN
    ;

postfixExpression
    : primaryExpression
    | postfixExpression LBRACKET expression RBRACKET
    | postfixExpression LPAREN argumentExpressionList? RPAREN
    | postfixExpression DOT identifier
    | postfixExpression ARROW identifier
    | postfixExpression INC
    | postfixExpression DEC
    ;

primaryExpression
    : identifier
    | INT_CONSTANT
    | FLOAT_CONSTANT
    | CHAR_CONSTANT
    | string
    | LPAREN expression RPAREN
    ;

argumentExpressionList
    : assignmentExpression (COMMA assignmentExpression)*
    ;

string
    : STRING_LITERAL+
    ;



typeName
    : specifierQualifierList abstractDeclarator?
    ;

abstractDeclarator
    : pointer
    | pointer? directAbstractDeclarator
    ;

directAbstractDeclarator
    : LPAREN abstractDeclarator RPAREN
    | directAbstractDeclarator LBRACKET (constantExpression)? RBRACKET
    | LBRACKET (constantExpression)? RBRACKET
    | LPAREN parameterTypeList RPAREN
    ;

unaryOperator
    : AND
    | MUL
    | ADD
    | SUB
    | BIT_NOT
    | NOT
    ;

assignmentOperator
    : ASSIGN
    | MUL_ASSIGN
    | DIV_ASSIGN
    | MOD_ASSIGN
    | ADD_ASSIGN
    | SUB_ASSIGN
    | LEFT_ASSIGN
    | RIGHT_ASSIGN
    | AND_ASSIGN
    | XOR_ASSIGN
    | OR_ASSIGN
    ;

identifier
    : IDENTIFIER
    ;


fragment
LETTER : 'a'..'z' | 'A'..'Z' ;
fragment
ALPHA : LETTER | '_' ;
fragment
ALPHANUMERIC : ALPHA | [0-9] ;

WHITESPACE : [ \t\n\r] -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;

FOR : 'for' ;
WHILE : 'while' ;
DO : 'do' ;
IF : 'if' ;
ELSE : 'else' ;
RETURN : 'return' ;
CONTINUE : 'continue' ;
BREAK : 'break' ;
SWITCH : 'switch' ;
CASE : 'case' ;
DEFAULT : 'default' ;
GOTO : 'goto' ;
INT : 'int' ;
FLOAT : 'float' ;
DOUBLE : 'double' ;
VOID : 'void' ;
CHAR : 'char' ;
ENUM : 'enum' ;
STRUCT : 'struct' ;
UNION : 'union' ;
CONST : 'const' ;
VOLATILE : 'volatile' ;
TYPEDEF : 'typedef' ;
EXTERN : 'extern' ;
STATIC : 'static' ;
AUTO : 'auto' ;
REGISTER : 'register' ;
SIGNED : 'signed' ;
UNSIGNED : 'unsigned' ;
SIZEOF : 'sizeof' ;
PTRDIFF : 'ptrdiff_t' ;
LONG : 'long' ;
SHORT : 'short' ;
INLINE : 'inline' ;
COMPLEX : '_Complex' ;
IMAGINARY : '_Imaginary' ;
BOOL : '_Bool' ;
BOOL_CONSTANT : 'true' | 'false' ;
ELLIPSIS : '...' ;
CONSTANT : INT_CONSTANT | FLOAT_CONSTANT | CHAR_CONSTANT | BOOL_CONSTANT ;
INT_CONSTANT : [0-9]+ ;
FLOAT_CONSTANT : [0-9]+ '.' [0-9]* ( [eE] [+-]? [0-9]+ )? ;
CHAR_CONSTANT : '\'' . '\'' ;
STRING_LITERAL : '"' (~["\r\n])* '"' ;
IDENTIFIER : ALPHA (ALPHANUMERIC)* ;
typedefName : IDENTIFIER ;
constantExpression : expression ;
enumerationConstant : IDENTIFIER ;
functionSpecifier : INLINE | EXTERN | STATIC | AUTO | REGISTER ;
SEMICOLON : ';';
LBRACE : '{';
RBRACE : '}';
COMMA : ',';
COLON : ':';
ASSIGN : '=';
LPAREN : '(';

RPAREN : ')';

LBRACKET : '[';

RBRACKET : ']';

QUESTION : '?';

OR : '||';

AND : '&&';
BIT_OR : '|';

BIT_XOR : '^';

BIT_AND : '&';

EQ : '==';

NE : '!=';
LEFT_SHIFT : '<<' ;
RIGHT_SHIFT : '>>' ;
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;
MUL_ASSIGN : '*=' ;
DIV_ASSIGN : '/=' ;
MOD_ASSIGN : '%=' ;
ADD_ASSIGN : '+=' ;
SUB_ASSIGN : '-=' ;
LEFT_ASSIGN : '<<=' ;
RIGHT_ASSIGN : '>>=' ;
AND_ASSIGN : '&=' ;
XOR_ASSIGN : '^=' ;
OR_ASSIGN : '|=' ;
INC : '++' ;
DEC : '--' ;
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';
DOT : '.';
ARROW : '->' ;
BIT_NOT : '~' ;
NOT : '!' ;

