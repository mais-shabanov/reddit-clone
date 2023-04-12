package com.company.redditclone.repository;

import com.company.redditclone.model.Comment;
import com.company.redditclone.model.Post;
import com.company.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
