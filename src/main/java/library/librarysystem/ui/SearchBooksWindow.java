package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.AllBookController;
import library.librarysystem.controller.IsbnSearchController;

import java.io.IOException;

public class SearchBooksWindow extends Stage implements LibWindow {
    public static final SearchBooksWindow INSTANCE = new SearchBooksWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Search Books");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("isbn_search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 320);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        ((IsbnSearchController) fxmlLoader.getController()).setData();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
}
