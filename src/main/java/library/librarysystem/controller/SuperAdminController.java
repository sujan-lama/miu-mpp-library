package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.librarysystem.business.*;
import library.librarysystem.ui.AddBookWindow;
import library.librarysystem.ui.AddLibraryMemberWindow;
import library.librarysystem.ui.SuperAdminWindow;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SuperAdminController extends Stage {

    @FXML
    private AnchorPane memberAnchor;
    @FXML
    public TableView<LibraryMember> memberTable;

    @FXML
    public TableColumn<LibraryMember, String> id;
    @FXML
    public TableColumn<LibraryMember, String> name;
    @FXML
    public TableColumn<LibraryMember, String> phone;
    @FXML
    public TableColumn<LibraryMember, String> street;
    @FXML
    public TableColumn<LibraryMember, String> city;
    @FXML
    public TableColumn<LibraryMember, String> state;
    @FXML
    public TableColumn<LibraryMember, String> zip;

    @FXML
    public TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> isbn;
    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, String> copyNum;
    @FXML
    private TableColumn<Book, String> maxCheckout;
    @FXML
    private TableColumn<Book, String> authors;

    public ControllerInterface ci = new SystemController();

    public ObservableList<LibraryMember> libraryMembers = FXCollections.observableArrayList(
            ci.allMembers()
    );
    public ObservableList<Book> booksList = FXCollections.observableArrayList(
            ci.allBooks()
    );

    @FXML
    public void checkoutBook() {

    }

    @FXML
    public void addBook() {
        try {
            AddBookWindow.INSTANCE.init();
            AddBookWindow.INSTANCE.setDataAndShow(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addMember() {
        try {
            AddLibraryMemberWindow.INSTANCE.init();
            AddLibraryMemberWindow.INSTANCE.setDataAndShow(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLibraryMember() {
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        edit.setOnAction(e -> {
            try {
                AddLibraryMemberWindow.INSTANCE.init();
                AddLibraryMemberWindow.INSTANCE.setDataAndShow(memberTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        delete.setOnAction(e -> {

            LibraryMember member = memberTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setHeaderText("Delete " + member.getName() + "?");
            alert.setContentText("");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                ControllerInterface controller = new SystemController();
                controller.deleteMember(member);
                libraryMembers.remove(member);
            }

        });

        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(edit, delete);

        memberTable.setContextMenu(contextMenu);

        id.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMemberId()));
        name.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        phone.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTelephone())));

        street.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress().getStreet())));
        city.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress().getCity())));
        state.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress().getState())));
        zip.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress().getZip())));
        memberTable.setItems(libraryMembers);

    }

    public void setBooks() {
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        edit.setOnAction(e -> {
            try {
                AddBookWindow.INSTANCE.init();
                AddBookWindow.INSTANCE.setDataAndShow(bookTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        delete.setOnAction(e -> {

            Book book = bookTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setHeaderText("Delete " + book.getTitle() + "?");
            alert.setContentText("");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                ControllerInterface controller = new SystemController();
                controller.deleteBook(book);
                booksList.remove(book);
            }

        });

        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(edit, delete);
        bookTable.setContextMenu(contextMenu);

        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIsbn()));
        bookName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCopies().length)));

        maxCheckout.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getMaxCheckoutLength())));
        authors.setCellValueFactory(cellData ->
                new SimpleStringProperty(getAuthors(cellData.getValue().getAuthors())));
        bookTable.setItems(booksList);
    }

    private String getAuthors(List<Author> authors) {
        return authors.stream().map(e -> e.getFirstName() + " " + e.getLastName()).collect(Collectors.joining(", "));
    }

    public void logout(ActionEvent event) {
        SuperAdminWindow.INSTANCE.logout();
    }
}
