package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Controller {

    @FXML
    private Button button;

    @FXML
    public TextArea input_area;


    @FXML
    private void handleButtonClick() {
        System.out.println("  .................... Tokenizing ............................. ");
        String input = input_area.getText();
        SymbolTable symbolTable = new SymbolTable();
        Lexer l = new Lexer(input,symbolTable);
        l.tokenize();
        for ( Token t : l.tokens){
            System.out.println(t);
        }
        symbolTable.display();
        Parser p = new Parser(l.getTokens());
        Node root = p.parse();
        p.traverse(root, 10);

        // Build the TreeView and add it to a new stage
        TreeItem<String> rootItem = p.buildTreeView(root);
        TreeView<String> treeView = new TreeView<>(rootItem);

        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle("Parse Tree");

        // Create a new scene with the TreeView and set it on the stage
        VBox vbox = new VBox(treeView);
        Scene newScene = new Scene(vbox, 600, 400);
        newStage.setScene(newScene);

        // Show the new stage
        newStage.show();
    }


}