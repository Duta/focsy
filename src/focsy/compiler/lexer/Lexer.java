package focsy.compiler.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bertie on 28/01/14.
 */
public class Lexer {
    private static final int
        INITIAL_LINE = 1,
        INITIAL_COL = 1;
    private String input;
    private int index;
    private int line, col;
    private char current;

    public List<Token> lex(String input) {
        if(input == null || input.isEmpty()) {
            return new ArrayList<Token>();
        }
        this.input = input;
        this.index = 0;
        this.line = INITIAL_LINE;
        this.col = INITIAL_COL;
        updateCurrent();

        List<Token> tokens = new ArrayList<Token>();
        while(index < input.length()) {
            tokens.add(lexToken());
        }
        return tokens;
    }

    private Token lexToken() {
        // Match a basic token (e.g. '(', ';'...)
        Token basic = matchBasic();
        if(basic != null) {
            advance();
            return basic;
        }

        // Match a number (either integer or floating point)
        if(Character.isDigit(current)) {
            return matchNumber();
        }

        // Match an identifier
        if(Character.isAlphabetic(current)) {
            return matchIdentifier();
        }

        // Match whitespace
        if(Character.isWhitespace(current)) {
            return matchWhitespace();
        }

        // Unrecognized token
        throw makeException(
                "Unrecognized symbol: '" + current + "'");
    }

    private Token makeToken(TokenType type) {
        switch(type) {
        case L_PAREN:
            return makeToken(type, "(");
        case R_PAREN:
            return makeToken(type, ")");
        case L_SQ_BRACK:
            return makeToken(type, "[");
        case R_SQ_BRACK:
            return makeToken(type, "]");
        case L_CURLY:
            return makeToken(type, "{");
        case R_CURLY:
            return makeToken(type, "}");
        case COMMA:
            return makeToken(type, ",");
        case SEMICOLON:
            return makeToken(type, ";");
        case LESS_THAN:
            return makeToken(type, "<");
        case GREATER_THAN:
            return makeToken(type, ">");
        case EQUALS:
            return makeToken(type, "=");
        default:
            throw makeInternalException(
                    "Invalid token type in makeToken(type): " + type);
        }
    }

    private Token makeToken(TokenType type, String text) {
        return new Token(type, text, line, col);
    }

    private LexException makeException(String message) {
        return new LexException(message, line, col);
    }

    private InternalLexException makeInternalException(String message) {
        return new InternalLexException(message, line, col);
    }

    private void advance() {
        if(current == '\n') {
            line++;
            col = INITIAL_COL;
        } else {
            col++;
        }
        index++;
        updateCurrent();
    }

    private void updateCurrent() {
        current = input.charAt(index);
    }

    private Token matchBasic() {
        switch(current) {
            case '(':
                return makeToken(TokenType.L_PAREN);
            case ')':
                return makeToken(TokenType.R_PAREN);
            case '[':
                return makeToken(TokenType.L_SQ_BRACK);
            case ']':
                return makeToken(TokenType.R_SQ_BRACK);
            case '{':
                return makeToken(TokenType.L_CURLY);
            case '}':
                return makeToken(TokenType.R_CURLY);
            case ',':
                return makeToken(TokenType.COMMA);
            case ';':
                return makeToken(TokenType.SEMICOLON);
            case '<':
                return makeToken(TokenType.LESS_THAN);
            case '>':
                return makeToken(TokenType.GREATER_THAN);
            case '=':
                return makeToken(TokenType.EQUALS);
            default:
                return null;
        }
    }

    private Token matchNumber() {
        StringBuilder sb = new StringBuilder();
        while(Character.isDigit(current)) {
            sb.append(current);
            advance();
        }
        if(current == '.') {
            do {
                sb.append(current);
                advance();
            } while(Character.isDigit(current));
            return makeToken(TokenType.FLOAT_NUM, sb.toString());
        } else {
            return makeToken(TokenType.INT_NUM, sb.toString());
        }
    }

    private Token matchIdentifier() {
        // TODO
        throw makeInternalException(
                "Unimplemented method: matchIdentifier");
    }

    private Token matchWhitespace() {
        // TODO
        throw makeInternalException(
                "Unimplemented method: matchWhitespace");
    }
}
