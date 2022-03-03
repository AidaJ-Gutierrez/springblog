package com.codeup.springblog;


import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AuthorRepository;
import com.codeup.springblog.repositories.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringblogApplication.class)
@AutoConfigureMockMvc
public class BooksIntegrationTest {


    private User testuser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    AuthorRepository authorDao;

    @Autowired
    BookRepository bookDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

         testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/ads"))
                .andReturn()
                .getRequest()
                .getSession();
    }


    //SANITY TEST
    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }

     //CRUD FUNCTIONALITY

    @Test
    public void testCreateBook() throws Exception{
        // MAKES A POST REQUEST TO /BOOK/CREATE AND EXPECT A REDIRECTION TO THE BOOK.
        this.mvc.perform(post("/books/create").with(csf())
                .session((MockHttpSession) httpSession)
                .param("title","Alicein Wonderlan")
                .param("genre_id", "6")
                .andExpect(status().is3xxRedirection()))
    }


    //READ
    @Test
    public void testShowBook() throws Exception{
        Book existingBook = bookDao.findAll.get(0);
        //Makes a get request to the /book/{id} and expect a redirection to the book show page.

        this.mvc.perform(get("/books/" + existingBook.getId()))
                .andExpect(status().isOk())
                //TEST THE DYNAMIC CONTET OF THE PAGE
                . andExpect((ResultMatcher) content().string(containsString(existingBook.getTitle())));
//    }



}
