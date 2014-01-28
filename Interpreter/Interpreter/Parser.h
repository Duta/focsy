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

    AST * parse(std::vector<Token *>);
private:
    void updateCurrent();
    void advance();
    void push();
    void pop();
    void match(TokenType);
    void match(TokenType, std::string);
    bool isSpeculating() const;
    bool is(TokenType);
    bool is(TokenType, std::string);
    void matchOptionalWhitespace();
    bool isForLoop();
    bool isWhileLoop();
    bool isCompound();
    bool isStmt();
    bool isExpr();
    bool isBool();
    bool isInt();
    bool isFloat();
    ForAST * matchForLoop();
    WhileAST * matchWhileLoop();
    CompoundAST * matchCompound();
    StmtAST * matchStmt();
    ExprAST * matchExpr();
    BoolAST * matchBool();
    IntAST * matchInt();
    FloatAST * matchFloat();

    std::vector<Token *> tokens;
    std::stack<int> markStack;
    int index;
    int length;
    Token *current;
};
