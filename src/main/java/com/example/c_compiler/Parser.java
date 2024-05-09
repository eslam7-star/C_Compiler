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

    public boolean parse() {
        return parseStatement();
    }



    private boolean parseIfStatement() {
        if (!tokens.get(currentTokenIndex).getValue().equals("if")) {
            return false;
        }

        currentTokenIndex++;

        if (!tokens.get(currentTokenIndex).getValue().equals("(")) {
            reportError("Expected '(' after 'if'");
            return false;
        }

        currentTokenIndex++;

        if (!parseExpression()) {
            reportError("Invalid expression in if statement");
            return false;
        }

        if (!tokens.get(currentTokenIndex).getValue().equals(")")) {
            reportError("Expected ')' after expression");
            return false;
        }

        currentTokenIndex++;

        if (!parseBlock()) {
            reportError("Invalid block in if statement");
            return false;
        }

        if (tokens.get(currentTokenIndex).getValue().equals("else")) {
            currentTokenIndex++;

            if (tokens.get(currentTokenIndex).getValue().equals("if")) {
                return parseIfStatement(); // Parse else if statement recursively
            } else {
                return parseBlock();
            }
        }

        return true;
    }




    private boolean parseExpression() {
        return parseLogicalOrExpression();
    }


    private boolean parseLogicalOrExpression() {
        if (!parseLogicalAndExpression()) {
            return false;
        }

        while (match(TokenType.OR)) {
            if (!parseLogicalAndExpression()) {
                reportError("Invalid logical OR expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseLogicalAndExpression() {
        if (!parseInclusiveOrExpression()) {
            return false;
        }

        while (match(TokenType.AND)) {
            if (!parseInclusiveOrExpression()) {
                reportError("Invalid logical AND expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseInclusiveOrExpression() {
        if (!parseExclusiveOrExpression()) {
            return false;
        }

        while (match(TokenType.BIT_OR)) {
            if (!parseExclusiveOrExpression()) {
                reportError("Invalid inclusive OR expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseExclusiveOrExpression() {
        if (!parseAndExpression()) {
            return false;
        }

        while (match(TokenType.BIT_XOR)) {
            if (!parseAndExpression()) {
                reportError("Invalid exclusive OR expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseAndExpression() {
        if (!parseEqualityExpression()) {
            return false;
        }

        while (match(TokenType.BIT_AND)) {
            if (!parseEqualityExpression()) {
                reportError("Invalid AND expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseEqualityExpression() {
        if (!parseRelationalExpression()) {
            return false;
        }

        while (match(TokenType.EQ) || match(TokenType.NE)) {
            if (!parseRelationalExpression()) {
                reportError("Invalid equality expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseRelationalExpression() {
        if (!parseShiftExpression()) {
            return false;
        }

        while (match(TokenType.LT) || match(TokenType.GT) || match(TokenType.LE) || match(TokenType.GE)) {
            if (!parseShiftExpression()) {
                reportError("Invalid relational expression");
                return false;
            }
        }

        return true;
    }


    private boolean parseShiftExpression() {
        if (!parseAdditiveExpression()) {
            return false;
        }

        while (match(TokenType.LEFT_SHIFT) || match(TokenType.RIGHT_SHIFT)) {
            if (!parseAdditiveExpression()) {
                reportError("Invalid shift expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseAdditiveExpression() {
        if (!parseMultiplicativeExpression()) {
            return false;
        }

        while (match(TokenType.ADD) || match(TokenType.SUB)) {
            if (!parseMultiplicativeExpression()) {
                reportError("Invalid additive expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseMultiplicativeExpression() {
        if (!parseCastExpression()) {
            return false;
        }

        while (match(TokenType.MUL) || match(TokenType.DIV) || match(TokenType.MOD)) {
            if (!parseCastExpression()) {
                reportError("Invalid multiplicative expression");
                return false;
            }
        }

        return true;
    }

    private boolean is_a_type(String type){
        for( String str : dataTypes ){
            if( str.equals(type) )
                return true;
        }
        return false;
    }

    private boolean parseCastExpression() {
        if ( tokens.get(currentTokenIndex).getValue().matches("\\(") ) {
            currentTokenIndex++;
            if (! is_a_type(tokens.get(currentTokenIndex).getValue()) ) {
                reportError("Invalid type specifier in cast expression");
                return false;
            }
            if (!tokens.get(currentTokenIndex).getValue().matches("\\)")) {
                reportError("Expected ')' in cast expression");
                return false;
            }
            return parseCastExpression();
        } else {
            return parseUnaryExpression();
        }
    }


    private boolean parseUnaryExpression() {
        if (match(TokenType.ADD) || match(TokenType.SUB) || match(TokenType.NOT) || match(TokenType.BIT_NOT)) {
            return parseCastExpression();
        } else {
            return parsePostfixExpression();
        }
    }

    private boolean parsePostfixExpression() {
        if (!parsePrimaryExpression()) {
            return false;
        }

        while (match(TokenType.INC) || match(TokenType.DEC)) {
            // Continue parsing postfix expressions
        }

        return true;
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



    private boolean parsePrimaryExpression() {
        if (match(TokenType.IDENTIFIER) || isConstant(tokens.get(currentTokenIndex)) || match(TokenType.STRING)) {
            return true;
        } else if (tokens.get(currentTokenIndex).getValue().matches("\\(") ) {
            currentTokenIndex++;
            if (!parseExpression()) {
                reportError("Invalid expression inside parentheses");
                return false;
            }
            if (!tokens.get(currentTokenIndex).getValue().matches("\\)")) {
                reportError("Expected ')' after expression");
                return false;
            }
            currentTokenIndex++;
            return true;
        } else {
            reportError("Invalid primary expression");
            return false;
        }
    }


    private boolean parseFunctionCall() {
        if (!match(TokenType.IDENTIFIER)) {
            reportError("Expected function identifier");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches("\\(")) {
            reportError("Expected '(' after function identifier");
            return false;
        }
        currentTokenIndex++;
        if (!parseArgumentList()) {
            // Error handling if the argument list parsing fails
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches("\\)")) {
            reportError("Expected ')' after argument list");
            return false;
        }
        currentTokenIndex++;
        return true;
    }

    private boolean parseArgumentList() {
        if (parseExpression()) {
            while (tokens.get(currentTokenIndex).getValue().matches(",")) {
                currentTokenIndex++;
                if (!parseExpression()) {
                    reportError("Expected expression after ','");
                    return false;
                }
            }
        }
        return true; // Argument list is optional
    }

    private boolean parseArrayAccess() {
        if (!match(TokenType.IDENTIFIER)) {
            reportError("Expected array identifier");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches("\\[")) {
            reportError("Expected '[' after array identifier");
            return false;
        }
        currentTokenIndex++;
        if (!parseExpression()) {
            reportError("Invalid expression inside array access");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches("\\]")) {
            reportError("Expected ']' after array index");
            return false;
        }
        currentTokenIndex++;
        return true;
    }

    private boolean parseBlock() {
        if (!tokens.get(currentTokenIndex).getValue().equals("{")) {
            reportError("Expected '{' to start block");
            return false;
        }

        currentTokenIndex++;

        while (!tokens.get(currentTokenIndex).getValue().equals("}")) {
            if (!parseStatement()) {
                reportError("Invalid statement inside block");
                return false;
            }
        }

        currentTokenIndex++;
        return true;
    }


    private boolean parseStatement() {
        if (match("if")) {
            return parseIfStatement();
        } else if (match("for")) {
            return parseForStatement();
        } else if (match("while")) {
            return parseWhileStatement();
        } else if (match("do")) {
            return parseDoWhileStatement();
        } else if (is_a_type(tokens.get(currentTokenIndex).getValue())) {
            return parseFunctionDefinition();
        } else {
            // Since no statement type matches, return false
            reportError("Invalid statement");
            return false;
        }
    }

    private boolean parseFunctionDefinition() {
        Node functionNode = new Node(tokens.get(currentTokenIndex)); // Create a new node for the function
        currentNode.addChild(functionNode); // Add the function node to the parse tree
        currentNode = functionNode; // Set the current node to the function node
    
        if (!is_a_type(tokens.get(currentTokenIndex).getValue())) {
            reportError("Expected function return type");
            return false;
        }
        currentTokenIndex++;
    
        if (!match(TokenType.IDENTIFIER)) {
            reportError("Expected function name");
            return false;
        }
        currentTokenIndex++;
    
        if (!tokens.get(currentTokenIndex).getValue().equals("(")) {
            reportError("Expected '(' after function name");
            return false;
        }
        currentTokenIndex++;
    
        if (!parseParameterList()) {
            return false;
        }
    
        if (!tokens.get(currentTokenIndex).getValue().equals(")")) {
            reportError("Expected ')' after parameter list");
            return false;
        }
        currentTokenIndex++;
    
        if (!parseBlock()) {
            return false;
        }
    
        currentNode = currentNode.getParent(); // Go back to the parent node after parsing the function
    
        return true;
    }

    private boolean parseParameterList() {
        Node parameterListNode = new Node(null); // Create a new node for the parameter list
        currentNode.addChild(parameterListNode); // Add the parameter list node to the parse tree
        Node currentNodeBackup = currentNode; // Backup the current node
        currentNode = parameterListNode; // Set the current node to the parameter list node
    
        do {
            Node parameterNode = new Node(tokens.get(currentTokenIndex)); // Create a new node for the parameter
            currentNode.addChild(parameterNode); // Add the parameter node to the parse tree
            currentNode = parameterNode; // Set the current node to the parameter node
    
            if (!is_a_type(tokens.get(currentTokenIndex).getValue())) {
                reportError("Expected parameter type");
                return false;
            }
            currentTokenIndex++;
    
            if (!match(TokenType.IDENTIFIER)) {
                reportError("Expected parameter name");
                return false;
            }
            currentTokenIndex++;
    
            currentNode = currentNode.getParent(); // Go back to the parameter list node after parsing the parameter
    
            if (tokens.get(currentTokenIndex).getValue().equals(",")) {
                currentTokenIndex++;
            } else if (tokens.get(currentTokenIndex).getValue().equals(")")) {
                break;
            } else {
                reportError("Expected ',' or ')' after parameter");
                return false;
            }
        } while (true);
    
        currentNode = currentNodeBackup; // Restore the current node
    
        return true;
    }

    private boolean parseForStatement() {
        if (!tokens.get(currentTokenIndex).getValue().matches("\\(")) {
            reportError("Expected '(' after 'for'");
            return false;
        }
        currentTokenIndex++;
        // Parse initialization expression
        if (!parseExpression()) {
            reportError("Invalid initialization expression in for statement");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches(";")) {
            reportError("Expected ';' after initialization expression");
            return false;
        }
        currentTokenIndex++;
        // Parse condition expression
        if (!parseExpression()) {
            reportError("Invalid condition expression in for statement");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches(";")) {
            reportError("Expected ';' after condition expression");
            return false;
        }
        currentTokenIndex++;
        // Parse update expression
        if (!parseExpression()) {
            reportError("Invalid update expression in for statement");
            return false;
        }
        if (!tokens.get(currentTokenIndex).getValue().matches("\\)")) {
            reportError("Expected ')' after update expression");
            return false;
        }
        currentTokenIndex++;
        // Parse loop body
        return parseBlock();
    }

    private boolean parseWhileStatement() {
        if (!match("(")) {
            reportError("Expected '(' after 'while'");
            return false;
        }
        // Parse condition expression
        if (!parseExpression()) {
            reportError("Invalid condition expression in while statement");
            return false;
        }
        if (!match(")")) {
            reportError("Expected ')' after condition expression");
            return false;
        }
        // Parse loop body
        return parseBlock();
    }

    private boolean parseDoWhileStatement() {
        // Parse loop body
        if (!parseBlock()) {
            reportError("Invalid block in do-while statement");
            return false;
        }
        if (!match("while")) {
            reportError("Expected 'while' after block in do-while statement");
            return false;
        }
        if (!match("(")) {
            reportError("Expected '(' after 'while'");
            return false;
        }
        // Parse condition expression
        if (!parseExpression()) {
            reportError("Invalid condition expression in do-while statement");
            return false;
        }
        if (!match(")")) {
            reportError("Expected ')' after condition expression");
            return false;
        }
        if (!match(";")) {
            reportError("Expected ';' after do-while statement");
            return false;
        }
        return true;
    }



    private boolean match(String keyword) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getValue().equals(keyword)) {
            currentTokenIndex++;
            return true;
        }
        return false;
    }




    private boolean match(TokenType expectedTokenType) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getType() == expectedTokenType) {
            currentTokenIndex++;
            return true;
        }
        return false;
    }

    private void reportError(String message) {
        System.out.println("Error: " + message);
    }


}
