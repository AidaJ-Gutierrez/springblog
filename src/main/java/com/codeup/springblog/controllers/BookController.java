package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private BookRepository bookDao;
//    private AuthorRepository authorsDao;
//   private GenreRepository genreDao;


    public BookController(BookRepository bookDao){
        this.bookDao= bookDao;
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("allBooks", bookDao.findAll());
        return "books";
    }
//
//    @GetMapping("/books/create")
//    public String showCreateForm(Model model){
//        model.addAttribute("book", new Book());
//        return "books/create"; // create new folder in templates
//    }
//    @PostMapping("/books/crreate")
//    public String createBook(@ModelAttribute Book book){
//        book.setAuthor(authorsDao.getById(1L));
//
//    }

}
