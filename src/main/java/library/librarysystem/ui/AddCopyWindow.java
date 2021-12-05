package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.SystemController;
import library.librarysystem.controller.AddCopyController;
import library.librarysystem.dataaccess.Auth;

import java.io.IOException;

public class AddCopyWindow extends Stage implements LibWindow {


    public static AddCopyWindow INSTANCE = new AddCopyWindow();
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Add Copy to Book");
        fxmlLoader = new FXMLLoader(getClass().getResource("addCopy.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 239, 201);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(Book book) {
        AddCopyController controller = fxmlLoader.getController();
        controller.setBook(book);
        show();
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void updateBook(Book book) {
        hide();
        HomeWindow.INSTANCE.updateBook(book);
    }
}
