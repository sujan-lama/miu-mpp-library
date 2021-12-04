package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.ListBookController;

import java.io.IOException;
import java.util.List;

public class ListBookWindow extends Stage implements LibWindow {

    public static ListBookWindow INSTANCE = new ListBookWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Books List");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        ControllerInterface ci = new SystemController();
        List<Book> books = ci.allBooks();
        ((ListBookController) fxmlLoader.getController()).setData(books);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void goToDetailOfBook(Book book) {
        hide();
        try {
            BookDetailWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BookDetailWindow.INSTANCE.setBook(book);
    }

    public void goBack() {
        hide();
        AdminWindow.INSTANCE.show();
    }
}
