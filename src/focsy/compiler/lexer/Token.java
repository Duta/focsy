package focsy.compiler.lexer;

import focsy.compiler.FileLocation;

/**
 * Created by Bertie on 28/01/14.
 */
public class Token {
    private final TokenType type;
    private final String text;
    private final FileLocation loc;

    public Token(TokenType type, String text, FileLocation loc) {
        this.type = type;
        this.text = text;
        this.loc = loc;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public FileLocation getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "Token{type={" + type + "},text={" + text
            + "},loc={" + loc + "}}";
    }
}
