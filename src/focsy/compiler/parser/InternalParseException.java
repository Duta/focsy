package focsy.compiler.parser;

import focsy.compiler.FileLocation;

/**
 * Created by Bertie on 28/01/14.
 */
public class InternalParseException extends ParseException {
    public InternalParseException(String message, FileLocation loc) {
        super(message, loc);
    }
}
