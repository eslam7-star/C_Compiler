package com.example.c_compiler;


public class Token{
    private TokenType type;

    private String token;

    private String Id_value;

    private String Id_type;


    public Token(TokenType type, String token) {
        this.type = type;
        this.token = token;
        this.Id_value = "";
        this.Id_type = "";
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return token;
    }

    public String getId_value() {
        return Id_value;
    }

    public void setId_value(String id_value) {
        Id_value = id_value;
    }

    public String getId_type() {
        return Id_type;
    }

    public void setId_type(String id_type) {
        Id_type = id_type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", token='" + token + '\'' +
                '}';
    }
}

