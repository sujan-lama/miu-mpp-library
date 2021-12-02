package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.LoginWindow;

import java.io.IOException;

public class AdminController extends Stage {


    @FXML
    public void addLibraryMember(ActionEvent event) {
        AdminWindow.INSTANCE.gotoAddLibraryMember();
    }


    @FXML
    public void viewLibraryMember(ActionEvent event) {AdminWindow.INSTANCE.gotoViewLibraryMember();}
    @FXML
    public void addBooks(ActionEvent event) {
        AdminWindow.INSTANCE.gotoAddBooks();
    }

    @FXML
    public void logout(ActionEvent event) {
        ControllerInterface controller = new SystemController();
        controller.logout();
        if (!LoginWindow.INSTANCE.isInitialized()) {
            try {
                LoginWindow.INSTANCE.init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        AdminWindow.INSTANCE.hide();
        LoginWindow.INSTANCE.show();
    }
}
