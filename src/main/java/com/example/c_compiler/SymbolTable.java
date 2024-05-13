package com.example.c_compiler;

import java.util.*;

class SymbolTable {
    private Deque<Map<String, Tokenn>> scopeStack;
    private int current_scope_level;

    private List<LinkedList<Map<String, Tokenn>>> allScopes;

    public Deque<Map<String, Tokenn>> getScopeStack() {
        return scopeStack;
    }

    public void setScopeStack(Deque<Map<String, Tokenn>> scopeStack) {
        this.scopeStack = scopeStack;
    }

    public int getCurrent_scope_level() {
        return current_scope_level;
    }

    public void setCurrent_scope_level(int current_scope_level) {
        this.current_scope_level = current_scope_level;
    }

    public List<LinkedList<Map<String, Tokenn>>> getAllScopes() {
        return allScopes;
    }

    public void setAllScopes(List<LinkedList<Map<String, Tokenn>>> allScopes) {
        this.allScopes = allScopes;
    }


    public SymbolTable() {
        allScopes = new ArrayList<>();
        allScopes.add(new LinkedList<>());
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

    public boolean addSymbol(String name, Tokenn token) {
        Map<String, Tokenn> currentScope = scopeStack.peek();
        if (currentScope.containsKey(name)) {
            return false;
        } else {
            currentScope.put(name, token);
            return true;
        }
    }

    public void display() {
        // Add the level 0 scope to allScopes
        if( !scopeStack.isEmpty() )
            allScopes.get(0).add(new HashMap<>(scopeStack.peek()));
        System.out.println("---------------------------- Symbol Table ----------------------------");
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < allScopes.size(); i++) {
            LinkedList<Map<String, Tokenn>> scopes = allScopes.get(i);
            for (Map<String, Tokenn> scope : scopes) {
                if( !scope.isEmpty() )
                    displayScope(i, scope);
            }
        }
    }

    private void displayScope(int scopeIndex, Map<String, Tokenn> scopeSymbols) {
        System.out.println("----------------------- Scope Level " + scopeIndex + " ------------------------");
        for (Map.Entry<String, Tokenn> entry : scopeSymbols.entrySet()) {
            System.out.println("Identifier: " + entry.getKey() + ", Token type: " + entry.getValue().getId_type());
        }
        System.out.println("-----------------------------------------------------------");
    }
}
