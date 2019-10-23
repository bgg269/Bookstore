package com.example.bookstoreproject.Web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstoreproject.domain.Book;
import com.example.bookstoreproject.domain.BookRepository;
import com.example.bookstoreproject.domain.Category;
import com.example.bookstoreproject.domain.CategoryRepository;



@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	
	// RESTful service to get all students
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get student by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String findAll() {
		List<Book> list = (List<Book>) repository.findAll();
		for (Book book : list) {
			System.out.println("id" + book.getId());
		}
		return null;
	}
	
	@RequestMapping(value = "/findC", method = RequestMethod.POST)
	public String findAllCategories() {
		List<Category> list = (List<Category>) crepository.findAll();
		for (Category category : list) {
			System.out.println("id" + category.getCategoryid());
		}
		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editbook";
	}
}
