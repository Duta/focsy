#pragma once

#include <vector>
#include <string>
#include "Token.h"

class Lexer {
public:
    Lexer();
    ~Lexer();

    std::vector<Token *> lex(std::string);
private:
    Token * lexToken();
    void matchNumber(Token *);
    void matchIdentifier(Token *);
    void matchWhitespace(Token *);
    void updateCurrent();
    void advance();
    bool isDigit() const;
    bool isLetter() const;
    bool isAlphanumeric() const;
    bool isWhitespace() const;

    char current;
    int index;
    int line;
    int col;
    int length;
    std::string input;

    static const char EOF_CHAR;
};

