package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private Button button;

    @FXML
    public TextArea input_area;


    @FXML
    private void handleButtonClick() throws IOException {
        System.out.println("  .................... Tokenizing ............................. ");
        String input = input_area.getText();
        SymbolTable symbolTable = new SymbolTable();
        Lexer l = new Lexer(input,symbolTable);
        l.tokenize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tokens_list.fxml"));
        Parent root = loader.load();
        TokenListController controller = loader.getController();

        List<Token> tokens = l.getTokens();

        controller.setTokens(tokens);

        symbolTable.display();
        Parser p = new Parser(l.getTokens());
        p.parse();
    }


}