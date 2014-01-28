package focsy.compiler;

import focsy.compiler.lexer.TokenType;

/**
 * Created by Bertie on 28/01/14.
 */
public class Token {
    private final TokenType type;
    private final String text;
    private final FileRange range;

    public Token(TokenType type, String text, FileRange range) {
        this.type = type;
        this.text = text;
        this.range = range;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public FileRange getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Token{type={" + type + "},text={" + text
            + "},range={" + range + "}}";
    }
}
