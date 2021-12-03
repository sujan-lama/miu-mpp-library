package library.librarysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import library.librarysystem.business.LibraryMember;
import library.librarysystem.ui.ListLibraryMemberWindow;

import java.util.ArrayList;
import java.util.List;

public class ListLibraryMemberController extends Stage {

    @FXML
    public ListView<String> listView;

    public void setData(List<LibraryMember> libraryMembers) {
        List<String> members = new ArrayList<>();
        int i = 1;
        for (LibraryMember member : libraryMembers) {
            members.add(i + ". " + member.getFirstName() + " " + member.getLastName());
            i++;
        }
        listView.getItems().addAll(members);

        listView.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        ListLibraryMemberWindow.INSTANCE.goToDetailOfLibraryMember(libraryMembers.get(listView.getSelectionModel().getSelectedIndices().get(0)));
                    }
                }
        );
    }

    @FXML
    public void goBack() {
        ListLibraryMemberWindow.INSTANCE.goBack();
    }

    public void showDetail(String id) {

    }
}
