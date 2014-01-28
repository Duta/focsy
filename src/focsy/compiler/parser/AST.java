package focsy.compiler.parser;

import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class AST {
    private Token token;

    public AST(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
