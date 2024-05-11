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
            "void","char","struct", "enum", "union", "typedef"
    };

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
        Token program = new Token(TokenType.PROGRAM,"PROGRAM");
        this.root = new Node(program);  // Root node has no token
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
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Expect: while ( <expression> ) <statement>
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
        Node statement = parseStatement();
        if (statement == null) {
            return null;
        }
        node.addChild(statement);
        return node;
    }

    private Node parseIfStatement() {
        Node node = new Node("IfStatement");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Expect: if ( <expression> ) <statement>
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
            Node statement = parseStatement();
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

    private Node parseExpression() {
        Node node = new Node("Expression");
        System.out.println("Current token: " + tokens.get(currentTokenIndex).getToken());
        System.out.println("Current token type: " + tokens.get(currentTokenIndex).getType());
        // Handle identifiers, numbers, booleans, and comparison operations
        if (match(TokenType.IDENTIFIER)) {
            node.addChild(new Node("Identifier: " + tokens.get(currentTokenIndex).getToken()));
            currentTokenIndex++; // Skip identifier
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

    private void reportError(String error) {
        System.out.println("Error: " + error);
    }

    void traverse(Node node, int depth) {
        // Print the node's name with indentation based on its depth in the tree
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
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
    private boolean parsePostfixExpression() {
        Node postfixExpressionNode = new Node("postfix_expression");

        if (match("IDENTIFIER")) {
            // Identifier
            Node identifierNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the identifier
            postfixExpressionNode.addChild(identifierNode); // Add identifier node to the postfix expression node
        } else if (isConstant(tokens.get(currentTokenIndex))) {
            // Constant
            Node constantNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the constant
            postfixExpressionNode.addChild(constantNode); // Add constant node to the postfix expression node
            currentTokenIndex++;
        } else if (match("STRING")) {
            // String literal
            Node stringLiteralNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the string literal
            postfixExpressionNode.addChild(stringLiteralNode); // Add string literal node to the postfix expression node
        } else if (match("(")) {
            // Parenthesized expression
            if (!parseExpression()) {
                reportError("Invalid expression inside parentheses");
                return false;
            }
            if (!match(")")) {
                reportError("Expected ')' after expression");
                return false;
            }
        } else {
            // Invalid postfix expression
            reportError("Invalid postfix expression");
            return false;
        }

        // Check for postfix operators (e.g., array access, function call, increment, decrement)
        while (true) {
            if (match("[")) {
                // Array access
                Node arrayAccessNode = new Node(tokens.get(currentTokenIndex)); // Create a node for array access
                postfixExpressionNode.addChild(arrayAccessNode); // Add array access node to the postfix expression node

                if (!parseExpression()) {
                    reportError("Invalid expression inside array access");
                    return false;
                }
                if (!match("]")) {
                    reportError("Expected ']' after array index");
                    return false;
                }

            } else if (match("(")) {
                // Function call
                Node functionCallNode = new Node(tokens.get(currentTokenIndex)); // Create a node for function call
                postfixExpressionNode.addChild(functionCallNode); // Add function call node to the postfix expression node

                if (!parseArgumentExpressionList(functionCallNode)) {
                    // Error handling if the argument expression list parsing fails
                    return false;
                }
                if (!match(")")) {
                    reportError("Expected ')' after argument list");
                    return false;
                }

            } else if (match("++") || match("--")) {
                // Increment or decrement
                Node incrementDecrementNode = new Node(tokens.get(currentTokenIndex)); // Create a node for increment/decrement
                postfixExpressionNode.addChild(incrementDecrementNode); // Add increment/decrement node to the postfix expression node

            } else {
                // No more postfix operators
                break;
            }
        }

        currentNode.addChild(postfixExpressionNode); // Add postfix expression node to the current node
        return true;
    }

    private boolean parseArgumentExpressionList(Node functionCallNode) {
        // Create a node for the argument expression list
        Node argumentExpressionListNode = new Node("argument_expression_list");

        // Add the argument expression list node as a child of the function call node
        functionCallNode.addChild(argumentExpressionListNode);

        // Parse the first argument expression, if present
        if (!parseAssignmentExpression()) {
            // Error handling if the assignment expression parsing fails
            reportError("Invalid argument expression in function call");
            return false;
        }

        // Parse additional argument expressions separated by commas
        while (match(",")) {
            // Move to the next token after the comma

            // Parse the next argument expression
            if (!parseAssignmentExpression()) {
                // Error handling if the assignment expression parsing fails
                reportError("Invalid argument expression in function call");
                return false;
            }
        }

        return true;
    }

    private boolean match(TokenType type) {
        if (currentTokenIndex >= tokens.size()) {
            return false;
        }
        Token currentToken = tokens.get(currentTokenIndex);
        return currentToken.getType() == type;
    }

    


}
