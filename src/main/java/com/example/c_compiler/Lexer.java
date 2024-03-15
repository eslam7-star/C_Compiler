package com.example.c_compiler;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int currentPosition;
    private static final String[] KEYWORDS = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile","while"};

    List<Token> tokens;

    public Lexer(String input) {
        this.input=input;
        this.currentPosition = 0;
        tokens = new ArrayList<>();
    }


    public void tokenize() {
        StringBuilder buffer = new StringBuilder();
        String op;
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);
            if(Character.toString(currentChar).matches("[+\\-*/%&|<>!=^~?:(),.;\\[\\]{}\\\\#\\s]")) {
                if(buffer.length() > 0) {
                    addToken(buffer);
                }
                if( input.charAt(currentPosition) == '/' )
                    remove_comments();
                op = Character.toString(currentChar);
                if(op.matches("[-+*/%&|<>^!~=]")) {
                    currentPosition++;
                    if(currentPosition < input.length() && Character.toString(input.charAt(currentPosition)).matches("[-+*/%&|<>^!~=]")) {
                        op += Character.toString(input.charAt(currentPosition));
                    } else {
                        currentPosition--;
                    }
                    tokens.add(new Token(TokenType.OPERATOR,op));
                }else if ( !op.equals("\s") && !op.equals("\n") ){
                    tokens.add(new Token(TokenType.SYMBOL,op));
                }

                op = "";
                buffer.delete(0, buffer.length());
            }else{
                buffer.append(input.charAt(currentPosition));
            }
            currentPosition++;
        }
        if(buffer.length() > 0) {
            addToken(buffer);
        }
    }

    public void addToken(StringBuilder buffer) {
        String sbuffer = buffer.toString();
        if(is_keyword(sbuffer)) {
            tokens.add(new Token(TokenType.KEYWORD, sbuffer));
        } else if(sbuffer.matches("[a-zA-Z_$][a-zA-Z0-9_$]*")) {
            tokens.add(new Token(TokenType.IDENTIFIER, sbuffer));
        } else if(sbuffer.matches("\\\".*?\\\"") || sbuffer.matches("'(\\\\.|[^'\\\\])*'|0[xX][0-9a-fA-F]+|0[0-7]*|0[bB][01]+|[1-9][0-9]*|0") || sbuffer.matches("[-+]?[0-9]*[.]?[0-9]+([eE][-+]?[0-9]+)?")) {
            tokens.add(new Token(TokenType.LITERAL, sbuffer));
        }
        buffer.delete(0, buffer.length());
    }


    public boolean is_keyword(String str){
        for(String s: KEYWORDS ){
            if ( s.equals(str) ){
                return true;
            }
        }
        return false;
     }
    

     public void remove_comments(){
        if( input.charAt(currentPosition + 1 ) == '/'  ){
            while (input.charAt(currentPosition) != '\n' ){
                currentPosition++;
            }
        }else if( input.charAt(currentPosition + 1 ) == '*' ){
            while ( ! ( input.charAt(currentPosition) != '*' && input.charAt(currentPosition+1) != '/') ){
                currentPosition++;
            }
         }
     }




}
