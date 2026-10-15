package com.craftinginterpreters.lox;

enum TokenType {
  // Single-character tokens.
  LEFT_PAREN, RIGHT_PAREN,
  LEFT_BRACE, RIGHT_BRACE,
  COMMA, DOT, MINUS, PLUS,
  SEMICOLON, SLASH, STAR,
  QUESTION, COLON,

  // One or two character tokens.
  BANG, BANG_EQUAL,
  EQUAL, EQUAL_EQUAL,
  GREATER, GREATER_EQUAL,
  LESS, LESS_EQUAL,
  PLUS_PLUS, MINUS_MINUS,

  // Literals.
  IDENTIFIER, STRING, NUMBER,

  // Keywords.
  AND, OR,
  CLASS, FUN, VAR,
  IF, ELSE,
  TRUE, FALSE,
  NIL,
  PRINT,
  RETURN,
  SUPER, THIS,
  WHILE,
  FOR,
  TRAIT, WITH,

  EOF
}