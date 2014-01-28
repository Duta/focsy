package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;

import java.util.List;

/**
 * Created by Bertie on 28/01/14.
 */
public class CompoundStmtAST extends StmtAST {
    private Token openBrace, closeBrace;
    private List<StmtAST> stmts;

    public CompoundStmtAST(Token openBrace, List<StmtAST> stmts, Token closeBrace) {
        this.openBrace = openBrace;
        this.stmts = stmts;
        this.closeBrace = closeBrace;
    }

    @Override
    public FileRange getRange() {
        return new FileRange(
                openBrace.getRange().getStart(),
                closeBrace.getRange().getEnd());
    }

    public Token getOpenBrace() {
        return openBrace;
    }

    public void setOpenBrace(Token openBrace) {
        this.openBrace = openBrace;
    }

    public List<StmtAST> getStmts() {
        return stmts;
    }

    public void setStmts(List<StmtAST> stmts) {
        this.stmts = stmts;
    }

    public Token getCloseBrace() {
        return closeBrace;
    }

    public void setCloseBrace(Token closeBrace) {
        this.closeBrace = closeBrace;
    }
}
