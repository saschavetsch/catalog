package ch.fhnw.catalog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import ch.fhnw.catalog.api.BookController;
import ch.fhnw.catalog.domain.Book;
import ch.fhnw.catalog.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookController.class)
class CatalogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private Book testBook = new Book();
    private List<Book> result;

    // Setup of a mock book.
    private void bookSetup() {
        this.testBook.setISBN("978-0-099-59008-8");
        this.testBook.setTitle("Sapiens");
        this.testBook.setAuthor("Yuval Noah Harari");
        this.testBook.setDescription("A Brief History of Humankind");
    }

    // Setup of a mock query result.
    private void resultSetup() {
        result = Arrays.asList(testBook);
    }

    @Test
    void getFilteredBooksShouldReturnCorrectBook() throws Exception {
        bookSetup();
        resultSetup();

        // Setup mock query behaviour.
        when(bookRepository
            .findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Sapiens", "Sapiens", "Sapiens"))
            .thenReturn(result);

        this.mockMvc.perform(get("/books")
            .param("search", "Sapiens"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Sapiens"))
            .andExpect(jsonPath("$[0].author").value("Yuval Noah Harari"))
            .andExpect(jsonPath("$[0].description").value("A Brief History of Humankind"));
    }

}
