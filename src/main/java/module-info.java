module library.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires ascii.utf.themes;
    requires asciitable;

//    opens library.librarysystem to javafx.fxml;
    exports library.librarysystem.ui;
    exports library.librarysystem.business;
    opens library.librarysystem.ui to javafx.fxml;
    opens library.librarysystem.business to javafx.fxml;
    opens library.librarysystem.controller to javafx.fxml;
}