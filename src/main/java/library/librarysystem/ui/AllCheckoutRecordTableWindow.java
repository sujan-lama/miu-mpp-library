package library.librarysystem.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.librarysystem.controller.AllCheckoutRecordEntryController;

import java.io.IOException;

public class AllCheckoutRecordTableWindow extends Stage implements LibWindow {
    public static AllCheckoutRecordTableWindow INSTANCE = new AllCheckoutRecordTableWindow();
    private boolean isInitialized = false;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws IOException {
        super.setTitle("View All Checkout Records");
        fxmlLoader = new FXMLLoader(
                Start.class.getResource("allCheckoutRecordTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 724, 436);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        setScene(scene);
    }

    public void setDataAndShow() {
        AllCheckoutRecordEntryController controller = fxmlLoader.getController();
        controller.setData();
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
