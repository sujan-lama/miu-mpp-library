package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.business.Book;
import library.librarysystem.controller.AddBookController;

import java.io.IOException;

public class AddBookWindow extends Stage implements LibWindow {

    public static AddBookWindow INSTANCE = new AddBookWindow();

    private boolean isInitialized = false;


    private Book book = null;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Add Book");
        fxmlLoader = new FXMLLoader(getClass().getResource("addBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 310, 600);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(Book book) {
        this.book = book;
        ((AddBookController) fxmlLoader.getController()).setData(book);
        show();
    }

    public void addAuthor(Author author, Author previousAuthor) {
        ((AddBookController) fxmlLoader.getController()).addAuthor(author, previousAuthor);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void deleteAuthor(Author author) {
        ((AddBookController) fxmlLoader.getController()).deleteAuthor(author);

    }

    public void addoreditBookSuccess(Book book) {
        hide();
        HomeWindow.INSTANCE.updateBook(book);


    }
}
