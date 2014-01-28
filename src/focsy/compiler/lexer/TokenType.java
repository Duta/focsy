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
    PLUS,
    MINUS,
    ASTERISK,
    FWD_SLASH,
    BACK_SLASH,
    INT_NUM,
    FLOAT_NUM,
    IDENT,
    WHITESPACE;

    @Override
    public String toString() {
        switch(this) {
            case L_PAREN:
                return "a '('";
            case R_PAREN:
                return "a ')'";
            case L_SQ_BRACK:
                return "a '['";
            case R_SQ_BRACK:
                return "a ']'";
            case L_CURLY:
                return "a '{'";
            case R_CURLY:
                return "a '}'";
            case COMMA:
                return "a ','";
            case SEMICOLON:
                return "a ';'";
            case LESS_THAN:
                return "a '<'";
            case GREATER_THAN:
                return "a '>'";
            case EQUALS:
                return "a '='";
            case PLUS:
                return "a '+'";
            case MINUS:
                return "a '-'";
            case ASTERISK:
                return "a '*'";
            case FWD_SLASH:
                return "a '/'";
            case BACK_SLASH:
                return "a '\\'";
            case INT_NUM:
                return "an int literal";
            case FLOAT_NUM:
                return "a float literal";
            case IDENT:
                return "an identifier";
            case WHITESPACE:
                return "whitespace";
            default:
                return "<Unknown>";
        }
    }
}
