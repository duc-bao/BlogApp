package baoduc.vn.blogapp.dao;

import baoduc.vn.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "posts")
public interface PostRepository extends JpaRepository<Post, Integer> {
}
