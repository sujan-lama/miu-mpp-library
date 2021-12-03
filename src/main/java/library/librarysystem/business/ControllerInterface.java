package library.librarysystem.business;


import java.util.List;


public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;

    public void logout();

    public List<Book> allBooks();

    //member crud
    public void saveMember(LibraryMember member);

    public void deleteMember(LibraryMember member);

    public void editMember(LibraryMember member);

    //books crud
    public void saveBook(Book book);

    public void deleteBook(Book book);

    public void editBook(Book book);

    boolean isBookAvailable(String memberId, String isbn);

    void createCheckOutRecordEntry(String memberId, String isbn);

    LibraryMember getMemberById(String memberId);

    public List<LibraryMember> allMembers();

}
