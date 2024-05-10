package com.example.c_compiler;

public enum TokenType {
        KEYWORD,
        IDENTIFIER,
        DECIMAL,
        OCTAL,
        BINARY,
        HEX,
        FLOAT,
        STRING,
        Character,
        SYMBOL,

        // Arithmetic Operators
        ADD, SUB, MUL, DIV, MOD, INC, DEC,
        // Assignment Operators
        ASSIGN, ADD_ASSIGN, SUB_ASSIGN, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN,
        // Relational Operators
        LT, GT, LE, GE, EQ, NE,
        // Logical Operators
        AND, OR, NOT,
        // Bitwise Operators
        BIT_AND, BIT_OR, BIT_XOR, BIT_NOT, LEFT_SHIFT, RIGHT_SHIFT, UnknownOP, PROGRAM

}

