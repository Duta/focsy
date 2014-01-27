#pragma once

#include <stack>
#include <vector>
#include "AST.h"
#include "Token.h"

class Parser
{
public:
    Parser();
    ~Parser();

    std::vector<AST *> parse(std::vector<Token *>);
private:
    AST * parseAST();
    void updateCurrent();
    void advance();
    void push();
    void pop();
    void match(TokenType);
    void match(TokenType, std::string);
    bool isSpeculating() const;
    bool isForLoop();
    ForAST * matchForLoop();

    std::vector<Token *> tokens;
    std::stack<int> markStack;
    int index;
    int length;
    Token *current;
};
