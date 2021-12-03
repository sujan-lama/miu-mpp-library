package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.controller.BookDetailController;

import java.io.IOException;

public class BookDetailWindow extends Stage implements LibWindow {


    public static final BookDetailWindow INSTANCE = new BookDetailWindow();
    private boolean isInitialized = false;

    private FXMLLoader fxmlLoader;


    @Override
    public void init() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("bookDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setBook(Book book) {
        ((BookDetailController) fxmlLoader.getController()).setData(book);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void goBack() {
        hide();
        try {
            ListBookWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
