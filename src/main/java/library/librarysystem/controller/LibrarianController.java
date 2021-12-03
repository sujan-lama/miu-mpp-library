package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.Start;

import java.io.IOException;

public class LibrarianController extends Stage {

    public static String currentMemberId = null;
    public static String currentMemberName = null;


    @FXML
    private TextField memberField;

    @FXML
    private TextField isbnField;

    @FXML
    private Label messageBar;

    @FXML
    public void handleSearchButtonAction() {
        String memberId = memberField.getText();
        String isbn = isbnField.getText();
        ControllerInterface c = new SystemController();
        currentMemberId = memberId;
        currentMemberName = c.getMemberById(memberId) != null ?
                c.getMemberById(memberId).getFirstName() + " "
                        + c.getMemberById(memberId).getLastName() : "";
        if (isBookAvailable(memberId, isbn, c)) return;
        c.createCheckOutRecordEntry(memberId, isbn);
        showTable();

    }

    private boolean isBookAvailable(String memberId, String isbn, ControllerInterface c) {
        if (!c.isBookAvailable(memberId, isbn)) {
            messageBar.setTextFill(Start.Colors.red);
            messageBar.setText("Item is not available.");
            return true;
        }
        return false;
    }

    @FXML
    public void handleViewButtonAction() {
        String memberId = memberField.getText();
        String isbn = isbnField.getText();
        ControllerInterface c = new SystemController();
        currentMemberId = memberId;
        LibraryMember libraryMember = c.getMemberById(memberId);
        if (libraryMember == null || libraryMember.getCheckoutRecord() == null) {
            messageBar.setTextFill(Start.Colors.red);
            messageBar.setText("Item is not available.");
        } else {
            currentMemberName =
                    c.getMemberById(memberId).getFirstName() + " "
                            + c.getMemberById(memberId).getLastName();
            showTable();
        }
    }

    private void showTable() {
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

    @FXML
    public void onBackPressed() {
        Start.hideAllWindows();
        Start.primStage().show();
        hide();
    }
}
