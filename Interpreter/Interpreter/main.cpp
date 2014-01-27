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
    case TokenType::L_PAREN:    return "L_PAREN";
    case TokenType::R_PAREN:    return "R_PAREN";
    case TokenType::L_SQ_BRACK: return "L_SQ_BRACK";
    case TokenType::R_SQ_BRACK: return "R_SQ_BRACK";
    case TokenType::L_CURLY:    return "L_CURLY";
    case TokenType::R_CURLY:    return "R_CURLY";
    case TokenType::COMMA:      return "COMMA";
    case TokenType::INT_NUM:    return "INT_NUM";
    case TokenType::FLOAT_NUM:  return "FLOAT_NUM";
    case TokenType::IDENT:      return "IDENT";
    case TokenType::WHITESPACE: return "WHITESPACE";
    default: return "<unknown>";
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