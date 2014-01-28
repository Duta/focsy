package focsy.compiler.lexer;

import focsy.compiler.FileLocation;
import focsy.compiler.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bertie on 28/01/14.
 */
public class Lexer {
    private String input;
    private int index;
    private char current;
    private FileLocation loc, startLoc;

    public List<Token> lex(String input) {
        if(input == null || input.isEmpty()) {
            return null;
        }
        this.input = input;
        index = 0;
        loc = new FileLocation();
        updateCurrent();

        List<Token> tokens = new ArrayList<Token>();
        while(index < input.length()) {
            tokens.add(lexToken());
        }
        return tokens;
    }

    private Token lexToken() {
        // Mark the start position of the
        // token (for use in markToken())
        markTokenStart();

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
        if(Character.isJavaIdentifierStart(current)) {
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

    private void markTokenStart() {
        startLoc = loc;
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
            case PLUS:
                return makeToken(type, "+");
            case MINUS:
                return makeToken(type, "-");
            case ASTERISK:
                return makeToken(type, "*");
            case FWD_SLASH:
                return makeToken(type, "/");
            case BACK_SLASH:
                return makeToken(type, "\\");
            default:
                throw makeInternalException(
                        "Invalid token type in makeToken(type): " + type);
        }
    }

    private Token makeToken(TokenType type, String text) {
        return new Token(type, text, startLoc);
    }

    private LexException makeException(String message) {
        return new LexException(message, loc);
    }

    private InternalLexException makeInternalException(String message) {
        return new InternalLexException(message, loc);
    }

    private void advance() {
        loc = current == '\n' ? loc.nextLine() : loc.nextCol();
        index++;
        updateCurrent();
    }

    private void updateCurrent() {
        if(index < input.length()) {
            current = input.charAt(index);
        } else {
            current = (char)-1;
        }
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
            case '+':
                return makeToken(TokenType.PLUS);
            case '-':
                return makeToken(TokenType.MINUS);
            case '*':
                return makeToken(TokenType.ASTERISK);
            case '/':
                return makeToken(TokenType.FWD_SLASH);
            case '\\':
                return makeToken(TokenType.BACK_SLASH);
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
            if(sb.length() == 1) {
                throw makeInternalException(
                        "Match failure in matchNumber()");
            }
            return makeToken(TokenType.FLOAT_NUM, sb.toString());
        } else {
            if(sb.length() == 0) {
                throw makeInternalException(
                        "Match failure in matchNumber()");
            }
            return makeToken(TokenType.INT_NUM, sb.toString());
        }
    }

    private Token matchIdentifier() {
        if(!Character.isJavaIdentifierStart(current)) {
            throw makeInternalException(
                    "Match failure in matchIdentifier()");
        }
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(current);
            advance();
        } while(Character.isJavaIdentifierPart(current));
        return makeToken(TokenType.IDENT, sb.toString());
    }

    private Token matchWhitespace() {
        StringBuilder sb = new StringBuilder();
        while(Character.isWhitespace(current)) {
            sb.append(current);
            advance();
        }
        if(sb.length() == 0) {
            throw makeInternalException(
                    "Match failure in matchWhitespace()");
        }
        return makeToken(TokenType.WHITESPACE, sb.toString());
    }
}
