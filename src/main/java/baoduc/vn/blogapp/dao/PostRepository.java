package baoduc.vn.blogapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import baoduc.vn.blogapp.entity.Post;

@RepositoryRestResource(path = "posts")
public interface PostRepository extends JpaRepository<Post, Integer> , JpaSpecificationExecutor<Post> {}
