package baoduc.vn.blogapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import baoduc.vn.blogapp.entity.Comment;

//
// @RepositoryRestResource(path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(int id);
}
