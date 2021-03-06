package library.librarysystem.dataaccess;


import library.librarysystem.business.Book;
import library.librarysystem.business.LibraryMember;

import java.util.HashMap;


public interface DataAccess {
    public HashMap<String, Book> readBooksMap();

    public HashMap<String, User> readUserMap();

    public HashMap<String, LibraryMember> readMemberMap();

    //member crud
    public void saveNewMember(LibraryMember member);

    public void deleteMember(LibraryMember member);

    public void editMember(LibraryMember member);

    //Book
    public void saveBook(Book book);

    public void deleteBook(Book book);

    public void editBook(Book book);

    boolean isBookAvailable(String memberId, String isbn);

    void createCheckOutRecordEntry(String memberId, String isbn);

    boolean checkOverdueRecords(String currentIsbn);
}
