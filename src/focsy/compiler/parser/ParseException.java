package focsy.compiler.parser;

import focsy.compiler.FileLocation;

/**
 * Created by Bertie on 28/01/14.
 */
public class ParseException extends RuntimeException {
    private final FileLocation loc;

    public ParseException(String message, FileLocation loc) {
        super(message);
        this.loc = loc;
    }

    public FileLocation getLoc() {
        return loc;
    }
}
