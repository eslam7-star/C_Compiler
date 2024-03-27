package com.example.c_compiler;

import java.util.*;

class SymbolTable {
    private Deque<Map<String, Token>> scopeStack;
    private int current_scope_level;
    private List<LinkedList<Map<String, Token>>> allScopes;

    public SymbolTable() {
        allScopes = new ArrayList<>();
        scopeStack = new ArrayDeque<>();
        scopeStack.push(new HashMap<>());
        this.current_scope_level = 0;
    }

    public void startScope() {
        scopeStack.push(new HashMap<>());
        current_scope_level++;
        // Ensure the allScopes list is large enough
        while (allScopes.size() <= current_scope_level) {
            allScopes.add(new LinkedList<>());
        }
    }

    public void endScope() {
        allScopes.get(current_scope_level).add(new HashMap<>(scopeStack.peek()));
        scopeStack.pop();
        current_scope_level--;
    }

    public boolean addSymbol(String name, Token token) {
        Map<String, Token> currentScope = scopeStack.peek();
        if (currentScope.containsKey(name)) {
            return false;
        } else {
            currentScope.put(name, token);
            return true;
        }
    }

    public void display() {
        // Add the level 0 scope to allScopes
        allScopes.get(0).add(new HashMap<>(scopeStack.peek()));
        System.out.println("---------------------------- Symbol Table ----------------------------");
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < allScopes.size(); i++) {
            LinkedList<Map<String, Token>> scopes = allScopes.get(i);
            for (Map<String, Token> scope : scopes) {
                if( !scope.isEmpty() )
                    displayScope(i, scope);
            }
        }
    }

    private void displayScope(int scopeIndex, Map<String, Token> scopeSymbols) {
        System.out.println("----------------------- Scope Level " + scopeIndex + " ------------------------");
        for (Map.Entry<String, Token> entry : scopeSymbols.entrySet()) {
            System.out.println("Identifier: " + entry.getKey() + ", Token type: " + entry.getValue().getId_type());
        }
        System.out.println("-----------------------------------------------------------");
    }
}
