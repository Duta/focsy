#include <iostream>
#include <sstream>
#include <fstream>
#include "Lexer.h"
#include "Token.h"

void pause() {
    system("PAUSE");
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
        std::cout << "  TYPE = " << toktype2str(token->type) << std::endl;
        std::cout << "  TEXT = '" << token->text << "'" << std::endl;
        std::cout << "  LINE = " << token->line << std::endl;
        std::cout << "  COL  = " << token->col << std::endl;
    }
    pause();
    return 0;
}