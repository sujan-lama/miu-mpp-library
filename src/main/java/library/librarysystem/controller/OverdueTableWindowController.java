package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.librarysystem.business.Book;
import library.librarysystem.business.BookCopy;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.ui.OverdueTableWindow;

import java.io.IOException;

public class OverdueTableWindowController extends Stage {

    @FXML
    public Label label;
    @FXML
    private TableView<BookCopy> table;
    @FXML
    private TableColumn<BookCopy, String> isbn;
    @FXML
    private TableColumn<BookCopy, String> titleField;
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

    public ObservableList<BookCopy> list;


    public void setData(Book book) {
        list = FXCollections.observableArrayList(
                DataAccessFacade.getBooksByIsbn(book.getIsbn())
        );
        label.setText("Overdue table for Book: " + book.getTitle());
        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getIsbn())));
        titleField.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getBook().getTitle())));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCopyNum())));
        libMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        table.setItems(list);
    }
}
