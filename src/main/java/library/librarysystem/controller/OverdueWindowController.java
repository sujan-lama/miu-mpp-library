package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.dataaccess.DataAccess;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.*;

import java.io.IOException;

public class OverdueWindowController extends Stage {

    @FXML
    private Label messageBar;

    @FXML
    private TextField searchField;

    public static String currentIsbn = null;


    @FXML
    public void searchOverdue() {
        currentIsbn = searchField.getText();
        DataAccess dataAccess = new DataAccessFacade();
        if (dataAccess.checkOverdueRecords(currentIsbn)) {
            showTable();
        } else {
            messageBar.setTextFill(Start.Colors.red);
            messageBar.setText("No overdue book copies found.");
        }
    }

    private void showTable() {
        messageBar.setText("");
        try {
//            OverdueWindow.INSTANCE.hide();
            OverdueTableWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
