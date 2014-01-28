package focsy.compiler.lexer;

/**
 * Created by Bertie on 28/01/14.
 */
public enum TokenType {
    L_PAREN,
    R_PAREN,
    L_SQ_BRACK,
    R_SQ_BRACK,
    L_CURLY,
    R_CURLY,
    COMMA,
    SEMICOLON,
    LESS_THAN,
    GREATER_THAN,
    EQUALS,
    INT_NUM,
    FLOAT_NUM,
    IDENT,
    WHITESPACE;

    @Override
    public String toString() {
        switch(this) {
            case L_PAREN:
                return "LeftParen";
            case R_PAREN:
                return "RightParen";
            case L_SQ_BRACK:
                return "LeftSquareBracket";
            case R_SQ_BRACK:
                return "RightSquareBracket";
            case L_CURLY:
                return "LeftCurlyBracket";
            case R_CURLY:
                return "RightCurlyBracket";
            case COMMA:
                return "Comma";
            case SEMICOLON:
                return "Semicolon";
            case LESS_THAN:
                return "LessThan";
            case GREATER_THAN:
                return "GreaterThan";
            case EQUALS:
                return "Equals";
            case INT_NUM:
                return "IntegerNumber";
            case FLOAT_NUM:
                return "FloatingPointNumber";
            case IDENT:
                return "Identifier";
            case WHITESPACE:
                return "Whitespace";
            default:
                return "<Unknown>";
        }
    }
}
