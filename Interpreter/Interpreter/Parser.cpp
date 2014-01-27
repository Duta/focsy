#include <sstream>
#include "Parser.h"

Parser::Parser() {}

Parser::~Parser() {}

std::vector<AST *> Parser::parse(std::vector<Token *> _tokens) {
    tokens = _tokens;
    index = 0;
    length = tokens.size();
    while(!markStack.empty()) {
        markStack.pop();
    }
    updateCurrent();
    std::vector<AST *> asts;

    while(current != nullptr) {
        asts.push_back(parseAST());
    }

    return asts;
}

AST * Parser::parseAST() {
    if(isForLoop()) {
        return matchForLoop();
    }
    return nullptr;
}

void Parser::updateCurrent() {
    current = index < length ? tokens[index] : nullptr;
}

void Parser::advance() {
    index++;
    updateCurrent();
}

void Parser::push() {
    markStack.push(index);
}

void Parser::pop() {
    index = markStack.top();
    markStack.pop();
}

void Parser::match(TokenType type) {
    if(current == nullptr) {
        std::stringstream ss;
        ss << "Unexpected EOF, expected " << toktype2str(type);
        throw ss.str();
    }
    if(current->type != type) {
        std::stringstream ss;
        ss << "Unexpected " << toktype2str(current->type);
        ss << ", expected " << toktype2str(type);
        throw ss.str();
    }
    advance();
}

void Parser::match(TokenType type, std::string text) {
    if(current == nullptr) {
        std::stringstream ss;
        ss << "Unexpected EOF, expected '" << text << "'";
        throw ss.str();
    }
    if(current->type != type || current->text != text) {
        std::stringstream ss;
        ss << "Unexpected '" << current->text << "'";
        ss << ", expected '" << text << "'";
        throw ss.str();
    }
    advance();
}

bool Parser::isSpeculating() const {
    return !markStack.empty();
}

bool Parser::isForLoop() {
    bool success = true;
    push();
    try {
        matchForLoop();
    } catch(std::string ex) {
        success = false;
    }
    pop();
    return success;
}

ForAST * Parser::matchForLoop() {
    
    return nullptr;
}
