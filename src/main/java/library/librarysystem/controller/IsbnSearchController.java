package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import library.librarysystem.ui.Start;

public class IsbnSearchController extends Stage {

    @FXML
    private ComboBox<Integer> noOfBooksCombo;

    @FXML
    public void onBackPressed() {
        Start.hideAllWindows();
        Start.primStage().show();
    }


    public void setData() {
        for (int i =1;i<=10;i++){
           noOfBooksCombo.getItems().add(i);
        }
    }

}
