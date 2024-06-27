package baoduc.vn.blogapp.dao;

import baoduc.vn.blogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "categories")
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
