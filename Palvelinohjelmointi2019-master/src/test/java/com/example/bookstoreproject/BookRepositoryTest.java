package com.example.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstoreproject.domain.Book;
import com.example.bookstoreproject.domain.BookRepository;
import com.example.bookstoreproject.domain.Category;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
    private BookRepository repository;

    @Test
    public void findTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("title");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("person");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Mickey", "Mouse", 2019, 23, 26, new Category("BITE"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }   
    @Test
    public void deleteBook() {
    	System.out.println("books" + repository.findAll());
		repository.deleteById((long) 3);
    	System.out.println("books" + repository.findAll());
		repository.deleteById((long) 5);
    }   
}
