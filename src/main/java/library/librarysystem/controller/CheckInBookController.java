package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.CheckInBookWindow;
import library.librarysystem.ui.CheckoutRecordTableWindow;

import java.io.IOException;

public class CheckInBookController extends Stage {
    @FXML
    public Label title;
    @FXML
    public Label message;
    @FXML
    public TextField isbn;

    private String memberId;

    public void checkInBook(ActionEvent event) {
        if (isbn.getText().isEmpty()) {
            message.setText("Please enter ISBN of book");
            return;
        }
        String isbnText = isbn.getText();
        ControllerInterface c = new SystemController();
        if (isBookAvailable(memberId, isbnText, c)) return;
        c.createCheckOutRecordEntry(memberId, isbnText);
        CheckInBookWindow.INSTANCE.hide();
        try {
            CheckoutRecordTableWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isBookAvailable(String memberId, String isbn, ControllerInterface c) {
        if (!c.isBookAvailable(memberId, isbn)) {
            message.setText("Book is not available for checkout");
            return true;
        }
        return false;
    }


    public void setData(String memberId, String name) {
        this.memberId = memberId;
        title.setText("Check in book for " + name);
    }
}
