package focsy.compiler.lexer;

/**
 * Created by Bertie on 28/01/14.
 */
public class InternalLexException extends LexException {
    public InternalLexException(String message, int line, int col) {
        super(message, line, col);
    }
}
