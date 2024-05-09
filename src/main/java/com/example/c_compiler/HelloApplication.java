package com.example.c_compiler;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader_1 = new FXMLLoader(HelloApplication.class.getResource("compiler-view.fxml"));
        Scene scene = new Scene(fxmlLoader_1.load());
        stage.setScene(scene);
        stage.setTitle("C Compiler App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}