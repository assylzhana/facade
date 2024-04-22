package facade.assignment6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ElementCollection
    @CollectionTable(name = "user_borrowed_books", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "book_title")
    private Collection<String> borrowedBooks;
    public void addBorrowedBook(String bookTitle) {
        if (borrowedBooks == null) {
            borrowedBooks = new HashSet<>();
        }
        borrowedBooks.add(bookTitle);
    }
    public void removeBorrowedBook(String bookTitle) {
        if (borrowedBooks != null) {
            borrowedBooks.remove(bookTitle);
        }
    }
}
