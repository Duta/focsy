package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class SimpleStmtAST extends StmtAST {
    private ExprAST expr;
    private Token semicolonToken;

    public SimpleStmtAST(ExprAST expr, Token semicolonToken) {
        this.expr = expr;
        this.semicolonToken = semicolonToken;
    }

    public ExprAST getExpr() {
        return expr;
    }

    public void setExpr(ExprAST expr) {
        this.expr = expr;
    }

    public Token getSemicolonToken() {
        return semicolonToken;
    }

    public void setSemicolonToken(Token semicolonToken) {
        this.semicolonToken = semicolonToken;
    }

    @Override
    public FileRange getRange() {
        return new FileRange(
                expr.getRange().getStart(),
                semicolonToken.getRange().getEnd());
    }

    @Override
    public String toString() {
        return expr.toString() + semicolonToken.toString();
    }
}
