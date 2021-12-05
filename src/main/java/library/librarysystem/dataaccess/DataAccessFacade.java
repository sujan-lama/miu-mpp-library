package library.librarysystem.dataaccess;


import library.librarysystem.business.*;
import library.librarysystem.controller.OverdueWindowController;
import library.librarysystem.ui.OverdueWindow;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DataAccessFacade implements DataAccess {

    enum StorageType {
        BOOKS, MEMBERS, USERS;
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir") + File.separator +
            "src" + File.separator + "main" + File.separator + "java" + File.separator + "library" + File.separator + "librarysystem" + File.separator + "dataaccess" + File.separator + "storage";

    public static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @Override
    public void deleteMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        mems.remove(member.getMemberId());
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @Override
    public void editMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        mems.put(member.getMemberId(), member);
        saveToStorage(StorageType.MEMBERS, mems);

    }

    @Override
    public void saveBook(Book book) {
        HashMap<String, Book> books = readBooksMap();
        String bookId = book.getIsbn();
        books.put(bookId, book);
        saveToStorage(StorageType.BOOKS, books);
    }

    @Override
    public void deleteBook(Book book) {
        HashMap<String, Book> books = readBooksMap();
        String bookId = book.getIsbn();
        books.remove(bookId);
        saveToStorage(StorageType.BOOKS, books);
    }

    @Override
    public void editBook(Book book) {
        HashMap<String, Book> books = readBooksMap();
        books.put(book.getIsbn(), book);
        saveToStorage(StorageType.BOOKS, books);
    }

    public boolean isBookAvailable(String memberId, String isbn) {
        return readMemberMap().get(memberId) != null
                && readBooksMap().get(isbn) != null
                && isCopyAvailable(readBooksMap().get(isbn));
    }

    public boolean isCopyAvailable(Book book) {
        return book.getCopies() != null && book.getCopies().length != 0
                && isBookCopyAvailable(book.getCopies());
    }

    private boolean isBookCopyAvailable(BookCopy[] copies) {
        boolean flag = false;
        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public void createCheckOutRecordEntry(String memberId, String isbn) {
        LibraryMember member = readMemberMap().get(memberId);
        Book book = readBooksMap().get(isbn);

        List<BookCopy> bookCopies = Arrays.stream(book.getCopies()).
                filter(BookCopy::isAvailable).collect(Collectors.toList());

        if (bookCopies.size() == 0) {
            return;
        }

        if (member.getCheckoutRecord() == null) {
            member.setCheckoutRecord(new CheckoutRecord());
        }

        if (member.getCheckoutRecord().getCheckoutRecordEntries() == null) {
            member.getCheckoutRecord().setCheckoutRecordEntries(new ArrayList<>());
            member.getCheckoutRecord().setLibraryMember(member);
        }


        for (BookCopy bookCopy : book.getCopies()) {
            if (bookCopy.isAvailable()) {
                bookCopy.changeAvailability();
                member.getCheckoutRecord().getCheckoutRecordEntries()
                        .add(new CheckoutRecordEntry(LocalDate.now(), LocalDate.now().plusDays(21),
                                bookCopy, member.getCheckoutRecord()));
                break;
            }
        }
        updateBook(book);
        updateLibraryMember(member);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        //Returns a Map with name/value pairs being
        //   isbn -> Book
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }


    /////load methods - these place test data into the library.librarysystem.storage area
    ///// - used just once at startup

    public static List<CheckoutRecordEntry> getCheckoutRecordEntries(String memberId) {
        HashMap<String, LibraryMember> memberHashMap = (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
        if (memberHashMap.get(memberId).getCheckoutRecord() == null)
            return new ArrayList<>();

        List<CheckoutRecordEntry> list = memberHashMap.get(memberId).getCheckoutRecord().getCheckoutRecordEntries();
        return list;
    }

    public static List<BookCopy> getBooksByIsbn(String isbn) {
        HashMap<String, Book> bookHashMap = (HashMap<String, Book>) readFromStorage(
                StorageType.BOOKS);
        HashMap<String, LibraryMember> map = (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
        List<BookCopy> bookCopies = Arrays.asList(bookHashMap.get(isbn).getCopies());
        List<BookCopy> resultCopies = new ArrayList<>();
        for (LibraryMember libraryMember : map.values()) {
            if (libraryMember.getCheckoutRecord() != null
                    && libraryMember.getCheckoutRecord().getCheckoutRecordEntries() != null) {
                List<CheckoutRecordEntry> entries = libraryMember.getCheckoutRecord().getCheckoutRecordEntries();
                for (CheckoutRecordEntry entry : entries) {
                    if (entry.getDueDate().isBefore(LocalDate.now())
                            && !entry.getBookCopy().isAvailable() &&
                            entry.getBookCopy().getBook().getIsbn().equals(OverdueWindowController.currentIsbn)) {
                        BookCopy bookCopy = entry.getBookCopy();
                        bookCopy.setDueDate(entry.getDueDate());
                        bookCopy.setMemberName(libraryMember.getFirstName() + " " + libraryMember.getLastName());
                        resultCopies.add(bookCopy);
                    }
                }
//                if (flag) {
//                    break;
//                }
            }
        }
        return resultCopies;
    }

    @Override
    public boolean checkOverdueRecords(String currentIsbn) {
        HashMap<String, LibraryMember> map = readMemberMap();
        boolean flag = false;
        for (LibraryMember libraryMember : map.values()) {
            if (libraryMember.getCheckoutRecord() != null
                    && libraryMember.getCheckoutRecord().getCheckoutRecordEntries() != null) {
                List<CheckoutRecordEntry> entries = libraryMember.getCheckoutRecord().getCheckoutRecordEntries();
                for (CheckoutRecordEntry entry : entries) {
                    if (entry.getDueDate().isBefore(LocalDate.now())
                            && !entry.getBookCopy().isAvailable() &&
                            entry.getBookCopy().getBook().getIsbn().equals(currentIsbn)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        return flag;
    }

    void updateBook(Book book) {
        HashMap<String, Book> map = readBooksMap();
        map.put(book.getIsbn(), book);
        saveToStorage(StorageType.BOOKS, map);
    }

    void updateLibraryMember(LibraryMember member) {
        HashMap<String, LibraryMember> map = readMemberMap();
        map.put(member.getMemberId(), member);
        saveToStorage(StorageType.MEMBERS, map);
    }

    static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }


    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked")
            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }

}
