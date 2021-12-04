package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.LibrarianWindow;
import library.librarysystem.ui.LoginWindow;
import library.librarysystem.ui.SuperAdminWindow;

import java.io.IOException;

public class SuperAdminController extends Stage {


    @FXML
    public void gotoAdminControl() {
        try {
            LoginWindow.INSTANCE.hide();
            AdminWindow.INSTANCE.setData(true);
            AdminWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gotoLibrarianControl() {
        try {
            LoginWindow.INSTANCE.hide();
            LibrarianWindow.INSTANCE.setFromSuperAdmin(true);
            LibrarianWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event) {
        SuperAdminWindow.INSTANCE.logout();
    }
}
