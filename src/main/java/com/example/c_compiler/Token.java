package com.example.c_compiler;


public class Token{
    private TokenType type;

    private String token;

    public Token(TokenType type, String token) {
        this.type = type;
        this.token = token;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + token + '\'' +
                '}';
    }
}
