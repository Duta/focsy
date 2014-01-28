package focsy.compiler.lexer;

/**
 * Created by Bertie on 28/01/14.
 */
public class Token {
    private final TokenType type;
    private final String text;
    private final int line, col;

    public Token(TokenType type, String text, int line, int col) {
        this.type = type;
        this.text = text;
        this.line = line;
        this.col = col;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Token{type={" + type + "},text={" + text
            + "},line={" + line + "},col={" + col + "}";
    }
}
