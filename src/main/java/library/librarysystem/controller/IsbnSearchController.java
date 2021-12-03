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
import library.librarysystem.ui.Start;

import java.util.List;

public class IsbnSearchController extends Stage {

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
        Start.hideAllWindows();
        Start.primStage().show();
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
            } else {
                setNegativeLabel();
            }
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
            separator = ",";
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
            noOfBooksCombo.setValue(null);
            messageBar.setTextFill(Start.Colors.green);
            messageBar.setText("Book copies added successfully.");
        }
    }


    public void setData() {
        for (int i = 1; i <= 10; i++) {
            noOfBooksCombo.getItems().add(i);
        }
    }

}
