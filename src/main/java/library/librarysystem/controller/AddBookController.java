package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AddAuthorWindow;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.ui.AuthorDetailWindow;
import library.librarysystem.utils.TextFieldUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddBookController extends Stage {

    @FXML
    private Label label;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField maxCheckout;

    @FXML
    private ListView<String> authorList;

    @FXML
    private Button submit;

    Alert alert = new Alert(Alert.AlertType.NONE);

    private Book book;
    private List<Author> authors = new ArrayList<>();

    @FXML
    public void goBack() {
        AddBookWindow.INSTANCE.goBack();
    }


    public void addBook(Book book, boolean update) {

        ControllerInterface controller = new SystemController();
        if (!update)
            controller.saveBook(book);
        else
            controller.editBook(book);
        AddBookWindow.INSTANCE.gotoBook(book);

    }

    @FXML
    public void submit(ActionEvent event) {
        String textIsbn = isbn.getText().trim();
        String textTitle = title.getText().trim();
        String textMaximumCheckout = maxCheckout.getText().trim();
        Book newBook = new Book(textIsbn, textTitle, Integer.parseInt(textMaximumCheckout), authors);
        boolean validate = validate(newBook);
        if (!validate) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Some fields are missing. Please check and try again.");
            alert.show();
            return;
        }

        if (book != null) {
            addBook(newBook, true);
            return;
        }
        boolean checkIdDifferent = checkIdDifferent(textIsbn);
        if (!checkIdDifferent) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Cannot have same isbn");
            alert.show();
            return;
        }
        addBook(newBook, false);
        clear();
        alert.close();
    }

    private boolean checkIdDifferent(String textId) {
        ControllerInterface ci = new SystemController();
        List<Book> books = ci.allBooks();
        books = books.stream().filter(book -> book.getIsbn().equals(textId)).toList();
        return books.size() == 0;
    }

    private boolean validate(Book newBook) {
        return !newBook.getIsbn().isEmpty() && !newBook.getTitle().isEmpty() && !authors.isEmpty();
    }

    private void clear() {
        isbn.setText("");
        title.setText("");
        maxCheckout.setText("");

    }


    public void setData(Book book) {
        this.book = book;
        maxCheckout.setTextFormatter(TextFieldUtils.integerTextFormatter());
        if (book == null) {
            label.setText("Add Book");
            submit.setText("Submit");
            isbn.setDisable(false);
            return;
        }
        authors.addAll(book.getAuthors());
        authorList.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        try {
                            AuthorDetailWindow.INSTANCE.init();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        AuthorDetailWindow.INSTANCE.setAuthor(authors.get(authorList.getSelectionModel().getSelectedIndices().get(0)));
                    }
                }
        );
        label.setText("Update Book");
        submit.setText("Update");

        isbn.setText(book.getIsbn());
        title.setText(book.getTitle());
        isbn.setDisable(true);
        maxCheckout.setText(String.valueOf(book.getMaxCheckoutLength()));
        List<String> authorNames = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            authorNames.add(author.getFirstName() + " " + author.getLastName());
        }
        authorList.getItems().addAll(authorNames);
    }

    public void addAuthor(Author author, Author previousAuthor) {
        if (previousAuthor != null) {
            authors.remove(previousAuthor);
            authorList.getItems().remove(previousAuthor.getFirstName() + " " + previousAuthor.getLastName());
        }
        authors.add(author);
        authorList.getItems().add(author.getFirstName() + " " + author.getLastName());
    }

    @FXML
    public void addAuthorButton(ActionEvent e) {
        try {
            AddAuthorWindow.INSTANCE.init();
            AddAuthorWindow.INSTANCE.setDataAndShow(null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAuthor(Author author) {
        authors.remove(author);
        authorList.getItems().remove(author.getFirstName() + " " + author.getLastName());
    }
}
