#include <sstream>
#include <iostream>
#include "Parser.h"

Parser::Parser() {}

Parser::~Parser() {}

AST * Parser::parse(std::vector<Token *> _tokens) {
    tokens = _tokens;
    index = 0;
    length = tokens.size();
    while(!markStack.empty()) {
        markStack.pop();
    }
    updateCurrent();
    AST *ast = nullptr;
    if(isStmt()) {
        ast = matchStmt();
    }
    return ast;
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

bool Parser::is(TokenType type) {
    return current != nullptr
        && current->type == type;
}

bool Parser::is(TokenType type, std::string text) {
    return current != nullptr
        && current->type == type
        && current->text == text;
}

void Parser::matchOptionalWhitespace() {
    if(is(TokenType::WHITESPACE)) {
        advance();
    }
}

#define PARSER_IS(type) bool Parser::is##type() {     \
                            bool success = true;      \
                            push();                   \
                            try {                     \
                                match##type();        \
                            } catch(std::string ex) { \
                                success = false;      \
                            } catch(char *ex) {       \
                                success = false;      \
                            }                         \
                            pop();                    \
                            return success;           \
                        }

PARSER_IS(ForLoop)
PARSER_IS(WhileLoop)
PARSER_IS(Compound)
PARSER_IS(Stmt)
PARSER_IS(Expr)
PARSER_IS(Bool)
PARSER_IS(Int)
PARSER_IS(Float)

#undef PARSER_IS

ForAST * Parser::matchForLoop() {
    // TODO
    throw "For loops not implemented";
}

WhileAST * Parser::matchWhileLoop() {
    match(TokenType::IDENT, "while");
    matchOptionalWhitespace();
    match(TokenType::L_PAREN);
    matchOptionalWhitespace();
    ExprAST *cond = matchExpr();
    matchOptionalWhitespace();
    match(TokenType::R_PAREN);
    matchOptionalWhitespace();
    StmtAST *body = matchStmt();
    if(isSpeculating()) {
        return nullptr;
    } else {
        WhileAST *ast = new WhileAST;
        ast->type = ASTType::While;
        ast->cond = cond;
        ast->body = body;
        return ast;
    }
}

CompoundAST * Parser::matchCompound() {
    CompoundAST *ast = nullptr;
    if(!isSpeculating()) {
        ast = new CompoundAST;
        ast->type = ASTType::Compound;
    }
    match(TokenType::L_CURLY);
    matchOptionalWhitespace();
    while(isStmt()) {
        StmtAST *stmt = matchStmt();
        if(!isSpeculating()) {
            ast->stmts.push_back(stmt);
        }
        matchOptionalWhitespace();
    }
    matchOptionalWhitespace();
    match(TokenType::R_CURLY);
    return ast;
}

StmtAST * Parser::matchStmt() {
    if(isWhileLoop()) {
        return matchWhileLoop();
    }
    if(isForLoop()) {
        return matchForLoop();
    }
    if(isExpr()) {
        ExprAST *expr = matchExpr();
        matchOptionalWhitespace();
        match(TokenType::SEMICOLON);
    }
    if(isCompound()) {
        return matchCompound();
    }
    throw "Couldn't match statement";
}

ExprAST * Parser::matchExpr() {
    if(isBool()) {
        return matchBool();
    }
    if(isInt()) {
        return matchInt();
    }
    if(isFloat()) {
        return matchFloat();
    }
    throw "Couldn't match expression";
}

BoolAST * Parser::matchBool() {
    if(is(TokenType::IDENT)) {
        std::string text = current->text;
        if(text == "true"
        || text == "false") {
            advance();
            if(isSpeculating()) {
                return nullptr;
            } else {
                BoolAST *ast = new BoolAST;
                ast->type = ASTType::Bool;
                ast->value = text == "true";
                return ast;
            }
        }
    }
    throw "Couldn't match bool";
}

IntAST * Parser::matchInt() {
    // TODO
    throw "Ints not implemented";
}

FloatAST * Parser::matchFloat() {
    // TODO
    throw "Floats not implemented";
}