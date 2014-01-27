#pragma once

#include <string>

enum class TokenType {
    L_PAREN,
    R_PAREN,
    L_SQ_BRACK,
    R_SQ_BRACK,
    L_CURLY,
    R_CURLY,
    COMMA,
    INT_NUM,
    FLOAT_NUM,
    IDENT,
    WHITESPACE
};

struct Token {
    TokenType type;
    std::string text;
};