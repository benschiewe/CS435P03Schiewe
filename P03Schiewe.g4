grammar P03Schiewe;

program
            : stmt_list
            ;

stmt_list
            : stmt stmt_list
            | 
            ;

stmt
            : ID ASSIGN expr SEMICOLON
            | 'read' LPAREN id_list RPAREN SEMICOLON
            | 'write' LPAREN expr_list RPAREN SEMICOLON
            ;

expr_list
            : expr expr_list_tail
            ;

expr_list_tail
            : COMMA expr expr_list_tail
            |
            ;

id_list
            : ID id_list_tail
            ;

id_list_tail
            : COMMA ID id_list_tail
            |
            ;

expr
            : term term_tail
            ;

term_tail
            : add_op term term_tail
            |
            ;

term
            : factor factor_tail
            ;

factor_tail
            : mult_op factor factor_tail
            |
            ;

factor
            : LPAREN expr RPAREN
            | ID
            | NUMBER
            ;

add_op
            : '+'
            | '-'
            ;

mult_op
            : TIMES
            | DIV
            ;

ID      : [a-zA-Z_][a-zA-Z_0-9]* ;
NUMBER  : [0-9]+ ;
SEMICOLON : ';' ;
TIMES   : '*' ;
DIV     : '/' ;
COMMA   : ',' ;
LPAREN  : '(' ;
RPAREN  : ')' ;
ASSIGN  : ':=' ;
WS      : [ \t\r\n]+ -> skip ;