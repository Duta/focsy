#include "Parser.h"

Parser::Parser() {}

Parser::~Parser() {}

std::vector<AST *> Parser::parse(std::vector<Token *> _tokens) {
    tokens = _tokens;
    index = 0;
    line = 1;
    col = 1;
    length = tokens.size();
    updateCurrent();
    std::vector<AST *> asts;

    while(current != nullptr) {
        asts.push_back(parseAST());
    }

    return asts;
}

AST * Parser::parseAST() {
    return nullptr;
}

void Parser::updateCurrent() {
    current = index < length ? tokens[index] : nullptr;
}

void Parser::advance() {
    if(current->type == TokenType::WHITESPACE) {
        for(auto ch : current->text) {
            if(ch == '\n') {
                line++;
                col = 1;
            } else {
                col++;
            }
        }
    } else {
        col += current->text.length();
    }
    index++;
    updateCurrent();
}
