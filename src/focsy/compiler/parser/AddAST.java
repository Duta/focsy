package focsy.compiler.parser;

import focsy.compiler.Token;

/**
 * Created by Bertie on 28/01/14.
 */
public class AddAST extends ExprAST {
    private ExprAST left, right;

    public AddAST(Token plusToken, ExprAST left, ExprAST right) {
        super(plusToken);
        this.left = left;
        this.right = right;
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
}
