package focsy.compiler.lexer;

import focsy.compiler.FileRange;

/**
 * Created by Bertie on 28/01/14.
 */
public class LexException extends RuntimeException {
    private final FileRange range;

    public LexException(String message, FileRange range) {
        super(message);
        this.range = range;
    }

    public FileRange getRange() {
        return range;
    }
}
