package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class IntAST extends ExprAST {
    private Token intToken;

    public IntAST(Token intToken) {
        this.intToken = intToken;
    }

    public Token getIntToken() {
        return intToken;
    }

    public void setIntToken(Token intToken) {
        this.intToken = intToken;
    }

    @Override
    public FileRange getRange() {
        return intToken.getRange();
    }
}
