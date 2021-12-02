package library.librarysystem.ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.AllBookController;

import java.io.IOException;

public class AllBooksWindow extends Stage implements LibWindow {
    public static final AllBooksWindow INSTANCE = new AllBooksWindow();

    private boolean isInitialized = false;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    /* This class is a singleton */
    private AllBooksWindow() {
    }

    private FXMLLoader loader;

    public void setData(String data) {
        ((AllBookController) loader.getController()).fillData(data);
    }

    public void init() throws IOException {
        loader = new FXMLLoader(AllBooksWindow.class.getResource("allbook.fxml"));
        Scene scene = new Scene(loader.load(), 480, 450);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized(true);
    }
}
