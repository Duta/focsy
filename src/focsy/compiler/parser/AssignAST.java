package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class AssignAST extends ExprAST {
    private Token identToken;
    private Token equalsToken;
    private ExprAST expr;

    public AssignAST(Token identToken, Token equalsToken, ExprAST expr) {
        this.identToken = identToken;
        this.equalsToken = equalsToken;
        this.expr = expr;
    }

    public Token getIdentToken() {
        return identToken;
    }

    public void setIdentToken(Token identToken) {
        this.identToken = identToken;
    }

    public Token getEqualsToken() {
        return equalsToken;
    }

    public void setEqualsToken(Token equalsToken) {
        this.equalsToken = equalsToken;
    }

    public ExprAST getExpr() {
        return expr;
    }

    public void setExpr(ExprAST expr) {
        this.expr = expr;
    }

    @Override
    public FileRange getRange() {
        return new FileRange(
                identToken.getRange().getStart(),
                expr.getRange().getEnd());
    }
}
