package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
        p.parse();
    }


}