package com.example.c_compiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class TokenListController {

    @FXML
    private ListView<String> tokenListView;

    public void initialize() {
        // Initialize the ListView
        tokenListView.setItems(FXCollections.observableArrayList());
    }

    public void setTokens(List<Tokenn> tokens) {
        ObservableList<String> tokenStrings = FXCollections.observableArrayList();
        // Convert tokens to string representations
        for (Tokenn token : tokens) {
            tokenStrings.add(token.toString());
        }
        // Set the tokens in the ListView
        tokenListView.setItems(tokenStrings);
    }
}
