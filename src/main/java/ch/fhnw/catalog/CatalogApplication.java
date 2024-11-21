package ch.fhnw.catalog;

import ch.fhnw.catalog.domain.Book;
import ch.fhnw.catalog.domain.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

    private final BookRepository bookRepository;

    public CatalogApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }

    @PostConstruct
    private void initPlaceholderData() throws Exception {
        Book sapiens = new Book();
        sapiens.setISBN("978-0-099-59008-8");
        sapiens.setTitle("Sapiens");
        sapiens.setAuthor("Yuval Noah Harari");
        sapiens.setDescription("A Brief History of Humankind");
        bookRepository.save(sapiens);

        Book nexus = new Book();
        nexus.setISBN("978-1-911717-09-6");
        nexus.setTitle("Nexus");
        nexus.setAuthor("Yuval Noah Harari");
        nexus.setDescription("A Brief History of Information Networks from the Stone Age to AI");
        bookRepository.save(nexus);

        Book subtle = new Book();
        subtle.setISBN("978-0-06-264154-0");
        subtle.setTitle("THE SUBTLE ART OF NOT GIVING A F*CK");
        subtle.setAuthor("Mark Manson");
        subtle.setDescription("A COUNTERINTUITIVE APPROACH TO LIVING A GOOD LIFE");
        bookRepository.save(subtle);

        System.out.println("PostConstruct generated books");
    }

}
