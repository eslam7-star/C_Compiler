package com.example.c_compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String,TokenType>symbols;

    public SymbolTable() {
        symbols = new HashMap<>();
    }
    public void addSymbol(String symbol, TokenType tokenType) {
        symbols.put(symbol, tokenType);
    }

    /*private List<Identifier> table;

    public SymbolTable() {
        this.table = new ArrayList<>();
    }

    public void addEntry(String name, String type) {

    }

    public Identifier getEntry(String name) {
        for (Identifier entry : table) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }*/
}
