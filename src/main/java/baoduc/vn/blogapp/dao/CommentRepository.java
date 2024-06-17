package baoduc.vn.blogapp.dao;

import baoduc.vn.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
