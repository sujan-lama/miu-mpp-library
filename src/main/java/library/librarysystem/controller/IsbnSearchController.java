package library.librarysystem.controller;

import javafx.fxml.FXML;
import library.librarysystem.ui.Start;

public class IsbnSearchController {

    @FXML
    public void onBackPressed() {
        Start.hideAllWindows();
        Start.primStage().show();
    }
}
