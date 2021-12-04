package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.dataaccess.DataAccess;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.OverdueWindow;
import library.librarysystem.ui.Start;

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
        FXMLLoader fxmlLoader = new FXMLLoader(
                Start.class.getResource("overdueTable.fxml"));
        try {
            OverdueWindow.INSTANCE.hide();
            Scene scene = new Scene(fxmlLoader.load(), 480, 360);
            scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
            setScene(scene);
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack() {
        OverdueWindow.INSTANCE.hide();
        AdminWindow.INSTANCE.show();
    }
}
