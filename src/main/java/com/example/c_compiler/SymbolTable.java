package com.example.c_compiler;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


class SymbolTable {
    private Deque<Map<String, Token>> scopeStack;

    public SymbolTable() {
        scopeStack = new ArrayDeque<>();
        scopeStack.push(new HashMap<>());
    }

    public void startScope() {
        scopeStack.push(new HashMap<>());
    }

    public void endScope() {
        scopeStack.pop();
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


    public Token lookup(String name) {
        for (Map<String, Token> scope : scopeStack) {
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }

    public void display() {
        System.out.println("Symbol Table:");
        for (Map<String, Token> scope : scopeStack) {
            for (Map.Entry<String, Token> entry : scope.entrySet()) {
                System.out.println("Identifier: " + entry.getKey() + ", Token: " + entry);
            }
            System.out.println("-----");
        }
    }


    public Deque<Map<String, Token>> getScopeStack() {
        return scopeStack;
    }

    public void setScopeStack(Deque<Map<String, Token>> scopeStack) {
        this.scopeStack = scopeStack;
    }
}

