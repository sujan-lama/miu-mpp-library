package library.librarysystem.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static Stage primStage = null;

    public static Stage primStage() {
        return primStage;
    }

    public static class Colors {
        public static Color green = Color.web("#034220");
        public static Color red = Color.FIREBRICK;
    }

    public static void hideAllWindows() {
        primStage.hide();
        LoginWindow.INSTANCE.hide();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primStage = primaryStage;
        primaryStage.setTitle("Main Page");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 555, 400);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Start.class.getResource("library.css").toExternalForm());
        primaryStage.show();
    }

}
