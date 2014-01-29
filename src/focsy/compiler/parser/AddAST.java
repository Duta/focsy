package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class AddAST extends ExprAST {
    private Token plusToken;
    private ExprAST left, right;

    public AddAST(ExprAST left, Token plusToken, ExprAST right) {
        this.left = left;
        this.plusToken = plusToken;
        this.right = right;
    }

    @Override
    public FileRange getRange() {
        return new FileRange(
                getLeft().getRange().getStart(),
                getRight().getRange().getEnd());
    }

    public ExprAST getLeft() {
        return left;
    }

    public void setLeft(ExprAST left) {
        this.left = left;
    }

    public ExprAST getRight() {
        return right;
    }

    public void setRight(ExprAST right) {
        this.right = right;
    }

    public Token getPlusToken() {
        return plusToken;
    }

    public void setPlusToken(Token plusToken) {
        this.plusToken = plusToken;
    }

    @Override
    public String toString() {
        return left.toString() + plusToken.toString() + right.toString();
    }
}
