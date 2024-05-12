package com.example.c_compiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class TokenListController {

    @FXML
    private ListView<String> tokenListView;

    public void initialize() {
        // Initialize the ListView
        tokenListView.setItems(FXCollections.observableArrayList());
    }

    public void setTokens(List<Token> tokens) {
        ObservableList<String> tokenStrings = FXCollections.observableArrayList();
        // Convert tokens to string representations
        for (Token token : tokens) {
            tokenStrings.add(token.toString());
        }
        // Set the tokens in the ListView
        tokenListView.setItems(tokenStrings);
    }
}
