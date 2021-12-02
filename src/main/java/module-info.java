module library.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;


//    opens library.librarysystem to javafx.fxml;
    exports library.librarysystem.ui;
    opens library.librarysystem.ui to javafx.fxml;
    opens library.librarysystem.controller to javafx.fxml;
}