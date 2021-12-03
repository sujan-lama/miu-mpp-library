package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.librarysystem.business.Book;
import library.librarysystem.business.BookCopy;
import library.librarysystem.business.CheckoutRecordEntry;
import library.librarysystem.dataaccess.DataAccessFacade;

import java.net.URL;
import java.util.ResourceBundle;

public class OverdueTableWindowController implements Initializable {

    @FXML
    private TableView<BookCopy> table;
    @FXML
    private TableColumn<BookCopy, String> isbn;
    @FXML
    private TableColumn<BookCopy, String> title;
    @FXML
    private TableColumn<BookCopy, String> copyNum;
    @FXML
    private TableColumn<BookCopy, String> libMember;
    @FXML
    private TableColumn<BookCopy, String> dueDate;

    @FXML
    private Label memberId;
    @FXML
    private Label memberName;

    public ObservableList<BookCopy> list = FXCollections.observableArrayList(
            DataAccessFacade.getBooksByIsbn(OverdueWindowController.currentIsbn)
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getIsbn())));
        title.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getTitle())));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCopyNum())));
        libMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        table.setItems(list);
    }
}
