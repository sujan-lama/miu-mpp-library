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
import library.librarysystem.ui.AddCopyWindow;
import library.librarysystem.ui.Start;
import library.librarysystem.utils.TextFieldUtils;

import java.util.Optional;

public class AddCopyController extends Stage {

    @FXML
    public TextField numCopy;
    @FXML
    public Label message;
    private Book book;

    public void setBook(Book book) {
        this.book = book;
        numCopy.setTextFormatter(TextFieldUtils.integerTextFormatter());

    }

    @FXML
    public void addCopy(ActionEvent event) {
        if (numCopy.getText().isEmpty()) {
            message.setText("Please type number of copies to add");
            return;
        }
        message.setText("");
        int numOfCopies = Integer.parseInt(numCopy.getText());
        makeBookCopy(numOfCopies);
    }

    private void makeBookCopy(int numOfCopies) {
        book.addCopy(book.getIsbn(), numOfCopies);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to add " + numOfCopies + " copies to " + book.getTitle() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ControllerInterface ci = new SystemController();
            Book updateBook = ci.getBooksById(book.getIsbn());
            AddCopyWindow.INSTANCE.updateBook(updateBook);
        }
    }
}
