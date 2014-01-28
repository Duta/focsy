package focsy.compiler.lexer;

import focsy.compiler.FileLocation;

/**
 * Created by Bertie on 28/01/14.
 */
public class LexException extends RuntimeException {
    private final FileLocation loc;

    public LexException(String message, FileLocation loc) {
        super(message);
        this.loc = loc;
    }

    public FileLocation getLoc() {
        return loc;
    }
}
