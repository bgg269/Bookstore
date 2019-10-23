package com.example.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstoreproject.domain.Category;
import com.example.bookstoreproject.domain.CategoryRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
    private CategoryRepository repository;

    @Test
    public void findTitleShouldReturnBook() {
        List<Category> categories = repository.findByName("fiction");
        
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("fantasy");
    }
    
    @Test
    public void createNewCategory() {
    	Category category = new Category("BITE");
    	repository.save(category);
    	assertThat(category.getCategoryid()).isNotNull();
    }   
    @Test
    public void deleteCategory() {
    	System.out.println("category" + repository.findAll());
		repository.deleteById((long) 1);
    	System.out.println("category" + repository.findAll());
		repository.deleteById((long) 5);
    }   
}
