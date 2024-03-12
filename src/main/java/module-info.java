module com.example.c_compiler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.c_compiler to javafx.fxml;
    exports com.example.c_compiler;
}