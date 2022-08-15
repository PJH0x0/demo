grammar compiler;

If : 'if';
Int : 'int';

IntLiteral : [0-9]+;
StringLiteral : '"'.*?'"';

AssignmentOP : '=';
RelationalOP : '>'|'>='|'<'|'<=';
Star : '*';
Plus : '+';
Sharp: '#';
SemiColon: ';';
Dot: '.';
Comm: ',';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';
LeftParen: '(';
RightParen: ')';

Id: [a-zA-Z_]([a-zA-Z_] | [0-9])*;
WhiteSpace: [ \t]+ -> skip;
Newline: ( '\r' '\n'?|'\n') -> skip;

additiveExpression
    : multiplicativeExpression 
    | additiveExpression '+' multiplicativeExpression
    | additiveExpression '-' multiplicativeExpression;
multiplicativeExpression    
:   IntLiteral    
|   multiplicativeExpression '*' IntLiteral    
|   multiplicativeExpression '/' IntLiteral    
|   multiplicativeExpression '%' IntLiteral;