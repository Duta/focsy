package focsy.compiler.parser;

import focsy.compiler.FileRange;

/**
 * Created by Bertie on 28/01/14.
 */
public class ParseException extends RuntimeException {
    private final FileRange range;

    public ParseException(String message, FileRange range) {
        super(message);
        this.range = range;
    }

    public FileRange getRange() {
        return range;
    }
}
