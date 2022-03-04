package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//Create a UserRepository interface and inject it into the PostController:
public interface UserRepository extends JpaRepository <User, Long>{
//    User findByUsername(String username);
    User findByUsername(String testUser);

}







































//    @Query("from Post a where a.id = ?1")
//    Post getPostById(long id);
//
//    @Query("from Post a where a.body like %:term%")
//    Post getByBody(@Param("term") String term);