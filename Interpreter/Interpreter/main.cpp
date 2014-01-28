#include <iostream>
#include <sstream>
#include <fstream>
#include "Lexer.h"
#include "Parser.h"
#include "Token.h"

void pause() {
    system("PAUSE");
}

void printTokens(std::vector<Token *> tokens) {
    for(auto token : tokens) {
        if(token == nullptr) {
            std::cout << "NULL_TOKEN" << std::endl;
            continue;
        }
        std::cout << "TOKEN" << std::endl;
        std::cout << "  TYPE = " << toktype2str(token->type) << std::endl;
        std::cout << "  TEXT = '" << token->text << "'" << std::endl;
        std::cout << "  LINE = " << token->line << std::endl;
        std::cout << "  COL  = " << token->col << std::endl;
    }
}

void printBoolAST(BoolAST *);
void printCompoundAST(CompoundAST *);
void printExprAST(ExprAST *);
void printFloatAST(FloatAST *);
void printForAST(ForAST *);
void printIntAST(IntAST *);
void printStmtAST(StmtAST *);
void printWhileAST(WhileAST *);
void printAST(AST *);

void printBoolAST(BoolAST *ast) {
    std::cout << "Bool{value=" << ast->value << "}";
}

void printCompoundAST(CompoundAST *ast) {
    std::cout << "Compound{";
    for(auto stmt : ast->stmts) {
        printStmtAST(stmt);
        std::cout << ",";
    }
    std::cout << "}";
}

void printExprAST(ExprAST *ast) {
    std::cout << "Expr{}";
}

void printFloatAST(FloatAST *ast) {
    std::cout << "Float{value=" << ast->value << "}";
}

void printForAST(ForAST *ast) {
    std::cout << "For{init=" << ast->init << ",cond=" << ast->cond
        << ",inc=" << ast->inc << ",body=" << ast->body << "}";
}

void printIntAST(IntAST *ast) {
    std::cout << "Int{value=" << ast->value << "}";
}

void printStmtAST(StmtAST *ast) {
    switch(ast->type) {
    case ASTType::Bool:
        printBoolAST(dynamic_cast<BoolAST *>(ast));
        break;
    case ASTType::Float:
        printFloatAST(dynamic_cast<FloatAST *>(ast));
        break;
    case ASTType::Int:
        printIntAST(dynamic_cast<IntAST *>(ast));
        break;
    case ASTType::Compound:
        printCompoundAST(dynamic_cast<CompoundAST *>(ast));
        break;
    case ASTType::Expr:
        printExprAST(dynamic_cast<ExprAST *>(ast));
        break;
    case ASTType::For:
        printForAST(dynamic_cast<ForAST *>(ast));
        break;
    case ASTType::While:
        printWhileAST(dynamic_cast<WhileAST *>(ast));
        break;
    default: std::cout << "Unknown StmtAST";
    }
    std::cout << std::endl;
}

void printWhileAST(WhileAST *ast) {
    std::cout << "While{cond=" << ast->cond << ",body=" << ast->body << "}";
}

void printAST(AST *ast) {
    if(ast == nullptr) {
        std::cout << "No AST" << std::endl;
        return;
    }
    printStmtAST(dynamic_cast<StmtAST *>(ast));
    std::cout << std::endl;
}

int main(int argc, char *argv[]) {
    Lexer lexer;
    Parser parser;
    std::string fileName;
    if(argc <= 1) {
        std::cout << "Enter the file name: ";
        std::cin >> fileName;
    } else {
        fileName = argv[1];
    }
    std::ifstream file(fileName);
    std::stringstream ss;
    ss << file.rdbuf();
    std::vector<Token *> tokens;
    try {
        tokens = lexer.lex(ss.str());
    } catch(std::string ex) {
        std::cout << ex << std::endl;
        pause();
        return 1;
    } catch(char *ex) {
        std::cout << ex << std::endl;
        pause();
        return 1;
    }
     printTokens(tokens);
    AST *ast = nullptr;
    try {
        ast = parser.parse(tokens);
    } catch(std::string ex) {
        std::cout << ex << std::endl;
        pause();
        return 2;
    }
    printAST(ast);
    pause();
    return 0;
}