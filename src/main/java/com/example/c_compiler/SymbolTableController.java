package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.LinkedList;
import java.util.Map;


public class SymbolTableController {

    @FXML
    private TableView<Tokenn> tableView;

    @FXML
    private TableColumn<Tokenn, String> nameColumn;

    @FXML
    private TableColumn<Tokenn, String> typeColumn;

    @FXML
    private TableColumn<Tokenn, Integer> scopeColumn;


    public void initialize(SymbolTable symbolTable) {
        // Set up cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<Tokenn, String>("Id_value"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Tokenn, String>("type"));


        // Display the symbol table in the TableView
        displaySymbolTable(symbolTable);
    }


    private void displaySymbolTable(SymbolTable symbolTable) {
        int scopeLevel = 0;
        // Iterate through each scope in the symbol table
        for (LinkedList<Map<String, Tokenn>> scopeList : symbolTable.getAllScopes()) {
            // Iterate through each symbol entry in the current scope
            for (Map<String, Tokenn> scope : scopeList) {
                for (Map.Entry<String, Tokenn> entry : scope.entrySet()) {
                    // Add symbol entry to the TableView with scope level
                    tableView.getItems().add(entry.getValue());
                }
            }
            scopeLevel++;
        }
    }
}
