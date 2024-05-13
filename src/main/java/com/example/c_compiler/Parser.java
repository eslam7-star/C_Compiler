package com.example.c_compiler;

import java.util.List;

public class Parser {
    private List<Tokenn> tokens;
    private int currentTokenIndex;
    private Node root;
    private Node currentNode;

    String[] dataTypes = {
            "int", "short", "long", "long long", "unsigned int",
            "float", "double", "long double",
            "char", "signed char", "unsigned char",
            "void","char","struct", "enum", "union", "typedef"
    };

    public Parser(List<Tokenn> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
        Tokenn program = new Tokenn(TokenType.PROGRAM,"PROGRAM");
        this.root = new Node(program);  // Root node has no token
        this.currentNode = root;
    }

    public boolean parse() {
        return parseProgram();
    }

    private boolean parseProgram() {
        while (currentTokenIndex < tokens.size()) {
            if (!parseFunctionDeclaration() && !parseDeclaration()) {
                return false;
            }
        }
        return true;
    }

    private boolean parseAssignmentExpression() {
        Node assignmentExpressionNode = new Node("assignment_expression");
        if (!parseConditionalExpression()) {
            return false;
        }
        while (true) {
            if (match("=") || match("+=") || match("-=") || match("*=") || match("/=") || match("%=") || match("<<=") || match(">>=") || match("&=") || match("^=") || match("|=")) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                assignmentExpressionNode.addChild(operatorNode); // Add operator node to the assignment expression node
                if (!parseAssignmentExpression()) {
                    return false;
                }
            } else {
                break;
            }
        }
        currentNode.addChild(assignmentExpressionNode); // Add assignment expression node to the current node
        return true;
    }

    private boolean parseConditionalExpression() {
        Node conditionalExpressionNode = new Node("conditional_expression");
        if (!parseLogicalOrExpression()) {
            return false;
        }
        if (match("?")) {
            Node questionNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the '?'
            conditionalExpressionNode.addChild(questionNode); // Add '?' node to the conditional expression node
            if (!parseExpression()) {
                return false;
            }
            if (!match(":")) {
                reportError("Expected ':' in conditional expression");
                return false;
            }
            Node colonNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the ':'
            conditionalExpressionNode.addChild(colonNode); // Add ':' node to the conditional expression node
            if (!parseConditionalExpression()) {
                return false;
            }
        }
        currentNode.addChild(conditionalExpressionNode); // Add conditional expression node to the current node
        return true;
    }

    private boolean parseLogicalOrExpression() {
        Node logicalOrExpressionNode = new Node("logical_or_expression");
        currentNode.addChild(logicalOrExpressionNode); // Add logical or expression node to the current node
        if (parseLogicalAndExpression()) {
            while (match("||")) {
                parseLogicalAndExpression(); // Recursive call
            }
        }
        return true;
    }

    private boolean parseLogicalAndExpression() {
        Node logicalAndExpressionNode = new Node("logical_and_expression");
        currentNode.addChild(logicalAndExpressionNode); // Add logical and expression node to the current node
        if (parseInclusiveOrExpression()) {
            while (match("&&")) {
                parseInclusiveOrExpression(); // Recursive call
            }
        }
        return true;
    }

    private boolean parseInclusiveOrExpression() {
        Node inclusiveOrExpressionNode = new Node("inclusive_or_expression");
        currentNode.addChild(inclusiveOrExpressionNode); // Add inclusive or expression node to the current node
        if (parseExclusiveOrExpression()) {
            while (match("|")) {
                parseExclusiveOrExpression(); // Recursive call
            }
        }
        return true;
    }

    private boolean parseExclusiveOrExpression() {
        Node exclusiveOrExpressionNode = new Node("exclusive_or_expression");
        currentNode.addChild(exclusiveOrExpressionNode); // Add exclusive or expression node to the current node
        if (parseAndExpression()) {
            while (match("^")) {
                parseAndExpression(); // Recursive call
            }
        }
        return true;
    }

    private boolean parseAndExpression() {
        Node andExpressionNode = new Node("and_expression");
        currentNode.addChild(andExpressionNode); // Add and expression node to the current node
        if (parseEqualityExpression()) {
            while (match("&")) {
                parseEqualityExpression(); // Recursive call
            }
        }
        return true;
    }

    private boolean parseEqualityExpression() {
        Node equalityExpressionNode = new Node("equality_expression");
        currentNode.addChild(equalityExpressionNode); // Add equality expression node to the current node
        if (parseRelationalExpression()) {
            while (match("==") || match("!=")) {
                parseRelationalExpression(); // Recursive call
            }
        }
        return true;
    }
    private boolean parseShiftExpression() {
        Node shiftExpressionNode = new Node("shift_expression");
        currentNode.addChild(shiftExpressionNode); // Add shift expression node to the current node
        if (parseAdditiveExpression()) {
            while (match(TokenType.LEFT_SHIFT) || match(TokenType.RIGHT_SHIFT)) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                shiftExpressionNode.addChild(operatorNode); // Add operator node to the shift expression node

                if (!parseAdditiveExpression()) {
                    reportError("Invalid additive expression");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean parseAdditiveExpression() {
        Node additiveExpressionNode = new Node("additive_expression");
        currentNode.addChild(additiveExpressionNode); // Add additive expression node to the current node
        if (parseMultiplicativeExpression()) {
            while (match(TokenType.ADD) || match(TokenType.SUB)) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                additiveExpressionNode.addChild(operatorNode); // Add operator node to the additive expression node
                if (!parseMultiplicativeExpression()) {
                    reportError("Invalid multiplicative expression");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean parseMultiplicativeExpression() {
        Node multiplicativeExpressionNode = new Node("multiplicative_expression");
        currentNode.addChild(multiplicativeExpressionNode); // Add multiplicative expression node to the current node
        if (parseCastExpression()) {
            while (match(TokenType.MUL) || match(TokenType.DIV) || match(TokenType.MOD)) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                multiplicativeExpressionNode.addChild(operatorNode); // Add operator node to the multiplicative expression node
                if (!parseCastExpression()) {
                    reportError("Invalid cast expression");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean parseCastExpression() {
        Node castExpressionNode = new Node("cast_expression");
        currentNode.addChild(castExpressionNode); // Add cast expression node to the current node

        if (match("(")) {
            // Check if it's a type name or a unary expression
            if ( is_a_type(tokens.get(currentTokenIndex).getValue()) ) {
                // Parse type name
                Node typeNameNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the type name
                castExpressionNode.addChild(typeNameNode); // Add type name node to the cast expression node
                if (!parseTypeName()) {
                    reportError("Invalid type name in cast expression");
                    return false;
                }
                if (!match(")")) {
                    reportError("Expected ')' after type name in cast expression");
                    return false;
                }
                // Parse cast expression
                if (!parseCastExpression()) {
                    reportError("Invalid cast expression");
                    return false;
                }
            } else {
                // Parse unary expression
                if (!parseUnaryExpression()) {
                    reportError("Invalid unary expression in cast expression");
                    return false;
                }
            }
        } else {
            // Parse unary expression
            if (!parseUnaryExpression()) {
                reportError("Invalid unary expression in cast expression");
                return false;
            }
        }

        return true;
    }

    private boolean parseTypeName() {
        Node typeNameNode = new Node("type_name");
        currentNode.addChild(typeNameNode); // Add type name node to the current node

        // Parse type specifier
        if (!parseTypeSpecifier()) {
            reportError("Invalid type specifier in type name");
            return false;
        }

        // Check for pointer
        while (match("*")) {
            Node pointerNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the pointer
            typeNameNode.addChild(pointerNode); // Add pointer node to the type name node
            currentTokenIndex++;
        }

        // Parse type qualifier if present
        if (isTypeQualifier()) {
            if (!parseTypeQualifier()) {
                reportError("Invalid type qualifier in type name");
                return false;
            }
        }

        return true;
    }

    private boolean isTypeQualifier() {
        // Check if the current token is a type qualifier
        String tokenValue = tokens.get(currentTokenIndex).getValue();
        return tokenValue.equals("const") || tokenValue.equals("volatile");
    }



    private boolean parseUnaryExpression() {
        Node unaryExpressionNode = new Node("unary_expression");
        currentNode.addChild(unaryExpressionNode); // Add unary expression node to the current node
        if (parsePostfixExpression()) {
            // Check if there's a unary operator
            if (match(TokenType.ADD) || match(TokenType.SUB) || match(TokenType.NOT) || match(TokenType.BIT_NOT)) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                unaryExpressionNode.addChild(operatorNode); // Add operator node to the unary expression node

                if (!parseCastExpression()) {
                    reportError("Invalid cast expression after unary operator");
                    return false;
                }
            }
        } else if (match("sizeof")) {
            Node sizeofNode = new Node(tokens.get(currentTokenIndex)); // Create a node for sizeof
            unaryExpressionNode.addChild(sizeofNode); // Add sizeof node to the unary expression node
            currentTokenIndex++;
            if (match("(")) {
                if ( is_a_type(tokens.get(currentTokenIndex).getValue()) ) {
                    // Parse type name
                    if (!parseTypeName()) {
                        reportError("Invalid type name in sizeof expression");
                        return false;
                    }
                    if (!match(")")) {
                        reportError("Expected ')' after type name in sizeof expression");
                        return false;
                    }

                } else {
                    // Parse unary expression
                    if (!parseUnaryExpression()) {
                        reportError("Invalid unary expression in sizeof expression");
                        return false;
                    }
                    if (!match(")")) {
                        reportError("Expected ')' after unary expression in sizeof expression");
                        return false;
                    }

                }
            } else {
                // Parse unary expression
                if (!parseUnaryExpression()) {
                    reportError("Invalid unary expression in sizeof expression");
                    return false;
                }
            }
        } else {
            // Parse postfix expression
            if (!parsePostfixExpression()) {
                reportError("Invalid postfix expression in unary expression");
                return false;
            }
        }
        return true;
    }

    private boolean parseRelationalExpression() {
        Node relationalExpressionNode = new Node("relational_expression");
        currentNode.addChild(relationalExpressionNode); // Add relational expression node to the current node

        if (parseShiftExpression()) {
            while (match(TokenType.LT) || match(TokenType.GT) || match(TokenType.LE) || match(TokenType.GE)) {
                Node operatorNode = new Node(tokens.get(currentTokenIndex)); // Create a node for the operator
                relationalExpressionNode.addChild(operatorNode); // Add operator node to the relational expression node

                if (!parseShiftExpression()) {
                    reportError("Invalid shift expression");
                    return false;
                }
            }
        }

        return true;
    }

    private boolean parseLabeledStatement() {
        if (match(TokenType.IDENTIFIER)) {
            // Labeled identifier statement
            Node labeledStatementNode = new Node("labeled_statement");
            currentNode.addChild(labeledStatementNode); // Add labeled statement node to the current node

            Node identifierNode = new Node(tokens.get(currentTokenIndex - 1)); // Create a node for the identifier
            labeledStatementNode.addChild(identifierNode); // Add identifier node to the labeled statement node

            if (!match(":")) {
                reportError("Expected ':' after identifier in labeled statement");
                return false;
            }

            // Parse the statement following the label
            Node statementFollowingLabelNode = new Node("statement_following_label");
            if (!parseStatement()) {
                reportError("Invalid statement following label");
                return false;
            }
            labeledStatementNode.addChild(statementFollowingLabelNode);

            return true;
        } else if (match("case")) {
            // Case statement
            Node caseStatementNode = new Node("case_statement");
            currentNode.addChild(caseStatementNode); // Add case statement node to the current node

            // Parse constant expression after 'case'
            Node constantExpressionNode = new Node(tokens.get(currentTokenIndex));
            if (!parseExpression()) {
                reportError("Invalid constant expression after 'case'");
                return false;
            }
            caseStatementNode.addChild(constantExpressionNode);

            if (!match(":")) {
                reportError("Expected ':' after constant expression in case statement");
                return false;
            }

            // Parse the statement following the case
            Node statementFollowingCaseNode = new Node("statement_following_case");
            if (!parseStatement()) {
                reportError("Invalid statement following case");
                return false;
            }
            caseStatementNode.addChild(statementFollowingCaseNode);

            return true;
        } else if (match("default")) {
            // Default statement
            Node defaultStatementNode = new Node("default_statement");
            currentNode.addChild(defaultStatementNode); // Add default statement node to the current node

            if (!match(":")) {
                reportError("Expected ':' after 'default' in default statement");
                return false;
            }

            // Parse the statement following the default
            Node statementFollowingDefaultNode = new Node("statement_following_default");
            if (!parseStatement()) {
                reportError("Invalid statement following default");
                return false;
            }
            defaultStatementNode.addChild(statementFollowingDefaultNode);

            return true;
        } else {
            reportError("Expected labeled statement (identifier, case, or default)" + currentTokenIndex);
            return false;
        }
    }

    private boolean parseStatement() {
        Node statementNode = new Node("statement");
        currentNode.addChild(statementNode); // Add statement node to the current node

        if (parseLabeledStatement()
                || parseCompoundStatement()
                || parseExpressionStatement()
                || parseSelectionStatement()
                || parseIterationStatement()
                || parseJumpStatement()) {
            return true;
        }
        return false;
    }

    private boolean parseIterationStatement() {
        if (match("while")) {
            // While statement
            Node whileStatementNode = new Node("while_statement");
            currentNode.addChild(whileStatementNode); // Add while statement node to the current node

            if (!match("(")) {
                reportError("Expected '(' after 'while'");
                return false;
            }

            // Parse expression inside parentheses
            Node expressionNode = new Node("expression");
            if (!parseExpression()) {
                reportError("Invalid expression in while statement");
                return false;
            }
            whileStatementNode.addChild(expressionNode);

            if (!match(")")) {
                reportError("Expected ')' after expression in while statement");
                return false;
            }

            // Parse statement inside while loop
            Node statementInsideWhileNode = new Node("statement_inside_while");
            if (!parseStatement()) {
                reportError("Invalid statement inside while loop");
                return false;
            }
            whileStatementNode.addChild(statementInsideWhileNode);

            return true;
        } else if (match("do")) {
            // Do-while statement
            Node doWhileStatementNode = new Node("do_while_statement");
            currentNode.addChild(doWhileStatementNode); // Add do-while statement node to the current node

            // Parse statement inside do-while loop
            Node statementInsideDoWhileNode = new Node("statement_inside_do_while");
            if (!parseStatement()) {
                reportError("Invalid statement inside do-while loop");
                return false;
            }
            doWhileStatementNode.addChild(statementInsideDoWhileNode);

            if (!match("while")) {
                reportError("Expected 'while' after statement in do-while loop");
                return false;
            }

            if (!match("(")) {
                reportError("Expected '(' after 'while'");
                return false;
            }

            // Parse expression inside parentheses
            Node expressionNode = new Node("expression");
            if (!parseExpression()) {
                reportError("Invalid expression in do-while statement");
                return false;
            }
            doWhileStatementNode.addChild(expressionNode);

            if (!match(")")) {
                reportError("Expected ')' after expression in do-while statement");
                return false;
            }

            if (!match(";")) {
                reportError("Expected ';' after do-while statement");
                return false;
            }

            return true;
        } else if (match("for")) {
            // For statement
            Node forStatementNode = new Node("for_statement");
            currentNode.addChild(forStatementNode); // Add for statement node to the current node

            if (!match("(")) {
                reportError("Expected '(' after 'for'");
                return false;
            }

            // Parse the first expression statement
            Node expressionStatement1Node = new Node("expression_statement_1");
            if (!parseExpressionStatement()) {
                reportError("Invalid expression statement in for loop");
                return false;
            }
            forStatementNode.addChild(expressionStatement1Node);

            // Parse the second expression statement
            Node expressionStatement2Node = new Node("expression_statement_2");
            if (!parseExpressionStatement()) {
                reportError("Invalid expression statement in for loop");
                return false;
            }
            forStatementNode.addChild(expressionStatement2Node);

            // Parse the third expression inside for loop
            Node expressionNode = new Node("expression");
            if (!parseExpression()) {
                reportError("Invalid expression in for loop");
                return false;
            }
            forStatementNode.addChild(expressionNode);

            if (!match(")")) {
                reportError("Expected ')' after expression in for loop");
                return false;
            }

            // Parse statement inside for loop
            Node statementInsideForNode = new Node("statement_inside_for");
            if (!parseStatement()) {
                reportError("Invalid statement inside for loop");
                return false;
            }
            forStatementNode.addChild(statementInsideForNode);

            return true;
        } else {
            reportError("Expected iteration statement (while, do, or for)");
            return false;
        }
    }

    private boolean parseJumpStatement() {
        if (match("goto")) {
            // Goto statement
            Node gotoStatementNode = new Node("goto_statement");
            currentNode.addChild(gotoStatementNode); // Add goto statement node to the current node

            if (!match(TokenType.IDENTIFIER)) {
                reportError("Expected identifier after 'goto'");
                return false;
            }

            // Add identifier node to the goto statement node
            Node identifierNode = new Node(tokens.get(currentTokenIndex - 1));
            gotoStatementNode.addChild(identifierNode);

            if (!match(";")) {
                reportError("Expected ';' after goto statement");
                return false;
            }

            return true;
        } else if (match("continue") || match("break")) {
            // Continue or break statement
            Node jumpStatementNode = new Node(tokens.get(currentTokenIndex - 1).getValue() + "_statement");
            currentNode.addChild(jumpStatementNode); // Add jump statement node to the current node

            if (!match(";")) {
                reportError("Expected ';' after " + tokens.get(currentTokenIndex - 1).getValue() + " statement");
                return false;
            }

            return true;
        } else if (match("return")) {
            // Return statement
            Node returnStatementNode = new Node("return_statement");
            currentNode.addChild(returnStatementNode); // Add return statement node to the current node

            // Parse expression if present
            if (peek().getType().equals(";")) {
                Node expressionNode = new Node("expression");
                if (!parseExpression()) {
                    reportError("Invalid expression in return statement");
                    return false;
                }
                returnStatementNode.addChild(expressionNode);
            }

            if (!match(";")) {
                reportError("Expected ';' after return statement");
                return false;
            }

            return true;
        } else {
            reportError("Expected jump statement (goto, continue, break, or return)");
            return false;
        }
    }

    private Tokenn peek() {
        int nextIndex = currentTokenIndex + 1;
        if (nextIndex < tokens.size()) {
            return tokens.get(nextIndex);
        }
        return null; // Return null if there are no more tokens to peek
    }


    private boolean parseSelectionStatement() {
        if (match("if")) {
            Node ifStatementNode = new Node("if_statement");
            currentNode.addChild(ifStatementNode); // Add if statement node to the current node

            if (!match("(")) {
                reportError("Expected '(' after 'if'");
                return false;
            }

            // Parse condition expression
            Node conditionNode = new Node("condition");
            if (!parseExpression()) {
                reportError("Invalid condition expression in if statement");
                return false;
            }
            ifStatementNode.addChild(conditionNode);

            if (!match(")")) {
                reportError("Expected ')' after condition expression in if statement");
                return false;
            }

            // Parse body of if statement
            Node ifBodyNode = new Node("if_body");
            if (!parseStatement()) {
                reportError("Invalid body of if statement");
                return false;
            }
            ifStatementNode.addChild(ifBodyNode);

            // Check for optional else if and else statements
            if (match("else")) {
                if (match("if")) {
                    // Parse else if statement recursively
                    return parseSelectionStatement();
                } else {
                    // Parse else statement
                    Node elseStatementNode = new Node("else_statement");
                    if (!parseStatement()) {
                        reportError("Invalid body of else statement");
                        return false;
                    }
                    ifStatementNode.addChild(elseStatementNode);
                }
            }

            return true;
        } else {
            reportError("Expected 'if' to start selection statement");
            return false;
        }
    }

    private boolean parseExpressionStatement() {
        Node expressionStatementNode = new Node("expression_statement");
        if (parseExpression()) {
            match(";"); // Consume optional semicolon
        } else {
            reportError("Invalid expression in expression statement");
            return false;
        }
        currentNode.addChild(expressionStatementNode); // Add expression statement node to the current node
        return true;
    }

    private boolean parseExpression() {
        Node expressionNode = new Node("expression");
        if (parseAssignmentExpression()) {
            while (match(",")) {
                if (!parseAssignmentExpression()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        currentNode.addChild(expressionNode); // Add expression node to the current node
        return true;
    }

    private boolean parseDeclaration() {
        Node declarationNode = new Node("declaration");
        if (!parseTypeSpecifier()) {
            reportError("Invalid type specifier in declaration");
            return false;
        }
        if (!match(TokenType.IDENTIFIER, declarationNode) || !parseDeclaratorList() || !match(";", declarationNode)) {
            reportError("Invalid declarator or missing semicolon in declaration");
            return false;
        }
        currentNode.addChild(declarationNode); // Add the declaration node to the current node
        return true;
    }

    private boolean parseDeclaratorList() {
        Node declaratorListNode = new Node("declarator_list");
        if (!parseDeclarator()) {
            return false;
        }
        currentNode.addChild(declaratorListNode); // Add the declarator list node to the current node
        while (match(",")) {
            if (!parseDeclarator()) {
                return false;
            }
        }
        return true;
    }

    private boolean parseDeclarator() {
        Node declaratorNode = new Node("declarator");
        if (match("*")) {
            declaratorNode.addChild(new Node("*")); // Add the pointer node to the declarator node
        }
        if (!match(TokenType.IDENTIFIER, declaratorNode)) {
            reportError("Expected identifier in declarator");
            return false;
        }
        if (match("=")) {
            if (!parseInitializer()) {
                reportError("Invalid initializer in declarator");
                return false;
            }
        }
        currentNode.addChild(declaratorNode); // Add the declarator node to the current node
        return true;
    }

    private boolean parseInitializer() {
        Node initializerNode = new Node("initializer");
        if (!parseAssignmentExpression()) {
            reportError("Invalid initializer");
            return false;
        }
        currentNode.addChild(initializerNode); // Add the initializer node to the current node
        return true;
    }

    private boolean parseCompoundStatement() {
        Node compoundStatementNode = new Node("compound_statement");
        if (!match("{")) {
            reportError("Expected '{' to start compound statement");
            return false;
        }
        Node previousNode = currentNode; // Store the previous current node
        currentNode = compoundStatementNode; // Update the current node
        while (!match("}")) {
            if (!parseDeclaration() && !parseStatement()) {
                reportError("Invalid declaration or statement inside compound statement");
                return false;
            }
        }
        currentNode = previousNode; // Restore the parent node
        currentNode.addChild(compoundStatementNode); // Add the compound statement node to the parent node
        return true;
    }

    private boolean parsePointer() {
        Node pointerNode = new Node("pointer");
        if (match("*", pointerNode)) {
            while (parseTypeQualifier()) {
                // Parse zero or more type qualifiers
            }
            parsePointer(); // Parse optional pointer
            currentNode.addChild(pointerNode); // Add pointer node to the current node
            return true;
        }
        return false;
    }

    private boolean parseDirectDeclarator() {
        Node directDeclaratorNode = new Node("direct_declarator");
        if (match(TokenType.IDENTIFIER, directDeclaratorNode)) {
            currentNode.addChild(directDeclaratorNode); // Direct declarator is an identifier
            return true;
        } else if (match("(")) {
            if (!parseDeclarator()) {
                reportError("Invalid declarator inside parentheses");
                return false;
            }
            boolean isParenthesizedDeclarator = match(")", directDeclaratorNode); // Direct declarator is a parenthesized declarator
            if (isParenthesizedDeclarator) {
                currentNode.addChild(directDeclaratorNode); // Add direct declarator node to the current node
            }
            return isParenthesizedDeclarator;
        } else if (parseDirectDeclarator()) {
            if (match("[")) {
                Node constantExpressionNode = new Node("constant_expression");
                if (!parseConstantExpression()) {
                    reportError("Invalid constant expression inside array declaration");
                    return false;
                }
                directDeclaratorNode.addChild(constantExpressionNode); // Add constant expression node
                boolean isArray = match("]", directDeclaratorNode); // Direct declarator is an array
                if (isArray) {
                    currentNode.addChild(directDeclaratorNode); // Add direct declarator node to the current node
                }
                return isArray;
            } else if (match("(")) {
                if (match(")", directDeclaratorNode)) {
                    currentNode.addChild(directDeclaratorNode); // Direct declarator is a function with no parameters
                    return true;
                }
                Node parameterList = new Node("parameter_list");
                if (!parseIdentifierList()) {
                    reportError("Invalid parameter list in function declaration");
                    return false;
                }
                directDeclaratorNode.addChild(parameterList); // Add parameter list node
                boolean isFunctionWithParameters = match(")", directDeclaratorNode); // Direct declarator is a function with parameters
                if (isFunctionWithParameters) {
                    currentNode.addChild(directDeclaratorNode); // Add direct declarator node to the current node
                }
                return isFunctionWithParameters;
            }
        }
        reportError("Invalid direct declarator");
        return false;
    }


    private boolean parseConstantExpression() {
        Node constantExpressionNode = new Node("constant_expression");

        // Check if the current token represents a constant
        TokenType tokenType = tokens.get(currentTokenIndex).getType();
        if (tokenType == TokenType.DECIMAL ||
                tokenType == TokenType.OCTAL ||
                tokenType == TokenType.BINARY ||
                tokenType == TokenType.HEX ||
                tokenType == TokenType.FLOAT ||
                tokenType == TokenType.STRING ||
                tokenType == TokenType.Character) {
            // Create a node for the constant and add it to the constant expression node
            Node constantNode = new Node(tokens.get(currentTokenIndex).getValue());
            constantExpressionNode.addChild(constantNode);
            currentTokenIndex++; // Move to the next token
            currentNode.addChild(constantExpressionNode); // Add constant expression node to the current node
            return true; // Parsing successful
        } else {
            reportError("Invalid constant expression");
            return false; // Parsing failed
        }
    }


    private boolean parseIdentifierList() {
        Node identifierListNode = new Node("identifier_list");
        if (!match(TokenType.IDENTIFIER, identifierListNode)) {
            reportError("Expected identifier");
            return false;
        }
        while (match(",")) {
            if (!match(TokenType.IDENTIFIER, identifierListNode)) {
                reportError("Expected identifier after comma");
                return false;
            }
        }
        currentNode.addChild(identifierListNode); // Add identifier list node to the current node
        return true;
    }

    private boolean parseTypeSpecifier() {
        Node typeSpecifierNode = new Node("type_specifier");
        if (match(TokenType.KEYWORD, typeSpecifierNode)) {
            currentNode.addChild(typeSpecifierNode); // Add type specifier node to the current node
            return true;
        } else if (parseStructOrUnionSpecifier() || parseEnumSpecifier() || match(TokenType.IDENTIFIER, typeSpecifierNode)) {
            currentNode.addChild(typeSpecifierNode); // Add type specifier node to the current node
            return true;
        }
        reportError("Invalid type specifier");
        return false;
    }


    private boolean parseStructOrUnionSpecifier() {
        Node structOrUnionSpecifierNode = new Node("struct_or_union_specifier");
        if (!match("struct", structOrUnionSpecifierNode) && !match("union", structOrUnionSpecifierNode)) {
            //reportError("Expected 'struct' or 'union'");
            return false;
        }
        if (match(TokenType.IDENTIFIER, structOrUnionSpecifierNode)) {
            // Add identifier node if present
            structOrUnionSpecifierNode.addChild(new Node(tokens.get(currentTokenIndex - 1).getValue()));
        }
        currentNode.addChild(structOrUnionSpecifierNode); // Add struct or union specifier node to the current node
        if (!match("{", structOrUnionSpecifierNode)) {
            reportError("Expected '{' after struct or union specifier");
            return false;
        }
        Node structDeclarationListNode = new Node("struct_declaration_list");
        if (!parseStructDeclarationList()) {
            return false;
        }
        structOrUnionSpecifierNode.addChild(structDeclarationListNode); // Add struct declaration list node
        if (!match("}", structOrUnionSpecifierNode)) {
            reportError("Expected '}' after struct or union declaration list");
            return false;
        }
        return true;
    }

    private boolean parseStructDeclarationList() {
        Node structDeclarationListNode = new Node("struct_declaration_list");
        while (true) {
            Node structDeclarationNode = new Node("struct_declaration");
            if (!parseSpecifierQualifierList()) {
                reportError("Invalid specifier qualifier list in struct declaration");
                return false;
            }
            if (!parseStructDeclaratorList()) {
                reportError("Invalid struct declarator list in struct declaration");
                return false;
            }
            structDeclarationListNode.addChild(structDeclarationNode); // Add struct declaration node
            if (!match(";")) {
                reportError("Expected ';' after struct declaration");
                return false;
            }
            if (match(";")) {
                break; // End of struct declaration list
            }
        }
        currentNode.addChild(structDeclarationListNode); // Add struct declaration list node to the current node
        return true;
    }

    private boolean parseStructDeclaration() {
        Node structDeclarationNode = new Node("struct_declaration");
        if (!match(";")) {
            reportError("Expected ';' in struct declaration");
            return false;
        }
        currentNode.addChild(structDeclarationNode); // Add struct declaration node to the current node
        return true;
    }

    private boolean parseStructDeclaratorList() {
        Node structDeclaratorListNode = new Node("struct_declarator_list");
        if (!parseStructDeclarator()) {
            reportError("Invalid struct declarator in struct declarator list");
            return false;
        }
        currentNode.addChild(structDeclaratorListNode); // Add struct declarator list node to the current node
        while (match(",")) {
            if (!parseStructDeclarator()) {
                reportError("Invalid struct declarator in struct declarator list");
                return false;
            }
        }
        return true;
    }

    private boolean parseSpecifierQualifierList() {
        Node specifierQualifierListNode = new Node("specifier_qualifier_list");
        if (parseTypeSpecifier() || parseTypeQualifier()) {
            if (parseSpecifierQualifierList()) {
                currentNode.addChild(specifierQualifierListNode); // Add specifier qualifier list node to the current node
                return true;
            }
            return true; // Type specifier/qualifier without a specifier qualifier list
        }
        reportError("Invalid specifier qualifier list");
        return false;
    }

    private boolean parseStructDeclarator() {
        Node structDeclaratorNode = new Node("struct_declarator");
        if (parseDeclarator()) {
            if (match(":")) {
                Node constantExpressionNode = new Node("constant_expression");
                if (!parseConstantExpression()) {
                    reportError("Invalid constant expression in struct declarator");
                    return false;
                }
                structDeclaratorNode.addChild(constantExpressionNode); // Add constant expression node
            }
            currentNode.addChild(structDeclaratorNode); // Add struct declarator node to the current node
            return true;
        }
        reportError("Invalid struct declarator");
        return false;
    }

    private boolean parseTypeQualifier() {
        if (match("const") || match("volatile")) {
            currentNode.addChild(new Node(tokens.get(currentTokenIndex - 1).getValue())); // Add type qualifier node to the current node
            return true;
        }
        reportError("Expected 'const' or 'volatile' as type qualifier");
        return false;
    }

    private boolean parseEnumSpecifier() {
        Node enumSpecifierNode = new Node("enum_specifier");
        if (!match("enum", enumSpecifierNode)) {
            //reportError("Expected 'enum' in enum specifier");
            return false;
        }
        if (match(TokenType.IDENTIFIER, enumSpecifierNode)) {
            enumSpecifierNode.addChild(new Node(tokens.get(currentTokenIndex - 1).getValue())); // Add identifier node if present
        }
        currentNode.addChild(enumSpecifierNode); // Add enum specifier node to the current node
        if (!match("{")) {
            reportError("Expected '{' after enum specifier");
            return false;
        }
        Node enumeratorListNode = new Node("enumerator_list");
        if (!parseEnumeratorList()) {
            return false;
        }
        enumSpecifierNode.addChild(enumeratorListNode); // Add enumerator list node
        if (!match("}")) {
            reportError("Expected '}' after enum declaration");
            return false;
        }
        return true;
    }

    private boolean parseEnumeratorList() {
        Node enumeratorListNode = new Node("enumerator_list");
        Node enumeratorNode = new Node("enumerator");
        if (!parseEnumerator()) {
            return false;
        }
        enumeratorListNode.addChild(enumeratorNode); // Add enumerator node
        while (match(",")) {
            if (!parseEnumerator()) {
                return false;
            }
        }
        currentNode.addChild(enumeratorListNode); // Add enumerator list node to the current node
        return true;
    }

    private boolean parseEnumerator() {
        Node enumeratorNode = new Node("enumerator");
        if (!match(TokenType.IDENTIFIER, enumeratorNode)) {
            reportError("Expected identifier in enumerator declaration");
            return false;
        }
        if (match("=")) {
            Node constantExpressionNode = new Node("constant_expression");
            if (!parseConstantExpression()) {
                reportError("Expected constant expression after '=' in enumerator declaration");
                return false;
            }
            enumeratorNode.addChild(constantExpressionNode); // Add constant expression node
        }
        currentNode.addChild(enumeratorNode); // Add enumerator node to the current node
        return true;
    }

    private boolean parseInitializerList() {
        Node initializerListNode = new Node("initializer_list");
        Node initializerNode = new Node("initializer");
        if (!parseInitializer()) {
            reportError("Invalid initializer in initializer list");
            return false;
        }
        initializerListNode.addChild(initializerNode); // Add initializer node
        while (match(",")) {
            if (!parseInitializer()) {
                reportError("Invalid initializer in initializer list");
                return false;
            }
        }
        currentNode.addChild(initializerListNode); // Add initializer list node to the current node
        return true;
    }
    private boolean parseParameterDeclaration() {
        Node parameterNode = new Node("parameter_declaration");
        if (!parseTypeSpecifier() || !match(TokenType.IDENTIFIER, parameterNode)) {
            reportError("Invalid parameter declaration");
            return false;
        }
        currentNode.addChild(parameterNode); // Add the parameter node to the current node
        return true;
    }

    private boolean parseFunctionDeclaration() {
        Node functionDeclarationNode = new Node("function_declaration");
        int startIndex = currentTokenIndex; // Record the starting index of the function declaration
        if (!parseTypeSpecifier() || !match(TokenType.IDENTIFIER, functionDeclarationNode) || !match("(", functionDeclarationNode)) {
            reportError("Invalid function declaration");
            currentTokenIndex = startIndex; // Reset the current token index if parsing fails
            return false;
        }
        // Parse parameter list if present
        if (!match(")")) {
            if (!parseParameterList()) {
                reportError("Invalid parameter list in function declaration");
                currentTokenIndex = startIndex; // Reset the current token index if parsing fails
                return false;
            }
            if (!match(")")) {
                reportError("Expected ')' after parameter list in function declaration");
                currentTokenIndex = startIndex; // Reset the current token index if parsing fails
                return false;
            }
        }
        // Parse compound statement
        if (!parseCompoundStatement()) {
            reportError("Invalid compound statement in function declaration");
            currentTokenIndex = startIndex; // Reset the current token index if parsing fails
            return false;
        }
        currentNode.addChild(functionDeclarationNode); // Add the function declaration node to the current node
        return true;
    }

    private boolean parseParameterList() {
        // Create a node for the parameter list
        Node parameterListNode = new Node("parameter_list");

        // Add the parameter list node to the current node
        currentNode.addChild(parameterListNode);

        // Parse the first parameter declaration, if present
        if (!parseParameterDeclaration()) {
            reportError("Invalid parameter list: missing or malformed parameter declaration");
            return false;
        }

        // Parse additional parameter declarations separated by commas
        while (match(",")) {
            // Move to the next token after the comma

            // Parse the next parameter declaration
            if (!parseParameterDeclaration()) {
                reportError("Invalid parameter list: missing or malformed parameter declaration after comma");
                return false;
            }
        }

        return true;
    }


    private boolean parseForStatement() {
        Node forStatementNode = new Node("for_statement");

        // Check if the current token is '('
        if (!match("(")) {
            reportError("Expected '(' after 'for'");
            return false;
        }

        // Parse initialization expression
        Node initializationExpressionNode = new Node("initialization_expression");
        if (!parseExpression()) {
            reportError("Invalid initialization expression in for statement");
            return false;
        }
        currentNode.addChild(initializationExpressionNode); // Add initialization expression to the current node

        // Check if the current token is ';'
        if (!match(";")) {
            reportError("Expected ';' after initialization expression");
            return false;
        }

        // Parse condition expression
        Node conditionExpressionNode = new Node("condition_expression");
        if (!parseExpression()) {
            reportError("Invalid condition expression in for statement");
            return false;
        }
        currentNode.addChild(conditionExpressionNode); // Add condition expression to the current node

        // Check if the current token is ';'
        if (!match(";")) {
            reportError("Expected ';' after condition expression");
            return false;
        }

        // Parse update expression
        Node updateExpressionNode = new Node("update_expression");
        if (!parseExpression()) {
            reportError("Invalid update expression in for statement");
            return false;
        }
        currentNode.addChild(updateExpressionNode); // Add update expression to the current node

        // Check if the current token is ')'
        if (!match(")")) {
            reportError("Expected ')' after update expression");
            return false;
        }

        // Parse loop body
        if (!parseBlock()) {
            reportError("Invalid block in for statement");
            return false;
        }

        currentNode.addChild(forStatementNode); // Add for statement node to the current node
        return true;
    }

    private boolean parseBlock() {
        Node blockNode = new Node("block");

        // Check if the current token is '{'
        if (!match("{")) {
            reportError("Expected '{' to start a block");
            return false;
        }

        // Parse statements inside the block until '}'
        while (!tokens.get(currentTokenIndex).getValue().equals("}")) {
            if (!parseStatement()) {
                reportError("Invalid statement inside block");
                return false;
            }
        }

        // Check if the current token is '}'
        if (!match("}")) {
            reportError("Expected '}' to end a block");
            return false;
        }

        currentNode.addChild(blockNode); // Add block node to the current node
        return true;
    }

    private boolean parseWhileStatement() {
        if (!match("(")) {
            reportError("Expected '(' after 'while'");
            return false;
        }

        // Create a node for the while statement
        Node whileStatementNode = new Node("while_statement");

        // Add the while statement node as a child of the current node
        currentNode.addChild(whileStatementNode);

        // Move the current node pointer to the while statement node
        currentNode = whileStatementNode;

        // Parse condition expression
        Node conditionExpressionNode = new Node("condition_expression");
        if (!parseExpression()) {
            reportError("Invalid condition expression in while statement");
            return false;
        }
        // Add the condition expression node as a child of the while statement node
        whileStatementNode.addChild(conditionExpressionNode);

        if (!match(")")) {
            reportError("Expected ')' after condition expression");
            return false;
        }

        // Parse loop body
        if (!parseBlock()) {
            return false;
        }

        // Restore the current node pointer to the parent node
        currentNode = currentNode.getParent();

        return true;
    }

    private boolean parseDoWhileStatement() {
        // Create a node for the do-while statement
        Node doWhileStatementNode = new Node("do_while_statement");

        // Add the do-while statement node as a child of the current node
        currentNode.addChild(doWhileStatementNode);

        // Move the current node pointer to the do-while statement node
        currentNode = doWhileStatementNode;

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
        Node conditionExpressionNode = new Node("condition_expression");
        if (!parseExpression()) {
            reportError("Invalid condition expression in do-while statement");
            return false;
        }
        // Add the condition expression node as a child of the do-while statement node
        doWhileStatementNode.addChild(conditionExpressionNode);

        if (!match(")")) {
            reportError("Expected ')' after condition expression");
            return false;
        }

        if (!match(";")) {
            reportError("Expected ';' after do-while statement");
            return false;
        }

        // Restore the current node pointer to the parent node
        currentNode = currentNode.getParent();

        return true;
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

    private boolean match(TokenType expectedTokenType) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getType() == expectedTokenType) {
            currentTokenIndex++;
            return true;
        }
        return false;
    }

    private void reportError(String message) {
        System.out.println("Error: " + message + tokens.get(currentTokenIndex) + currentTokenIndex);
    }

    private boolean match(TokenType expectedTokenType, Node parentNode) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getType() == expectedTokenType) {
            Node tokenNode = new Node(tokens.get(currentTokenIndex).getValue()); // Create a node for the token
            parentNode.addChild(tokenNode); // Add the token node to the parent node
            currentTokenIndex++;
            return true;
        }
        return false;
    }

    private boolean match(String expectedTokenType, Node parentNode) {
        if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getValue().equals(expectedTokenType) ) {
            Node tokenNode = new Node(tokens.get(currentTokenIndex).getValue()); // Create a node for the token
            parentNode.addChild(tokenNode); // Add the token node to the parent node
            currentTokenIndex++;
            return true;
        }
        return false;
    }


    private boolean is_a_type(String type){
        for( String str : dataTypes ){
            if( str.equals(type) )
                return true;
        }
        return false;
    }

    private boolean isConstant(Tokenn token) {
        TokenType type = token.getType();
        return type == TokenType.DECIMAL ||
                type == TokenType.OCTAL ||
                type == TokenType.BINARY ||
                type == TokenType.HEX ||
                type == TokenType.FLOAT ||
                type == TokenType.STRING ||
                type == TokenType.Character;
    }





}
