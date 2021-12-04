package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.controller.AuthorDetailController;

import java.io.IOException;

public class AuthorDetailWindow extends Stage implements LibWindow {


    public static final AuthorDetailWindow INSTANCE = new AuthorDetailWindow();
    private boolean isInitialized = false;

    private FXMLLoader fxmlLoader;


    @Override
    public void init() throws IOException {
        super.setTitle("Author Detail");
        fxmlLoader = new FXMLLoader(getClass().getResource("authorDetail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 410);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setAuthor(Author author) {
        ((AuthorDetailController) fxmlLoader.getController()).setData(author);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    public void deleteAuthor(Author author) {
        hide();
        AddBookWindow.INSTANCE.deleteAuthor(author);

    }
}
