package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.LoginWindow;

import java.io.IOException;

public class AdminController extends Stage {


    @FXML
    public Button logout;

    @FXML
    public void addLibraryMember(ActionEvent event) {
        AdminWindow.INSTANCE.gotoAddLibraryMember();
    }


    @FXML
    public void viewLibraryMember(ActionEvent event) {
        AdminWindow.INSTANCE.gotoViewLibraryMember();
    }

    @FXML
    public void addBooks(ActionEvent event) {
        AdminWindow.INSTANCE.gotoAddBooks();
    }

    @FXML
    public void viewBooks(ActionEvent event) {
        AdminWindow.INSTANCE.gotoViewBooks();
    }

    @FXML
    public void searchBooks(ActionEvent event) {
        AdminWindow.INSTANCE.gotoSearchBooks();
    }

    @FXML
    public void viewOverdueBooks(ActionEvent event) {
        AdminWindow.INSTANCE.gotoOverdueBooks();
    }

    @FXML
    public void logout(ActionEvent event) {
        AdminWindow.INSTANCE.logout();
    }

    public void setFromSuperAdmin() {
        logout.setText("<- Go back");
    }
}
