package facade.assignment6.controllers;

import facade.assignment6.exception.BookUnavailableException;
import facade.assignment6.facade.LibraryFacade;
import facade.assignment6.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private final LibraryFacade libraryFacade;

    @Autowired
    public LibraryController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam String bookTitle, @RequestParam String username) {
        try {
            libraryFacade.borrowBook(bookTitle, username);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (BookUnavailableException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam String bookTitle, @RequestParam String username) {
        libraryFacade.returnBook(bookTitle, username);
        return ResponseEntity.ok("Book returned successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = libraryFacade.searchBooks(query);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/checkAvailability")
    public ResponseEntity<Boolean> checkAvailability(@RequestParam String bookTitle) {
        boolean available = libraryFacade.checkAvailability(bookTitle);
        return ResponseEntity.ok(available);
    }
}
