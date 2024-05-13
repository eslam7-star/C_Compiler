module com.example.c_compiler {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.abego.treelayout.core;
    requires java.desktop;
    requires org.antlr.antlr4.runtime;


    opens com.example.c_compiler to javafx.fxml;
    exports com.example.c_compiler;
}