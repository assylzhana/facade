package facade.assignment6.services;

import facade.assignment6.models.User;
import facade.assignment6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void borrowBook(String bookTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User '" + username + "' not found.");
        }

        if (user.getBorrowedBooks() == null) {
            user.setBorrowedBooks(new HashSet<>());
        }
        if (user.getBorrowedBooks().contains(bookTitle)) {
            throw new IllegalArgumentException("User '" + username + "' has already borrowed the book '" + bookTitle + "'.");
        }
        user.getBorrowedBooks().add(bookTitle);

        userRepository.save(user);
    }

    public void returnBook(String bookTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User '" + username + "' not found.");
        }

        if (user.getBorrowedBooks() == null || !user.getBorrowedBooks().contains(bookTitle)) {
            throw new IllegalArgumentException("User '" + username + "' has not borrowed the book '" + bookTitle + "'.");
        }
        user.getBorrowedBooks().remove(bookTitle);
        userRepository.save(user);
    }
}