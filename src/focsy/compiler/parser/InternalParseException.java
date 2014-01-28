package focsy.compiler.parser;

import focsy.compiler.FileRange;

/**
 * Created by Bertie on 28/01/14.
 */
public class InternalParseException extends ParseException {
    public InternalParseException(String message, FileRange range) {
        super(message, range);
    }
}
