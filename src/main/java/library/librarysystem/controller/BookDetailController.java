package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.ui.AuthorDetailWindow;
import library.librarysystem.ui.BookDetailWindow;
import library.librarysystem.ui.ListBookWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDetailController extends Stage {


    @FXML
    private Label title;

    @FXML
    private Label maxCheckout;

    @FXML
    private ListView<String> authorList;

    private Book book;


    @FXML
    public void goBack() {
        BookDetailWindow.INSTANCE.goBack();
    }


    public void setData(Book book) {
        this.book = book;
        title.setText(book.getTitle());
        maxCheckout.setText(String.valueOf(book.getMaxCheckoutLength()));
        List<String> authorNames = new ArrayList<>();
        int i = 1;
        for (Author author : book.getAuthors()) {
            authorNames.add(i + ". " + author.getFirstName() + " " + author.getLastName());
            i++;
        }
        authorList.getItems().addAll(authorNames);
    }

    @FXML
    public void deleteBook() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setHeaderText("Delete " + book.getTitle().trim() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            ControllerInterface controller = new SystemController();
            controller.deleteBook(book);
            goBack();
        }

    }

    @FXML
    public void editBook() {
        BookDetailWindow.INSTANCE.hide();
        try {
            AddBookWindow.INSTANCE.init();
            AddBookWindow.INSTANCE.setDataAndShow(book);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
