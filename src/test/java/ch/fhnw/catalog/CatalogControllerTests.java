package ch.fhnw.catalog;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import ch.fhnw.catalog.api.BookController;
import ch.fhnw.catalog.domain.Book;
import ch.fhnw.catalog.domain.BookRepository;

@WebMvcTest(BookController.class)
public class CatalogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookRepository bookRepository;

    Book testBook = new Book();

    @Test
    void getFilteredBooksShouldReturnCorrectBook() throws Exception {

    testBook.setISBN("978-0-099-59008-8");
    testBook.setTitle("Sapiens");
    testBook.setAuthor("Yuval Noah Harari");
    testBook.setDescription("A Brief History of Humankind");

    List<Book> result = Arrays.asList(testBook);

        when(bookRepository
            .findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase("title", "author", "description"))
            .thenReturn(result);

        //     this.mockMvc.perform((get("/books")
        //             .param("title", "Sapiens")
        //             .param("author", "Yuval Noah Harari")
        //             .param("description", "A Brief History")
        //         )
        //         .andExpect(status().isOk()
        // ;


    }
    
}
