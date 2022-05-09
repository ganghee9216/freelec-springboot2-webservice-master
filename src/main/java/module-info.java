module com.example.freelecspringboot2webservicemaster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.freelecspringboot2webservicemaster to javafx.fxml;
    exports com.example.freelecspringboot2webservicemaster;
}