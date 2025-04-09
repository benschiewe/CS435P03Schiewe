grammar P03Schiewe;


program
            : stmt_list EOF
            ;


stmt_list
            : stmt stmt_list
            | 
            ;


stmt
            : ID ':=' expr
            | 'read(' id_list ');'
            | 'write(' expr_list ');'
            ;


expr_list
            : expr expr_list_tail
            ;


expr_list_tail
            : ',' expr expr_list_tail
            |
            ;


id_list
            : ID id_list_tail
            ;


id_list_tail
            : ',' ID id_list_tail
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
            : '(' expr ')'
            | ID
            | NUMBER
            ;


add_op
            : '+'
            | '-'
            ;


mult_op
            : '*'
            | '/'
            ;


ID      : [a-zA-Z_][a-zA-Z_0-9]* ;
NUMBER  : [0-9]+ ;
WS      : [ \t\r\n]+ -> skip ;