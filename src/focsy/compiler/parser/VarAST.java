package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class VarAST extends ExprAST {
    private Token identToken;

    public VarAST(Token identToken) {
        this.identToken = identToken;
    }

    public Token getIdentToken() {
        return identToken;
    }

    public void setIdentToken(Token identToken) {
        this.identToken = identToken;
    }

    @Override
    public FileRange getRange() {
        return identToken.getRange();
    }

    @Override
    public String toString() {
        return identToken.toString();
    }
}
