package com.example.c_compiler;
import java.util.*;


public class Lexer {
    private String input;
    private int currentPosition;
    private static final String[] KEYWORDS = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile","while"};

    List<Tokenn> tokens;

    protected SymbolTable symbolTable;



    public Lexer(String input, SymbolTable symbolTable) {
        this.input=input;
        this.currentPosition = 0;
        tokens = new ArrayList<>();
        this.symbolTable = symbolTable;
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
                    if( op.equals("=") && Character.toString(input.charAt(currentPosition)).matches("[-+*/%&|<>^!~]") ){
                        tokens.add(new Tokenn(TokenType.ASSIGN,"="));
                        continue;
                    }else if( currentPosition < input.length() && Character.toString(input.charAt(currentPosition)).matches("[-+*/%&|<>^!~=]") ) {
                        op += Character.toString(input.charAt(currentPosition));
                    }else {
                        currentPosition--;
                    }
                    tokens.add(new Tokenn( recognizeOperator(op) ,op));
                }else if ( !op.equals("\s") && !op.equals("\n") ){
                    tokens.add(new Tokenn(TokenType.SYMBOL,op));
                    if ( op.equals("{") ){
                        symbolTable.startScope();
                    } else if ( op.equals("}") ) {
                        symbolTable.endScope();
                    }
                }
                op = "";
            }else{
                StringBuilder b = new StringBuilder();
                if ( input.charAt(currentPosition) == '"' ){
                    currentPosition++;
                    while ( input.charAt(currentPosition) != '"' ){
                        b.append(input.charAt(currentPosition));
                        currentPosition++;
                    }
                    tokens.add(new Tokenn(TokenType.STRING,b.toString()));
                }else {
                    buffer.append(input.charAt(currentPosition));
                }
            }
            currentPosition++;
        }
        if(buffer.length() > 0) {
            addToken(buffer);
        }
        buffer.delete(0, buffer.length());
    }

    public void addToken(StringBuilder buffer) {
        String sbuffer = buffer.toString();
        if(is_keyword(sbuffer)) {
            tokens.add(new Tokenn(TokenType.KEYWORD, sbuffer));
        } else if(sbuffer.matches("[a-zA-Z_$][a-zA-Z0-9_$]*")) {
            Tokenn id = new Tokenn(TokenType.IDENTIFIER, sbuffer);
            tokens.add(id);
            if( tokens.size() - 1 > 0 ) {
                Tokenn pre_token = tokens.get(tokens.size() - 2);
                if ( pre_token.getType() == TokenType.KEYWORD){
                    id.setId_type(pre_token.getValue());
                }
                symbolTable.addSymbol(sbuffer,id);
            }
        } else if(sbuffer.matches("\\\".*?\\\"")) {
            tokens.add(new Tokenn(TokenType.STRING, sbuffer));
        }else if( sbuffer.matches("'(\\\\.|[^'\\\\])*'")){
            tokens.add(new Tokenn(TokenType.Character,sbuffer));
        }else if( sbuffer.matches("(((([0-9]*\\.[0-9]+)|([0-9]*))([eE][-+]?[0-9]+)?)|([0-9.]*(e|E)))") || sbuffer.matches("[1-9][0-9]*|0") || sbuffer.matches("0[bB][01]+") || sbuffer.matches("0[xX][0-9a-fA-F]+") || sbuffer.matches("0[0-7]+")){
            while (tokens.size() > 1 &&
                    (tokens.get(tokens.size() - 1).getType() == TokenType.SUB ||
                            tokens.get(tokens.size() - 1).getType() == TokenType.ADD || (tokens.get(tokens.size() - 1).getType() == TokenType.UnknownOP && tokens.get(tokens.size() - 1).getValue().matches("[+-][-+]") ) )) {
                Tokenn t1 = tokens.get(tokens.size() - 1);
                Tokenn t2 = tokens.get(tokens.size() - 2);


                if (!(t2.getType() == TokenType.IDENTIFIER || t2.getType() == TokenType.INC ||
                        t2.getType() == TokenType.DEC ) ) {
                    sbuffer = t1.getValue() + sbuffer;
                    tokens.remove(tokens.size() - 1);
                } else {
                    break;
                }
            }

            if( sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)[1-9][0-9]*|0")){
                // handling floating signed E/e
                if ( tokens.size() > 1 && tokens.get( tokens.size() - 1).getValue().matches("((\\+-)*\\+?|(-\\+)*-?)[0-9.]*(e|E)")){
                    sbuffer = tokens.get(tokens.size() - 1).getValue() + sbuffer;
                    if( tokens.size() > 1 && tokens.get(tokens.size()-2).getValue().matches("((\\+-)*\\+?|(-\\+)*-?)") ) {
                        sbuffer = tokens.get(tokens.size() - 2).getValue() + sbuffer;
                        tokens.remove(tokens.size() -2);
                    }
                    tokens.get( tokens.size() - 1).setToken(sbuffer);
                }
                else {
                    tokens.add(new Tokenn(TokenType.DECIMAL, sbuffer));
                }
            }else if( sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)0[bB][01]+")){
                tokens.add(new Tokenn(TokenType.BINARY,sbuffer));
            }else if( sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)0[xX][0-9a-fA-F]+")){
                tokens.add(new Tokenn(TokenType.HEX,sbuffer));
            }else if( sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)0[0-7]+")){
                tokens.add(new Tokenn(TokenType.OCTAL,sbuffer));
            }else if( sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)(((([0-9]*\\.[0-9]+)|([0-9]*))([eE][-+]?[0-9]+)?)|([0-9.]*(e|E)))") && !sbuffer.matches("((\\+-)*\\+?|(-\\+)*-?)(0[0-9]*)")){
                tokens.add(new Tokenn(TokenType.FLOAT,sbuffer));
            }else {
                System.out.println("Syntax Error here :" + sbuffer);
            }
        }else{
                System.out.println("Syntax Error here :" + sbuffer);
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
            case "+=":
                return TokenType.ADD_ASSIGN;
            case "*=":
                return TokenType.MUL_ASSIGN;
            case "/=":
                return TokenType.DIV_ASSIGN;
            case "-=":
                return TokenType.SUB_ASSIGN;
            case "%=":
                return TokenType.MOD_ASSIGN;
            default:
                return TokenType.UnknownOP;
        }
    }

    

    public String removeComments() {
        String pattern = "(//[^\\n]*)|(/\\*[^/]*\\*/)";
        return input.replaceAll(pattern,"");
    }

    public List<Tokenn> getTokens() {
        return tokens;
    }


}

