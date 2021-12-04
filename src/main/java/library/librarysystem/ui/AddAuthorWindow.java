package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.Author;
import library.librarysystem.controller.AddAuthorController;

import java.io.IOException;

public class AddAuthorWindow extends Stage implements LibWindow {

    public static AddAuthorWindow INSTANCE = new AddAuthorWindow();

    private boolean isInitialized = false;


    private Author author = null;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("Add Author");
        fxmlLoader = new FXMLLoader(getClass().getResource("addAuthor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 560);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow(Author author) {
        this.author = author;
        ((AddAuthorController) fxmlLoader.getController()).setData(author);
        show();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {


    }


}
