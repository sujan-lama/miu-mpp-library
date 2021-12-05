package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AddAuthorWindow;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.utils.TextFieldUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddBookController extends Stage {

    @FXML
    private Label label;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<Integer> maxCheckout;

    @FXML
    private ListView<String> authorList;

    @FXML
    private Button submit;

    Alert alert = new Alert(Alert.AlertType.NONE);

    private Book book;
    private List<Author> authors = new ArrayList<>();


    public void addBook(Book book, boolean update) {

        ControllerInterface controller = new SystemController();
        if (!update)
            controller.saveBook(book);
        else
            controller.editBook(book);
        AddBookWindow.INSTANCE.addoreditBookSuccess(book);
    }

    @FXML
    public void submit(ActionEvent event) {
        String textIsbn = isbn.getText().trim();
        String textTitle = title.getText().trim();
        int textMaximumCheckout = maxCheckout.getSelectionModel().getSelectedItem();
        Book newBook = new Book(textIsbn, textTitle, textMaximumCheckout, authors);
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

    }


    public void setData(Book book) {
        this.book = book;
        maxCheckout.getItems().add(7);
        maxCheckout.getItems().add(21);
        if (book == null) {
            label.setText("Add Book");
            submit.setText("Submit");
            maxCheckout.getSelectionModel().select(Integer.valueOf(7));
            isbn.setDisable(false);
            return;
        }
        authors.addAll(book.getAuthors());
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        edit.setOnAction(e -> {
            try {
                AddAuthorWindow.INSTANCE.init();
                AddAuthorWindow.INSTANCE.setDataAndShow(book.getAuthors().get(authorList.getSelectionModel().getSelectedIndex()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        delete.setOnAction(e -> {
            Author author = book.getAuthors().get(authorList.getSelectionModel().getSelectedIndex());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setHeaderText("Delete author: " + author.getName() + "?");
            alert.setContentText("");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                authors.remove(author);
                authorList.getItems().remove(author.getName());
            }
        });

        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(edit, delete);
        authorList.setContextMenu(contextMenu);

        label.setText("Update Book");
        submit.setText("Update");

        isbn.setText(book.getIsbn());
        title.setText(book.getTitle());
        isbn.setDisable(true);
        maxCheckout.getSelectionModel().select(Integer.valueOf(book.getMaxCheckoutLength()));
        List<String> authorNames = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            authorNames.add(author.getName());
        }
        authorList.getItems().addAll(authorNames);
    }

    public void addAuthor(Author author, Author previousAuthor) {
        if (previousAuthor != null) {
            authors.remove(previousAuthor);
            authorList.getItems().remove(previousAuthor.getName());
        }
        authors.add(author);
        authorList.getItems().add(author.getName());
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
        authorList.getItems().remove(author.getName());
    }
}
