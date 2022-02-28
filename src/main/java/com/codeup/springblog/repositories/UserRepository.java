package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;



//Create a UserRepository interface and inject it into the PostController:
public interface UserRepository extends JpaRepository <Post, Long>{
}
