package baoduc.vn.blogapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import baoduc.vn.blogapp.entity.Category;

@RepositoryRestResource(path = "categories")
public interface CategoriesRepository extends JpaRepository<Category, Integer> {}
