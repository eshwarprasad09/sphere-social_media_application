package com.eshwarprasad.sphere.repository;

import com.eshwarprasad.sphere.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //custom query method
    List<Comment> findByPostId(long id);

}
