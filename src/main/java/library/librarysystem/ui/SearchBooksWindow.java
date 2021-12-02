package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchBooksWindow extends Stage implements LibWindow {
    public static final SearchBooksWindow INSTANCE = new SearchBooksWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("isbn_search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),480,360);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);

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
