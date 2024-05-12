package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SymbolTableController {

    @FXML
    private TableView<SymbolEntry> tableView;

    @FXML
    private TableColumn<SymbolEntry, String> nameColumn;

    @FXML
    private TableColumn<SymbolEntry, String> typeColumn;

    public void initialize() {
        // Set up cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Populate the table with symbol table data
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.startScope();
        symbolTable.addSymbol("x", new Tokenn("INT"));
        symbolTable.addSymbol("y", new Tokenn("FLOAT"));
        symbolTable.addSymbol("z", new Tokenn("CHAR"));
        symbolTable.endScope();

        // Display the symbol table in the TableView
        displaySymbolTable(symbolTable);
    }

    private void displaySymbolTable(SymbolTable symbolTable) {
        // Iterate through each scope and symbol entry in the symbol table
        for (int i = 0; i < symbolTable.getAllScopes().size(); i++) {
            for (Map.Entry<String, Tokenn> entry : symbolTable.getAllScopes().get(i).entrySet()) {
                // Add symbol entry to the TableView
                tableView.getItems().add(new SymbolEntry(entry.getKey(), entry.getValue().getId_type()));
            }
        }
    }
}
