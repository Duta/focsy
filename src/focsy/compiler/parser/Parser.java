package focsy.compiler.parser;

import focsy.compiler.FileRange;
import focsy.compiler.Token;
import focsy.compiler.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Bertie on 28/01/14.
 */
public class Parser {
    private final BasicParser
            addParser = new AddParser(),
            compoundStmtParser = new CompoundStmtParser(),
            exprParser = new ExprParser(),
            intParser = new IntParser(),
            mainParser = new MainParser(),
            simpleStmtParser = new SimpleStmtParser(),
            stmtParser = new StmtParser();
    private List<Token> tokens;
    private Stack<Integer> indices;
    private int index;

    public Parser() {
        indices = new Stack<Integer>();
    }

    public List<AST> parse(List<Token> tokens) {
        if(tokens == null || tokens.isEmpty()) {
            return null;
        }
        this.tokens = tokens;
        index = 0;
        indices.clear();
        List<AST> asts = new ArrayList<AST>();

        while(index < tokens.size()) {
            asts.add(mainParser.match());
        }

        return asts;
    }

    private boolean isSpeculating() {
        return !indices.isEmpty();
    }

    private void pushIndex() {
        indices.push(index);
    }
    
    private void popIndex() {
        index = indices.pop();
    }

    private Token current() {
        return lookahead(0);
    }

    private Token lookahead(int i) {
        try {
            return tokens.get(index + i);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }

    private Token matchToken(TokenType type) {
        Token current = current();
        if(current != null && current.getType() == type) {
            index++;
            return current;
        }
        throw makeUnexpectedException(type);
    }

    private void skipWhitespace() {
        Token current = current();
        if(current != null && current.getType() == TokenType.WHITESPACE) {
            index++;
        }
    }

    private ParseException makeUnexpectedException() {
        Token current = current();
        String unexpected = current == null ? "null" : current.getText();
        return makeException(
                "Unexpected '" + unexpected + "'");
    }

    private ParseException makeUnexpectedException(TokenType expectedType) {
        Token current = current();
        String unexpected = current == null ? "null" : current.getText();
        return makeException(
                "Unexpected '" + unexpected + "', " +
                "was expecting " + expectedType);
    }

    private ParseException makeMatchFailureException(String attempted) {
        return makeException(
                "Failed to match " + attempted);
    }

    private ParseException makeException(String message) {
        Token current = current();
        FileRange range = current == null ? null : current.getRange();
        return new ParseException(message, range);
    }

    private InternalParseException makeInternalException(String message) {
        Token current = current();
        FileRange range = current == null ? null : current.getRange();
        return new InternalParseException(message, range);
    }

    public abstract class BasicParser {
        private BasicParser() {}

        public boolean matches() {
            boolean success = true;
            pushIndex();
            try {
                match();
            } catch(ParseException ex) {
                success = false;
            }
            popIndex();
            return success;
        }

        public abstract AST match();
    }

    public class AddParser extends BasicParser {
        @Override
        public AST match() {
            skipWhitespace();
            ExprAST left = (ExprAST) intParser.match();
            skipWhitespace();
            Token token = matchToken(TokenType.PLUS);
            skipWhitespace();
            ExprAST right = (ExprAST) exprParser.match();
            skipWhitespace();
            return new AddAST(left, token, right);
        }
    }

    public class CompoundStmtParser extends BasicParser {
        @Override
        public AST match() {
            skipWhitespace();
            Token openBrace = matchToken(TokenType.L_CURLY);
            List<StmtAST> stmts = new ArrayList<StmtAST>();
            while(stmtParser.matches()) {
                skipWhitespace();
                stmts.add((StmtAST) stmtParser.match());
            }
            skipWhitespace();
            Token closeBrace = matchToken(TokenType.R_CURLY);
            skipWhitespace();
            return new CompoundStmtAST(openBrace, stmts, closeBrace);
        }
    }

    public class ExprParser extends BasicParser {
        @Override
        public AST match() {
            if(addParser.matches()) {
                return addParser.match();
            }
            if(intParser.matches()) {
                return intParser.match();
            }
            throw makeMatchFailureException("an expression");
        }
    }

    public class IntParser extends BasicParser {
        @Override
        public AST match() {
            skipWhitespace();
            Token intToken = matchToken(TokenType.INT_NUM);
            skipWhitespace();
            return new IntAST(intToken);
        }
    }

    public class MainParser extends BasicParser {
        @Override
        public AST match() {
            skipWhitespace();
            StmtAST stmt = (StmtAST) stmtParser.match();
            skipWhitespace();
            return stmt;
        }
    }

    public class SimpleStmtParser extends BasicParser {
        @Override
        public AST match() {
            skipWhitespace();
            ExprAST expr = (ExprAST) exprParser.match();
            skipWhitespace();
            Token semicolonToken = matchToken(TokenType.SEMICOLON);
            skipWhitespace();
            return new SimpleStmtAST(expr, semicolonToken);
        }
    }

    public class StmtParser extends BasicParser {
        @Override
        public AST match() {
            if(compoundStmtParser.matches()) {
                return compoundStmtParser.match();
            }
            if(simpleStmtParser.matches()) {
                return simpleStmtParser.match();
            }
            throw makeMatchFailureException("a statement");
        }
    }
}
