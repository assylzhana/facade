package facade.assignment6.config;

import facade.assignment6.facade.LibraryFacade;
import facade.assignment6.facade.LibraryFacadeImpl;
import facade.assignment6.services.BookService;
import facade.assignment6.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeConfiguration {


    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    public FacadeConfiguration(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Bean
    public LibraryFacade libraryFacade() {
        return new LibraryFacadeImpl(bookService, userService);
    }
}
