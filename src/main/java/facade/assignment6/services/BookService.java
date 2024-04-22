package facade.assignment6.services;

import facade.assignment6.models.Book;
import facade.assignment6.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public boolean checkAvailability(String bookTitle) {
        return !bookRepository.findByTitleIgnoreCase(bookTitle).isEmpty();
    }
}