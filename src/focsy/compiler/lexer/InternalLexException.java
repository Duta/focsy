package focsy.compiler.lexer;

import focsy.compiler.FileLocation;

/**
 * Created by Bertie on 28/01/14.
 */
public class InternalLexException extends LexException {
    public InternalLexException(String message, FileLocation loc) {
        super(message, loc);
    }
}
