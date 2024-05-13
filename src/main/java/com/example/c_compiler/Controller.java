package com.example.c_compiler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.PrintStream;
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
        Lexer l = new Lexer(input, symbolTable);
        l.tokenize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tokens_list.fxml"));
        Parent r = loader.load();
        TokenListController controller = loader.getController();

        // Get the list of tokens (assuming you have it available)
        List<Tokenn> tokens = l.getTokens(); // Implement this method to retrieve tokens

        // Set the tokens in the controller
        controller.setTokens(tokens);

        // Create a new stage for the token list window
        Stage stage = new Stage();
        stage.setScene(new Scene(r));
        stage.setTitle("Token List");
        stage.show();


        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("symbol_table.fxml"));
        Parent r3 = loader2.load();
        SymbolTableController controller4 = loader2.getController();

        // Set the tokens in the controller
        controller4.initialize(symbolTable);

        // Create a new stage for the token list window
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(r3));
        stage2.setTitle("symbole table view");
        stage2.show();


        c_gLexer lexer = new c_gLexer(CharStreams.fromString(input));
        CommonTokenStream t = new CommonTokenStream(lexer);
        c_gParser pa = new c_gParser(t);
        pa.removeErrorListeners(); // Remove the default error listeners
        TextArea errorTextArea = new TextArea();
        pa.addErrorListener(new CustomErrorListener(errorTextArea)); // Add the custom error listener


        ParseTree parseTree = pa.program();


        Scene scen1e = new Scene(errorTextArea, 600, 400);

        Stage newWindow5 = new Stage();
        newWindow5.setTitle("errors Output");
        newWindow5.setScene(scen1e);

        newWindow5.show();

        TreeLayoutExample tree = new TreeLayoutExample(parseTree);
        tree.displayParseTree();




    }


}