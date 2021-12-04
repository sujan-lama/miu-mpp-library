package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueTableWindow extends Stage implements LibWindow {

    public static OverdueTableWindow INSTANCE = new OverdueTableWindow();

    @Override
    public void init() throws IOException {
        super.setTitle("Overdue Table");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("overdueTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 566, 418);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
        show();
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }
}
