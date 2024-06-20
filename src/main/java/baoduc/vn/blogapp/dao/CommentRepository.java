package baoduc.vn.blogapp.dao;

import baoduc.vn.blogapp.entity.Comment;
import baoduc.vn.blogapp.playload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//
//@RepositoryRestResource(path = "comments")
public interface   CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(int id);
}
