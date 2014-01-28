package focsy.compiler.parser;

import focsy.compiler.Token;

import java.util.List;
import java.util.Stack;

/**
 * Created by Bertie on 28/01/14.
 */
public class Parser {
    private final BasicParser
            addParser = new AddParser(),
            compoundParser = new CompoundParser(),
            exprParser = new ExprParser(),
            intParser = new IntParser(),
            stmtParser = new StmtParser();
    private List<Token> tokens;
    private Stack<Integer> indices;
    private int index;

    public Parser() {
        indices = new Stack<Integer>();
    }

    public AST parse(List<Token> tokens) {
        if(tokens == null || tokens.isEmpty()) {
            return null;
        }
        this.tokens = tokens;
        index = 0;
        indices.clear();

        if(compoundParser.matches()) {
            return compoundParser.match();
        }

        throw makeException("Complete parse failure");
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
        return tokens.get(index + i);
    }

    private ParseException makeException(String message) {
        return new ParseException(message, current().getLoc());
    }

    private InternalParseException makeInternalException(String message) {
        return new InternalParseException(message, current().getLoc());
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
            throw makeInternalException(
                    "Unimplemented: AddParser::match()");
        }
    }

    public class CompoundParser extends BasicParser {
        @Override
        public AST match() {
            throw makeInternalException(
                    "Unimplemented: CompoundParser::match()");
        }
    }

    public class ExprParser extends BasicParser {
        @Override
        public AST match() {
            throw makeInternalException(
                    "Unimplemented: ExprParser::match()");
        }
    }

    public class IntParser extends BasicParser {
        @Override
        public AST match() {
            throw makeInternalException(
                    "Unimplemented: IntParser::match()");
        }
    }

    public class StmtParser extends BasicParser {
        @Override
        public AST match() {
            throw makeInternalException(
                    "Unimplemented: StmtParser::match()");
        }
    }
}
