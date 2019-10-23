package com.example.bookstoreproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bookstoreproject.domain.User;
import com.example.bookstoreproject.domain.UserRepository;

public class UserRepositoryTest {

	@Autowired
    private UserRepository repository;

    @Test
    public void findTitleShouldReturnUser() {
    	User users = repository.findByUsername("user");
        
        assertThat(users).hasSize(1);
        assertThat(users.getUsername()).isEqualTo("action");
    }
    
    @Test
    public void createNewUser() {
    	User user = new User("user2", "$2y$10$IoEOaTXS0hER3g0Feod1euyx.gOEg7uVD5nPYdKWb6wVtT.lcuL.2", "user2@haaga-helia.fi", "USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }   
    @Test
    public void deleteUser() {
    	System.out.println("users" + repository.findAll());
		repository.deleteById((long) 1);
    	System.out.println("users" + repository.findAll());
		repository.deleteById((long) 5);
    }   
}
