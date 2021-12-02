package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.business.SystemController;

import java.io.IOException;

public class AdminWindow extends Stage implements LibWindow {

    public static final AdminWindow INSTANCE = new AdminWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("admin.xml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        show();

    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

}
