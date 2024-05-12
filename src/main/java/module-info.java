module com.example.c_compiler {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.abego.treelayout.core;


    opens com.example.c_compiler to javafx.fxml;
    exports com.example.c_compiler;
}