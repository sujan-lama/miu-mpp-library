package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueWindow extends Stage implements LibWindow {

    public static final OverdueWindow INSTANCE = new OverdueWindow();

    private boolean isInitialized = false;

    @Override
    public void init() throws IOException {
        super.setTitle("Over Dues Book");
        FXMLLoader fxmlLoader = new FXMLLoader(OverdueWindow.class.getResource("viewOverdue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 467, 161);
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
