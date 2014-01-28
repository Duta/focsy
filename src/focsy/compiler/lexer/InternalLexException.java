package focsy.compiler.lexer;

import focsy.compiler.FileRange;

/**
 * Created by Bertie on 28/01/14.
 */
public class InternalLexException extends LexException {
    public InternalLexException(String message, FileRange range) {
        super(message, range);
    }
}
