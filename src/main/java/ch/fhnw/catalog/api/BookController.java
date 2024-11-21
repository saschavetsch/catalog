package ch.fhnw.catalog.api;

import ch.fhnw.catalog.domain.Book;
import ch.fhnw.catalog.domain.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @GetMapping
    public List<Book> getFilteredBooks(@RequestParam(required = false) String search) {
        search = (search == null) ? "" : search;
        return bookRepository.findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                search, search, search
        );
    }
}
