#pragma once

struct StmtAST;
 struct CompoundAST;
 struct ExprAST;
  struct BoolAST;
  struct IntAST;
  struct FloatAST;
 struct ForAST;
 struct WhileAST;

enum class ASTType {
    Stmt,
    Compound,
    Expr,
    Bool,
    Int,
    Float,
    For,
    While,
};

struct AST {
    ASTType type;
    union {
        StmtAST *stmtAst;
        CompoundAST *compoundAst;
        ExprAST *exprAst;
        BoolAST *boolAst;
        IntAST *intAst;
        FloatAST *floatAst;
        ForAST *forAst;
        WhileAST *whileAst;
    };
};

struct StmtAST : AST {};
struct CompoundAST : StmtAST {
    std::vector<StmtAST *> stmts;
};
struct ExprAST : StmtAST {};
struct BoolAST : ExprAST {
    bool value;
};
struct IntAST : ExprAST {
    int value;
};
struct FloatAST : ExprAST {
    double value;
};
struct ForAST : StmtAST {
    StmtAST *init;
    ExprAST *cond;
    ExprAST *inc;
    StmtAST *body;
};
struct WhileAST : StmtAST {
    ExprAST *cond;
    StmtAST *body;
};