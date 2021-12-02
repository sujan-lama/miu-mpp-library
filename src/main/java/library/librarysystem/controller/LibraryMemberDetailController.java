package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import library.librarysystem.business.ControllerInterface;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.business.SystemController;
import library.librarysystem.ui.AddLibraryMemberWindow;
import library.librarysystem.ui.LibraryMemberDetailWindow;
import library.librarysystem.ui.ListLibraryMemberWindow;

import java.io.IOException;
import java.util.Optional;

public class LibraryMemberDetailController extends Stage {


    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label address;

    private LibraryMember member;


    @FXML
    public void goBack() {
        LibraryMemberDetailWindow.INSTANCE.goBack();
    }


    public void setData(LibraryMember member) {
        this.member = member;
        name.setText(member.getFirstName() + " " + member.getLastName());
        phone.setText(member.getTelephone());
        address.setText(member.getAddress().getStreet() + ", " + member.getAddress().getCity() + ", " + member.getAddress().getState() + ", " + member.getAddress().getZip());
    }

    @FXML
    public void deleteLibraryMember() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setHeaderText("Delete " + member.getFirstName() + " " + member.getLastName().trim() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            ControllerInterface controller = new SystemController();
            controller.deleteMember(member);
            goBack();
        }

    }

    @FXML
    public void editLibraryMember() {
        ListLibraryMemberWindow.INSTANCE.hide();
        try {
            AddLibraryMemberWindow.INSTANCE.init();
            AddLibraryMemberWindow.INSTANCE.setDataAndShow(member);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
