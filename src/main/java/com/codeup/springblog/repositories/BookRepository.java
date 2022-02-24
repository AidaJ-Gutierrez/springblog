package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
