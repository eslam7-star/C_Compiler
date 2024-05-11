package com.example.c_compiler;

import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int currentTokenIndex;
    private Node root;
    private Node currentNode;

    String[] dataTypes = {
            "int", "short", "long", "long long", "unsigned int",
            "float", "double", "long double",
            "char", "signed char", "unsigned char",
            "void"
    };

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
        this.root = new Node(null);  // Root node has no token
        this.currentNode = root;
    }

    public Node parse() {
        Node program = new Node("Program");
        while (currentTokenIndex < tokens.size()) {
            Node statement = parseStatement();
            if (statement == null) {
                return null;
            }
            program.addChild(statement);
        }
        return program;
    }

    private Node parseStatement() {
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());

        if (currentTokenIndex >= tokens.size()) {
            reportError("Unexpected end of input");
            return null;
        }
    
        if (match(TokenType.KEYWORD) && "int".equals(tokens.get(currentTokenIndex).getToken())) {
            return parseDeclarationStatement();
        } else if (match(TokenType.IDENTIFIER)) {
            return parseAssignmentStatement();
        } else if (match(TokenType.KEYWORD) && "while".equals(tokens.get(currentTokenIndex).getToken())) {
            return parseWhileStatement();
        } else if (match(TokenType.KEYWORD) && "if".equals(tokens.get(currentTokenIndex).getToken())) {
            return parseIfStatement();
        } else {
            reportError("Invalid statement");
            return null;
        }
    }

    private Node parseDeclarationStatement() {
        Node node = new Node("DeclarationStatement");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Expect: int <identifier> ;
        currentTokenIndex++; // Skip 'int'
        if (!match(TokenType.IDENTIFIER)) {
            reportError("Expected identifier");
            return null;
        }
        node.addChild(new Node("Identifier: " + tokens.get(currentTokenIndex).getToken()));
        currentTokenIndex++; // Skip identifier
        if (!match(TokenType.SYMBOL) || !";".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected ';'");
            return null;
        }
        currentTokenIndex++; // Skip ';'
        return node;
    }

    private Node parseAssignmentStatement() {
        Node node = new Node("AssignmentStatement");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Expect: <identifier> = <expression> ;
        if (!match(TokenType.IDENTIFIER)) {
            reportError("Expected identifier");
            return null;
        }
        node.addChild(new Node("Identifier: " + tokens.get(currentTokenIndex).getToken()));
        currentTokenIndex++; // Skip identifier
        if (!match(TokenType.ASSIGN)) {
            reportError("Expected '='");
            return null;
        }
        currentTokenIndex++; // Skip '='
        Node expression = parseExpression();
        if (expression == null) {
            return null;
        }
        node.addChild(expression);
        if (!match(TokenType.SYMBOL) || !";".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected ';'");
            return null;
        }
        currentTokenIndex++; // Skip ';'
        return node;
    }

    private Node parseWhileStatement() {
        Node node = new Node("WhileStatement");
    
        currentTokenIndex++; // Skip 'while'
    
        if (!match(TokenType.SYMBOL) || !"(".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected '('");
            return null;
        }
    
        currentTokenIndex++; // Skip '('
        Node expression = parseExpression();
        if (expression == null) {
            return null;
        }
        node.addChild(expression);
    
        if (!match(TokenType.SYMBOL) || !")".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected ')'");
            return null;
        }
    
        currentTokenIndex++; // Skip ')'
    
        if (currentTokenIndex < tokens.size()) {
            Node statement;
            if (match("{")) {
                currentTokenIndex--; // Go back to '{'
                statement = parseBlock();
            } else {
                statement = parseStatement();
            }
            if (statement == null) {
                return null;
            }
            node.addChild(statement);
        } else {
            reportError("Expected a statement after 'while' condition");
            return null;
        }
    
        return node;
    }

    private Node parseIfStatement() {
        Node node = new Node("IfStatement");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        currentTokenIndex++; // Skip 'if'
    
        if (!match(TokenType.SYMBOL) || !"(".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected '('");
            return null;
        }
    
        currentTokenIndex++; // Skip '('
        Node expression = parseExpression();
        if (expression == null) {
            return null;
        }
        node.addChild(expression);
    
        if (!match(TokenType.SYMBOL) || !")".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected ')'");
            return null;
        }
    
        currentTokenIndex++; // Skip ')'
    
        if (currentTokenIndex < tokens.size()) {
            Node statement;
            if (match("{")) {
                currentTokenIndex--; // Skip '{'
                statement = parseBlock();
            } else {
                statement = parseStatement();
            }
            if (statement == null) {
                return null;
            }
            node.addChild(statement);
        } else {
            reportError("Expected a statement after 'if' condition");
            return null;
        }
    
        return node;
    }

    private Node parseBlock() {
        Node blockNode = new Node("block");
    
        // Check if the current token is '{'
        if (!match("{")) {
            reportError("Expected '{' to start a block");
            return null;
        }
    
        // Parse statements inside the block until '}'
        while (!tokens.get(currentTokenIndex).getValue().equals("}")) {
            Node statement = parseStatement();
            if (statement == null) {
                reportError("Invalid statement inside block");
                return null;
            }
            blockNode.addChild(statement);
        }
    
        // Check if the current token is '}'
        if (!match("}")) {
            reportError("Expected '}' to end a block");
            return null;
        }
    
        return blockNode;
    }

    private Node parseExpression() {
        Node node = new Node("Expression");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Handle identifiers, numbers, booleans, and comparison operations
        if (match(TokenType.IDENTIFIER)) {
            node.addChild(new Node("Identifier: " + tokens.get(currentTokenIndex).getToken()));
            currentTokenIndex++; // Skip identifier
            if (match(TokenType.EQ) || match(TokenType.GE) || match(TokenType.LE) || match(TokenType.NE) || match(TokenType.GT) || match(TokenType.LT)) {
                node.addChild(new Node("Operator: " + tokens.get(currentTokenIndex).getToken()));
                currentTokenIndex++; // Skip operator
                Node rightExpression = parseExpression();
                if (rightExpression == null) {
                    return null;
                }
                node.addChild(rightExpression);
            }
        } else if (match(TokenType.DECIMAL)) {
            node.addChild(new Node("Number: " + tokens.get(currentTokenIndex).getToken()));
            currentTokenIndex++; // Skip number
            if (match(TokenType.EQ)) {
                node.addChild(new Node("Operator: " + tokens.get(currentTokenIndex).getToken()));
                currentTokenIndex++; // Skip operator
                Node rightExpression = parseExpression();
                if (rightExpression == null) {
                    return null;
                }
                node.addChild(rightExpression);
            }
        } else if (match(TokenType.KEYWORD) && ("true".equals(tokens.get(currentTokenIndex).getToken()) || "false".equals(tokens.get(currentTokenIndex).getToken()))) {
            node.addChild(new Node("Boolean: " + tokens.get(currentTokenIndex).getToken()));
            currentTokenIndex++; // Skip boolean
        } else {
            reportError("Expected expression");
            return null;
        }
        return node;
        
    }
    /* 
    private Node parseBlock() {
        Node node = new Node("Block");
        if (!match(TokenType.SYMBOL) || !"{".equals(tokens.get(currentTokenIndex).getToken())) {
            reportError("Expected '{'");
            return null;
        }
        currentTokenIndex++; // Skip '{'
        while (!match(TokenType.SYMBOL) || !"}".equals(tokens.get(currentTokenIndex).getToken())) {
            Node statement = parseStatement();
            if (statement == null) {
                return null;
            }
            node.addChild(statement);
        }
        currentTokenIndex++; // Skip '}'
        return node;
    }
    */
    
    private void reportError(String error) {
        System.out.println("Error: " + error);
    }

    void traverse(Node node, int depth) {
        // Print the node's name with indentation based on its depth in the tree
        if (!node.name.equals("program")) {
            for (int i = 0; i < depth; i++) {
                System.out.print("    "); // Reduced to 4 spaces
            }
        }
        System.out.println(node.name);
    
        // Recursively traverse the node's children
        for (Node child : node.children) {
            traverse(child, depth + 1);
        }
    }

    
    

    private boolean is_a_type(String type){
        for( String str : dataTypes ){
            if( str.equals(type) )
                return true;
        }
        return false;
    }

    

    private boolean isConstant(Token token) {
        TokenType type = token.getType();
        return type == TokenType.DECIMAL ||
                type == TokenType.OCTAL ||
                type == TokenType.BINARY ||
                type == TokenType.HEX ||
                type == TokenType.FLOAT ||
                type == TokenType.STRING ||
                type == TokenType.Character;
    }



    



    private boolean match(String keyword) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getValue().equals(keyword)) {
            currentTokenIndex++;
            return true;
        }
        return false;
    }




    private boolean match(TokenType type) {
        if (currentTokenIndex >= tokens.size()) {
            return false;
        }
        Token currentToken = tokens.get(currentTokenIndex);
        return currentToken.getType() == type;
    }

    


}
