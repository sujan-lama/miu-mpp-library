package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import library.librarysystem.ui.Start;

public class AllMemberController {


    @FXML
    private Label allMemberLabel;

    @FXML
    private TextArea textArea;

    @FXML
    public void fillData(String data) {
        textArea.setText(data);
    }

    @FXML
    public void onBackPressed() {
        Start.hideAllWindows();
        Start.primStage().show();
    }
}
