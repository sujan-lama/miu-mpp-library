package library.librarysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AllBooksWindow;
import library.librarysystem.ui.AllMembersWindow;
import library.librarysystem.ui.LoginWindow;
import library.librarysystem.ui.Start;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class StartController extends Stage {

    @FXML
    public void login(ActionEvent event) {
        Start.hideAllWindows();
        if (!LoginWindow.INSTANCE.isInitialized()) {
            try {
                LoginWindow.INSTANCE.init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        LoginWindow.INSTANCE.clear();
        LoginWindow.INSTANCE.show();
    }

    @FXML
    public void allBookIds(ActionEvent event) {
        Start.hideAllWindows();
        if (!AllBooksWindow.INSTANCE.isInitialized()) {
            try {
                AllBooksWindow.INSTANCE.init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        ControllerInterface ci = new SystemController();
        List<String> ids = ci.allBookIds();
        Collections.sort(ids);
        StringBuilder sb = new StringBuilder();
        for (String s : ids) {
            sb.append(s).append("\n");
        }
        AllBooksWindow.INSTANCE.setData(sb.toString());
        AllBooksWindow.INSTANCE.show();
    }

    @FXML
    public void allMemberIds(ActionEvent event) {
        Start.hideAllWindows();
        if (!AllMembersWindow.INSTANCE.isInitialized()) {
            try {
                AllMembersWindow.INSTANCE.init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        ControllerInterface ci = new SystemController();
        List<String> ids = ci.allMemberIds();
        Collections.sort(ids);
        System.out.println(ids);
        StringBuilder sb = new StringBuilder();
        for (String s : ids) {
            sb.append(s + "\n");
        }
        System.out.println(sb.toString());
        AllMembersWindow.INSTANCE.setData(sb.toString());
        AllMembersWindow.INSTANCE.show();

    }
}
