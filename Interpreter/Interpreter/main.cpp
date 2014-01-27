#include <iostream>
#include <sstream>
#include <fstream>
#include "Lexer.h"
#include "Token.h"

void pause() {
    system("PAUSE");
}

std::string toktype2str(Token *token) {
    switch(token->type) {
    case TokenType::L_PAREN:
        std::cout << "L_PAREN";
        break;
    case TokenType::R_PAREN:
        std::cout << "R_PAREN";
        break;
    case TokenType::L_SQ_BRACK:
        std::cout << "L_SQ_BRACK";
        break;
    case TokenType::R_SQ_BRACK:
        std::cout << "R_SQ_BRACK";
        break;
    case TokenType::L_CURLY:
        std::cout << "L_CURLY";
        break;
    case TokenType::R_CURLY:
        std::cout << "R_CURLY";
        break;
    case TokenType::COMMA:
        std::cout << "COMMA";
        break;
    case TokenType::INT_NUM:
        std::cout << "INT_NUM";
        break;
    case TokenType::FLOAT_NUM:
        std::cout << "FLOAT_NUM";
        break;
    case TokenType::IDENT:
        std::cout << "IDENT";
        break;
    case TokenType::WHITESPACE:
        std::cout << "WHITESPACE";
        break;
    default:
        std::cout << "<unknown>";
    }
}

int main(int argc, char *argv[]) {
    Lexer lexer;
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
    }
    for(auto token : tokens) {
        if(token == nullptr) {
            std::cout << "NULL_TOKEN" << std::endl;
            continue;
        }
        std::cout << "TOKEN" << std::endl;
        std::cout << "  TYPE = " << toktype2str(token) << std::endl;
        std::cout << "  TEXT = '" << token->text << "'" << std::endl;
    }
    pause();
    return 0;
}