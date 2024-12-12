package ch.fhnw.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.fhnw.catalog.domain.Book;
import ch.fhnw.catalog.domain.BookRepository;

@SpringBootTest
class CatalogApplicationTests {

    @Autowired
    BookRepository bookRepository;

    private Book testBook = new Book();
    List<Book> expectedResult;

    // Setup of a test repository.
    private void repositorySetup() {
        this.testBook.setISBN("978-0-099-59008-8");
        this.testBook.setTitle("Sapiens");
        this.testBook.setAuthor("Yuval Noah Harari");
        this.testBook.setDescription("A Brief History of Humankind");
        bookRepository.save(testBook);
    }

    // Setup expected result.
    private void resultSetup() {
        this.expectedResult = Arrays.asList(testBook);
    }

    @Test
    void findBookShouldReturnResultAcrossAllFieldsAsList() {
       repositorySetup(); 
       resultSetup();

       List<Book> result;
       result = bookRepository.findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Sapiens", null, null);
       assertEquals(expectedResult.getFirst().getTitle(), result.getFirst().getTitle());
       assertEquals(expectedResult.getFirst().getAuthor(), result.getFirst().getAuthor());
       assertEquals(expectedResult.getFirst().getDescription(), result.getFirst().getDescription());
    }

}
