package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.CheckoutBookWindow;
import library.librarysystem.ui.Start;

import java.util.Optional;

public class CheckoutBookController extends Stage {
    @FXML
    public Label title;
    @FXML
    public Label message;
    @FXML
    public TextField memberField;
    @FXML
    public TextField isbnField;

    private ControllerInterface ci = new SystemController();

    public void checkInBook(ActionEvent event) {
        String isbn = isbnField.getText();
        String memberId = memberField.getText();
        if (memberId.isEmpty() && isbn.isEmpty()) {
            message.setTextFill(Start.Colors.red);
            message.setText("Please enter memberId and ISBN");
            return;
        }
        if (memberId.isEmpty()) {
            message.setTextFill(Start.Colors.red);
            message.setText("Please enter memberId");
            return;
        }
        if (isbn.isEmpty()) {
            message.setTextFill(Start.Colors.red);
            message.setText("Please enter ISBN of book");
            return;
        }
        ControllerInterface c = new SystemController();
        if (isBookAvailable(memberId, isbn, c)) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText("Do you want to add checkout record?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            message.setTextFill(Start.Colors.green);
            message.setText("Checkout record successfully added");
            c.createCheckOutRecordEntry(memberId, isbn);
            Book updatedBook = ci.getBooksById(isbnField.getText());
            CheckoutBookWindow.INSTANCE.updateBook(updatedBook);
            clear();
        }
    }

    public void clear() {
        isbnField.clear();
        memberField.clear();
    }

    private boolean isBookAvailable(String memberId, String isbn, ControllerInterface c) {
        if (!c.isBookAvailable(memberId, isbn)) {
            message.setTextFill(Start.Colors.red);
            message.setText("Book is not available for checkout");
            return true;
        }
        return false;
    }
}
