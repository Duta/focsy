#include "Token.h"

std::string toktype2str(TokenType type) {
    switch(type) {
    case TokenType::L_PAREN:      return "L_PAREN";
    case TokenType::R_PAREN:      return "R_PAREN";
    case TokenType::L_SQ_BRACK:   return "L_SQ_BRACK";
    case TokenType::R_SQ_BRACK:   return "R_SQ_BRACK";
    case TokenType::L_CURLY:      return "L_CURLY";
    case TokenType::R_CURLY:      return "R_CURLY";
    case TokenType::COMMA:        return "COMMA";
    case TokenType::SEMICOLON:    return "SEMICOLON";
    case TokenType::LESS_THAN:    return "LESS_THAN";
    case TokenType::GREATER_THAN: return "GREATER_THAN";
    case TokenType::EQUALS:       return "EQUALS";
    case TokenType::INT_NUM:      return "INT_NUM";
    case TokenType::FLOAT_NUM:    return "FLOAT_NUM";
    case TokenType::IDENT:        return "IDENT";
    case TokenType::WHITESPACE:   return "WHITESPACE";
    default: return "<unknown>";
    }
}