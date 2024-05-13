package com.example.c_compiler;

import javafx.scene.control.TextArea;
import org.antlr.v4.runtime.*;

public class CustomErrorListener extends BaseErrorListener {
    private TextArea errorTextArea;

    public CustomErrorListener(TextArea errorTextArea) {
        this.errorTextArea = errorTextArea;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errorTextArea.appendText("line " + line + ":" + charPositionInLine + " " + msg + "\n");
    }
}
