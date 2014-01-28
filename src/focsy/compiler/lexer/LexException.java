package focsy.compiler.lexer;

/**
 * Created by Bertie on 28/01/14.
 */
public class LexException extends RuntimeException {
    private final int line, col;

    public LexException(String message, int line, int col) {
        super(message);
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }
}
