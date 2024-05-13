package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.LinkedList;
import java.util.Map;


public class SymbolTableController {

    @FXML
    private TreeView<String> treeView;

    public void initialize(SymbolTable symbolTable) {
        // Create the root item for the tree
        TreeItem<String> rootItem = new TreeItem<>("Symbol Table");
        rootItem.setExpanded(true); // Expand the root item by default

        // Add each scope as a child item to the root
        int scopeLevel = 0;
        for (LinkedList<Map<String, Tokenn>> scopeList : symbolTable.getAllScopes()) {
            TreeItem<String> scopeItem = new TreeItem<>("Scope Level " + scopeLevel);
            for (Map<String, Tokenn> scope : scopeList) {
                for (Map.Entry<String, Tokenn> entry : scope.entrySet()) {
                    // Add each symbol entry as a child item to the scope
                    TreeItem<String> symbolItem = new TreeItem<>(entry.getKey() + " : " + entry.getValue().getType());
                    scopeItem.getChildren().add(symbolItem);
                }
            }
            rootItem.getChildren().add(scopeItem);
            scopeLevel++;
        }

        // Set the root item as the root of the tree view
        treeView.setRoot(rootItem);
    }
}
