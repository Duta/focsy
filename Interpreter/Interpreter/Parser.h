#pragma once

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

    std::vector<Token *> tokens;
    int index;
    int length;
    int line;
    int col;
    Token *current;
};
