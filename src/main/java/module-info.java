module com.example.notas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.notas to javafx.fxml;
    exports com.example.notas;
}