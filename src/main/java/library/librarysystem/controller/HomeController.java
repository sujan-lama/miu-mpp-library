package library.librarysystem.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import library.librarysystem.business.*;
import library.librarysystem.dataaccess.Auth;
import library.librarysystem.ui.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomeController extends Stage {

    @FXML
    public TextField searchByISBN;
    @FXML
    public Tab memberTab;
    @FXML
    public Tab bookTab;
    @FXML
    public Button checkoutBooksBtn;
    @FXML
    public Button addBooksBtn;
    @FXML
    public Menu menu;
    @FXML
    public Button addMemberBtn;
    @FXML
    public TextField searchByMemberIdorName;
    @FXML
    private TabPane tabPane;
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
    public TableColumn<Book, String> availableCopies;
    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, String> copyNum;
    @FXML
    private TableColumn<Book, String> maxCheckout;
    @FXML
    private TableColumn<Book, String> authors;

    //books menu item
    MenuItem authorDetail = new MenuItem("Author Detail");
    MenuItem editBook = new MenuItem("Edit");
    MenuItem deleteBook = new MenuItem("Delete");
    MenuItem addCopy = new MenuItem("Add copy");
    MenuItem checkOverdue = new MenuItem("Check Overdue");

    //library menu item
    MenuItem viewCheckout = new MenuItem("View Checkout Records");
    MenuItem editMember = new MenuItem("Edit");
    MenuItem deleteMember = new MenuItem("Delete");

    public ControllerInterface ci = new SystemController();

    public ObservableList<LibraryMember> libraryMembers = FXCollections.observableArrayList(
            ci.allMembers()
    );
    public ObservableList<Book> booksList = FXCollections.observableArrayList(
            ci.allBooks()
    );

    @FXML
    public void checkoutBook() {
        try {
            CheckoutBookWindow.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setVisibilityByAuth(Auth auth) {
        switch (auth) {
            case BOTH -> {
                menu.setText("SuperAdmin");
                checkoutBooksBtn.setVisible(true);
                addBooksBtn.setVisible(true);

                //member
                addMemberBtn.setVisible(true);
                final ContextMenu contextMember = new ContextMenu();
                contextMember.getItems().addAll(viewCheckout, editMember, deleteMember);
                memberTable.setContextMenu(contextMember);

                //book
                final ContextMenu contextBook = new ContextMenu();
                contextBook.getItems().addAll(authorDetail, addCopy, checkOverdue, editBook, deleteBook);
                bookTable.setContextMenu(contextBook);
            }

            case ADMIN -> {
                menu.setText("Admin");
                checkoutBooksBtn.setVisible(false);
                addBooksBtn.setVisible(true);

                //member
                addMemberBtn.setVisible(true);
                final ContextMenu contextMember = new ContextMenu();
                contextMember.getItems().addAll(editMember, deleteMember);
                memberTable.setContextMenu(contextMember);

                //book
                final ContextMenu contextBook = new ContextMenu();
                contextBook.getItems().addAll(authorDetail, addCopy, editBook, deleteBook);
                bookTable.setContextMenu(contextBook);
            }

            case LIBRARIAN -> {
                menu.setText("Librarian");
                checkoutBooksBtn.setVisible(true);
                addBooksBtn.setVisible(false);
                //member
                addMemberBtn.setVisible(false);
                final ContextMenu contextMember = new ContextMenu();
                contextMember.getItems().addAll(viewCheckout);
                memberTable.setContextMenu(contextMember);

                //book
                final ContextMenu contextBook = new ContextMenu();
                contextBook.getItems().addAll(authorDetail, checkOverdue);
                bookTable.setContextMenu(contextBook);
            }
        }
    }

    public void setLibraryMember() {
        searchByMemberIdorName.textProperty().addListener((observable, oldValue, newValue) -> {
            List<LibraryMember> member = ci.allMembers();
            libraryMembers.clear();
            libraryMembers.addAll(member.stream().filter(e -> e.getMemberId().startsWith(newValue) || e.getName().toLowerCase(Locale.ROOT).startsWith(newValue)).toList());
        });
        editMember.setOnAction(e -> {
            try {
                AddLibraryMemberWindow.INSTANCE.init();
                AddLibraryMemberWindow.INSTANCE.setDataAndShow(memberTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        viewCheckout.setOnAction(e -> {
            try {
                CheckoutRecordTableWindow.INSTANCE.init();
                CheckoutRecordTableWindow.INSTANCE.setDataAndShow(memberTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        deleteMember.setOnAction(e -> {
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
        searchByISBN.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Book> books = ci.allBooks();
            booksList.clear();
            booksList.addAll(books.stream().filter(e -> e.getIsbn().startsWith(newValue)).toList());
        });

        authorDetail.setOnAction(e -> {
            try {
                ListAuthorWindow.INSTANCE.init();
                ListAuthorWindow.INSTANCE.setDataAndShow(bookTable.getSelectionModel().getSelectedItem().getAuthors());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        checkOverdue.setOnAction(e -> {
            try {
                OverdueTableWindow.INSTANCE.init();
                OverdueTableWindow.INSTANCE.setDataAndShow(bookTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        editBook.setOnAction(e -> {
            try {
                AddBookWindow.INSTANCE.init();
                AddBookWindow.INSTANCE.setDataAndShow(bookTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        addCopy.setOnAction(e -> {
            try {
                AddCopyWindow.INSTANCE.init();
                AddCopyWindow.INSTANCE.setDataAndShow(bookTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        deleteBook.setOnAction(e -> {

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

        isbn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIsbn()));
        bookName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        availableCopies.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().availableCopies())));
        copyNum.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCopies().length)));

        maxCheckout.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getMaxCheckoutLength())));
        authors.setCellValueFactory(cellData ->
                new SimpleStringProperty(getAuthors(cellData.getValue().getAuthors())));
        bookTable.setItems(booksList);
    }

    private String getAuthors(List<Author> authors) {
        return authors.stream().map(Person::getName).collect(Collectors.joining(", "));
    }

    public void logout(ActionEvent event) {
        HomeWindow.INSTANCE.logout();
    }

    public void updateBookTable(Book book) {
        if (!booksList.contains(book)) {
            booksList.add(book);
        } else {
            booksList.set(booksList.indexOf(book), book);
        }
    }

    public void updateLibraryMember(LibraryMember member) {
        if (!libraryMembers.contains(member)) {
            libraryMembers.add(member);
        } else
            libraryMembers.set(libraryMembers.indexOf(member), member);
    }
}
