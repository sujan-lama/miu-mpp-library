package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.CheckoutRecordEntryController;

import java.io.IOException;

public class CheckoutRecordTableWindow extends Stage implements LibWindow {
    public static CheckoutRecordTableWindow INSTANCE = new CheckoutRecordTableWindow();
    private boolean isInitialized = false;
    private FXMLLoader fxmlLoader;
    @Override
    public void init() throws IOException {
        super.setTitle("Checkout Records");
        fxmlLoader = new FXMLLoader(
                Start.class.getResource("checkoutRecordTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
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
}
