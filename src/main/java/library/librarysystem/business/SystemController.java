package library.librarysystem.business;

import library.librarysystem.dataaccess.Auth;
import library.librarysystem.dataaccess.DataAccess;
import library.librarysystem.dataaccess.DataAccessFacade;
import library.librarysystem.dataaccess.User;

import java.util.HashMap;
import java.util.List;


public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();

    }

    @Override
    public void logout() {
        currentAuth = null;
    }

    @Override
    public List<LibraryMember> allMembers() {
        DataAccess da = new DataAccessFacade();
        return da.readMemberMap().values().stream().toList();
    }

    @Override
    public void saveMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(member);
    }

    @Override
    public void editMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.editMember(member);
    }

    @Override
    public void deleteMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.deleteMember(member);
    }

    @Override
    public List<Book> allBooks() {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().values().stream().toList();

    }

    @Override
    public void saveBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.saveBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.deleteBook(book);
    }

    @Override
    public void editBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.editBook(book);
    }

    @Override
    public boolean isBookAvailable(String memberId, String isbn) {
        DataAccess dataAccess = new DataAccessFacade();
        return dataAccess.isBookAvailable(memberId, isbn);
    }

    @Override
    public void createCheckOutRecordEntry(String memberId, String isbn) {
        DataAccess dataAccess = new DataAccessFacade();
        dataAccess.createCheckOutRecordEntry(memberId, isbn);
    }

    @Override
    public LibraryMember getMemberById(String memberId) {
        DataAccess dataAccess = new DataAccessFacade();
        return dataAccess.readMemberMap().get(memberId);
    }

    @Override
    public Book getBooksById(String isbn) {
        DataAccess dataAccess = new DataAccessFacade();
        return dataAccess.readBooksMap().get(isbn);
    }
}
