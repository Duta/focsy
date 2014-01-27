#include <sstream>
#include <ctype.h>
#include "Lexer.h"

const char Lexer::EOF_CHAR = -1;

Lexer::Lexer() {}

Lexer::~Lexer() {}

std::vector<Token *> Lexer::lex(std::string _input) {
    input = _input;
    index = 0;
    line = 1;
    col = 1;
    length = input.length();
    updateCurrent();
    tokens.clear();

    while(current != EOF_CHAR) {
        tokens.push_back(lexToken());
    }

    return tokens;
}

Token * Lexer::lexToken() {
    Token *token = new Token;
    bool matched = true;
    switch(current) {
    case '(':
        token->type = TokenType::L_PAREN;
        token->text = "(";
        break;
    case ')':
        token->type = TokenType::R_PAREN;
        token->text = ")";
        break;
    case '[':
        token->type = TokenType::L_SQ_BRACK;
        token->text = "[";
        break;
    case ']':
        token->type = TokenType::R_SQ_BRACK;
        token->text = "]";
        break;
    case '{':
        token->type = TokenType::L_CURLY;
        token->text = "{";
        break;
    case '}':
        token->type = TokenType::R_CURLY;
        token->text = "}";
        break;
    case ',':
        token->type = TokenType::COMMA;
        token->text = ",";
        break;
    default:
        matched = false;
    }
    if(matched) {
        advance();
        return token;
    }
    if(isDigit()) {
        matchNumber(token);
        return token;
    }
    if(isLetter()) {
        matchIdentifier(token);
        return token;
    }
    if(isWhitespace()) {
        matchWhitespace(token);
        return token;
    }
    delete token;
    //advance();
    //return nullptr;
    std::stringstream ss;
    ss << "LEX ERROR:" << std::endl;
    ss << "    Unexpected symbol: " << current << std::endl;
    ss << "    Line: " << line << std::endl;
    ss << "    Column: " << col << std::endl;
    throw ss.str();
}

void Lexer::matchNumber(Token *token) {
    token->type = TokenType::INT_NUM;
    token->text = "";
    while(isDigit()) {
        token->text += current;
        advance();
    }
    if(current == '.') {
        token->type = TokenType::FLOAT_NUM;
        token->text += '.';
        advance();
        while(isDigit()) {
            token->text += current;
            advance();
        }
    }
}

void Lexer::matchIdentifier(Token *token) {
    token->type = TokenType::IDENT;
    token->text = "";
    while(isAlphanumeric()) {
        token->text += current;
        advance();
    }
}

void Lexer::matchWhitespace(Token *token) {
    token->type = TokenType::WHITESPACE;
    token->text = "";
    while(isWhitespace()) {
        token->text += current;
        advance();
    }
}

void Lexer::updateCurrent() {
    current = index < length ? input[index] : EOF_CHAR;
}

void Lexer::advance() {
    if(current == '\n') {
        line++;
        col = 1;
    } else {
        col++;
    }
    index++;
    updateCurrent();
}

bool Lexer::isDigit() {
    return isdigit(current);
}

bool Lexer::isLetter() {
    return isalpha(current);
}

bool Lexer::isAlphanumeric() {
    return isalnum(current);
}

bool Lexer::isWhitespace() {
    return isspace(current);
}
