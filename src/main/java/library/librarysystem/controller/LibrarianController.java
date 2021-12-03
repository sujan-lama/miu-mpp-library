package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.Start;

import java.io.IOException;

public class LibrarianController extends Stage {

    public static String currentMemberId = null;


    @FXML
    private TextField memberField;

    @FXML
    private TextField isbnField;

    @FXML
    private Label messageBar;

    @FXML
    public void handleSearchButtonAction() {
        String memberId = memberField.getText();
        currentMemberId = memberId;
        String isbn = isbnField.getText();
        ControllerInterface c = new SystemController();
        if (!c.isBookAvailable(memberId, isbn)) {
            messageBar.setTextFill(Start.Colors.red);
            messageBar.setText("Item is not available.");
        } else {
            c.createCheckOutRecordEntry(memberId, isbn);
            messageBar.setText("");

            FXMLLoader fxmlLoader = new FXMLLoader(
                    Start.class.getResource("checkoutRecordTable.fxml"));
            try {
                Start.hideAllWindows();
                Scene scene = new Scene(fxmlLoader.load(), 480, 360);
                scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
                setScene(scene);
                show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
