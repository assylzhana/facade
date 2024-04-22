package facade.assignment6.facade;

import facade.assignment6.models.Book;

import java.util.List;

public interface LibraryFacade {
    void borrowBook(String bookTitle, String username);
    void returnBook(String bookTitle, String username);
    List<Book> searchBooks(String query);
    boolean checkAvailability(String bookTitle);
}
