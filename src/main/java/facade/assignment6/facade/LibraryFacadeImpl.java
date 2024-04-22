package facade.assignment6.facade;

import facade.assignment6.exception.BookUnavailableException;
import facade.assignment6.models.Book;
import facade.assignment6.services.BookService;
import facade.assignment6.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryFacadeImpl implements LibraryFacade {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public LibraryFacadeImpl(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void borrowBook(String bookTitle, String username) {
        if (bookService.checkAvailability(bookTitle)) {
            userService.borrowBook(bookTitle, username);
        } else {
            throw new BookUnavailableException("Book '" + bookTitle + "' is not available for borrowing.");
        }
    }

    @Override
    public void returnBook(String bookTitle, String username) {
        userService.returnBook(bookTitle, username);
    }

    @Override
    public List<Book> searchBooks(String query) {
        return bookService.searchBooks(query);
    }

    @Override
    public boolean checkAvailability(String bookTitle) {
        return bookService.checkAvailability(bookTitle);
    }
}
