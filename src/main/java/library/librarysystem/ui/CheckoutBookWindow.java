package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.SystemController;
import library.librarysystem.dataaccess.Auth;

import java.io.IOException;

public class CheckoutBookWindow extends Stage implements LibWindow {
    public static CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Check out Books");
        fxmlLoader = new FXMLLoader(getClass().getResource("checkoutBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 282, 242);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        show();

    }


    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void updateBook(Book updatedBook) {
        HomeWindow.INSTANCE.updateBook(updatedBook);
    }
}
