package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("from Post a where a.id = ?1")
    Post getPostById(long id);

    @Query("from Post a where a.body like %:term%")
    Post getByBody(@Param("term") String term);

}

