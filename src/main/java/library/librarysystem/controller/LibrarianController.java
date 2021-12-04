package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.CheckoutRecordTableWindow;
import library.librarysystem.ui.LibrarianWindow;

import java.io.IOException;
import java.util.Optional;

public class LibrarianController extends Stage {

    public static String currentMemberId = null;
    public static String currentMemberName = null;

    @FXML
    public Button logout;

    @FXML
    private TextField memberField;

    @FXML
    private TextField isbnField;

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    public void checkStatusAction() {
        String memberId = memberField.getText();
        String isbn = isbnField.getText();
        if (memberId.isEmpty() && isbn.isEmpty()) {
            showAlertDialog(alert, "Enter member id and ISBN number of book to view status");
            return;
        }
        if (isbn.isEmpty()) {
            showAlertDialog(alert, "Enter ISBN number");
            return;
        }
        if (memberId.isEmpty()) {
            showAlertDialog(alert, "Enter member id");
            return;
        }
        ControllerInterface c = new SystemController();
        currentMemberId = memberId;
        currentMemberName = c.getMemberById(memberId) != null ?
                c.getMemberById(memberId).getFirstName() + " "
                        + c.getMemberById(memberId).getLastName() : "";
        if (isBookAvailable(memberId, isbn, c)) return;

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Checkout is possible. Do you want to add a checkout record for this book?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            c.createCheckOutRecordEntry(memberId, isbn);
            showTable();
        }

    }

    private boolean isBookAvailable(String memberId, String isbn, ControllerInterface c) {
        if (!c.isBookAvailable(memberId, isbn)) {
            showAlertDialog(alert, "Book is not available for checkout");
            return true;
        }
        return false;
    }

    @FXML
    public void handleViewButtonAction() {
        String memberId = memberField.getText();
        if (memberId.isEmpty()) {
            showAlertDialog(alert, "Enter member id");
            return;
        }
        ControllerInterface c = new SystemController();
        currentMemberId = memberId;
        LibraryMember libraryMember = c.getMemberById(memberId);
        if (libraryMember == null) {
            showAlertDialog(alert, "No member id found for id " + memberId);
        } else {
            currentMemberName =
                    c.getMemberById(memberId).getFirstName() + " "
                            + c.getMemberById(memberId).getLastName();
            showTable();
        }
    }

    private void showAlertDialog(Alert alert, String contentText) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setHeaderText("");
        alert.setContentText(contentText);
        alert.show();
    }

    private void showTable() {
        try {
            LibrarianWindow.INSTANCE.hide();
            CheckoutRecordTableWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFromSuperAdmin() {
        logout.setText("<- Go back");
    }

    @FXML
    public void logout() {
        LibrarianWindow.INSTANCE.logOut();
    }
}
