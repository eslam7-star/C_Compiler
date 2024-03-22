package com.example.c_compiler;
import java.util.ArrayList;
import java.util.List;


public class Lexer {
    private String input;
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
        input = removeComments();
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);
            if(Character.toString(currentChar).matches("[+\\-*/%&|<>!=^~?:(),;\\[\\]{}#\\s]") ) {
                if(buffer.length() > 0) {
                    addToken(buffer);
                }
                op = Character.toString(currentChar);
                if(op.matches("[-+*/%&|<>^!~=]")) {
                    currentPosition++;      
                    if(currentPosition < input.length() && Character.toString(input.charAt(currentPosition)).matches("[-+*/%&|<>^!~=]")) {
                        op += Character.toString(input.charAt(currentPosition));
                    } else {
                        currentPosition--;
                    }
                    tokens.add(new Token( recognizeOperator(op) ,op));
                }else if ( !op.equals("\s") && !op.equals("\n") ){
                    tokens.add(new Token(TokenType.SYMBOL,op));
                }
                op = "";
                buffer.delete(0, buffer.length());
            }else{
                StringBuilder b = new StringBuilder();
                if ( input.charAt(currentPosition) == '"' ){
                    currentPosition++;
                    while ( input.charAt(currentPosition) != '"' ){
                        b.append(input.charAt(currentPosition));
                        currentPosition++;
                    }
                    tokens.add(new Token(TokenType.STRING,b.toString()));
                }else {
                    buffer.append(input.charAt(currentPosition));
                }
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
        } else if(sbuffer.matches("\\\".*?\\\"")) {
            tokens.add(new Token(TokenType.STRING, sbuffer));
        }else if( sbuffer.matches("[1-9][0-9]*")){
            tokens.add(new Token(TokenType.DECIMAL,sbuffer));
        }else if( sbuffer.matches("0[bB][01]+")){
            tokens.add(new Token(TokenType.BINARY,sbuffer));
        }else if( sbuffer.matches("0[xX][0-9a-fA-F]+")){
            tokens.add(new Token(TokenType.HEX,sbuffer));
        }else if( sbuffer.matches("0[0-7]*")){
            tokens.add(new Token(TokenType.OCTAL,sbuffer));
        }else if( sbuffer.matches("'(\\\\.|[^'\\\\])*'")){
            tokens.add(new Token(TokenType.Character,sbuffer));
        }else if( sbuffer.matches("[-+]?[0-9]*[.]?[0-9]+([eE][-+]?[0-9]+)?") ){
            tokens.add(new Token(TokenType.FLOAT,sbuffer));
        }else{
            System.out.println("Syntax Error here :"+sbuffer);
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


    public TokenType recognizeOperator(String operator) {
        // Mapping operator symbols to their types
        switch (operator) {
            case "+":
                return TokenType.ADD;
            case "-":
                return TokenType.SUB;
            case "*":
                return TokenType.MUL;
            case "/":
                return TokenType.DIV;
            case "%":
                return TokenType.MOD;
            case "&":
                return TokenType.BIT_AND;
            case "|":
                return TokenType.BIT_OR;
            case "^":
                return TokenType.BIT_XOR;
            case "~":
                return TokenType.BIT_NOT;
            case "++":
                return TokenType.INC;
            case "--":
                return TokenType.DEC;
            case ">":
                return TokenType.GT;
            case "<":
                return TokenType.LT;
            case ">=":
                return TokenType.GE;
            case "<=":
                return TokenType.LE;
            case "==":
                return TokenType.EQ;
            case "!=":
                return TokenType.NE;
            case "&&":
                return TokenType.AND;
            case "||":
                return TokenType.OR;
            case "!":
                return TokenType.NOT;
            case "<<":
                return TokenType.LEFT_SHIFT;
            case ">>":
                return TokenType.RIGHT_SHIFT;
            case "=":
                return TokenType.ASSIGN;
            default:
                return TokenType.UnknownOP;
        }
    }

    

    public String removeComments() {
        String pattern = "(//[^\\n]*)|(/\\*[^/]*\\*/)";
        return input.replaceAll(pattern,"");
    }


}
