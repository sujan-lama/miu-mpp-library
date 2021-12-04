package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.business.Book;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.AdminWindow;
import library.librarysystem.ui.SearchBooksWindow;
import library.librarysystem.ui.Start;

import java.io.IOException;
import java.util.List;

public class IsbnSearchController extends Stage {

    @FXML
    public Label isbnLabel;
    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label numCopiesLabel;
    @FXML
    public Button addCopyButton;
    @FXML
    private ComboBox<Integer> noOfBooksCombo;

    @FXML
    private TextField isbnSearchText;

    @FXML
    private Label bookStatus;

    @FXML
    private Text isbn;

    @FXML
    private Text bookTitle;

    @FXML
    private Text author;

    @FXML
    private Text numberOfCopies;

    @FXML
    private Label messageBar;

    private DataAccessFacade dataAccessFacade;

    @FXML
    public void onBackPressed() {
        SearchBooksWindow.INSTANCE.hide();
        try {
            AdminWindow.INSTANCE.init();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchBook(ActionEvent event) {
        dataAccessFacade = new DataAccessFacade();
        String isbn = isbnSearchText.getText().trim();
        if (!isbn.isBlank()) {
            boolean isBookAvailable = searchBookInDatabase(isbn);
            if (isBookAvailable) {
                Book book = dataAccessFacade.readBooksMap().get(isbn);
                inflateBookDataToView(book);
                setPositiveLabel();
                showHideView(true);
            } else {
                setNegativeLabel();
                showHideView(false);
            }
        } else {
            showEmptyLabel();
        }
    }

    private void inflateBookDataToView(Book book) {
        isbn.setText(book.getIsbn());
        bookTitle.setText(book.getTitle());
        author.setText(getAuthorsFromBook(book.getAuthors()));
        numberOfCopies.setText(String.valueOf(book.getNumCopies()));
    }

    private String getAuthorsFromBook(List<Author> authors) {
        StringBuilder result = new StringBuilder();
        String separator = "";
        for (Author author : authors) {
            result.append(separator);
            result.append(author.getFirstAndLastName());
            separator = ", ";
        }
        return result.toString();
    }

    private void setNegativeLabel() {
        bookStatus.setText("Book is not available");
        bookStatus.setTextFill(Paint.valueOf("RED"));
    }

    private void setPositiveLabel() {
        bookStatus.setText("Book is available");
        bookStatus.setTextFill(Paint.valueOf("GREEN"));
    }

    private void showEmptyLabel() {
        bookStatus.setText("Please enter ISBN");
        bookStatus.setTextFill(Paint.valueOf("RED"));
    }

    private boolean searchBookInDatabase(String isbn) {
        boolean isBookAvailable = false;
        Book book = dataAccessFacade.readBooksMap().get(isbn);
        if (book != null) {
            isBookAvailable = true;
        }
        return isBookAvailable;
    }

    @FXML
    public void addBookCopy(ActionEvent event) {
        if (noOfBooksCombo.getValue() != null) {
            int numOfCopies = noOfBooksCombo.getValue();
            makeBookCopy(numOfCopies);
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Please select the book and number of copies" + " ?", ButtonType.CLOSE);
            alert.show();
        }
    }

    private void makeBookCopy(int numOfCopies) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to add copy of the current book ", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Book book = dataAccessFacade.readBooksMap().get(isbn.getText());
            book.addCopy(isbn.getText(), numOfCopies);
            messageBar.setTextFill(Start.Colors.green);
            messageBar.setText("Book copies added successfully.");
        }
    }


    private void showHideView(boolean show) {
        isbn.setVisible(show);
        bookTitle.setVisible(show);
        author.setVisible(show);
        numberOfCopies.setVisible(show);
        noOfBooksCombo.setVisible(show);
        isbnLabel.setVisible(show);
        authorLabel.setVisible(show);
        numCopiesLabel.setVisible(show);
        titleLabel.setVisible(show);
        addCopyButton.setVisible(show);
    }

    public void setData() {
        for (int i = 1; i <= 10; i++) {
            noOfBooksCombo.getItems().add(i);
        }
        addCopyButton.setDisable(true);
        showHideView(false);
    }

    @FXML
    private void comboAction(ActionEvent event) {
        addCopyButton.setDisable(false);
        System.out.println(noOfBooksCombo.getValue());
    }

}
