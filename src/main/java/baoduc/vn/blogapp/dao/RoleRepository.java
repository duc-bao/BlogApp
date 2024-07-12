package baoduc.vn.blogapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import baoduc.vn.blogapp.entity.Role;

@RepositoryRestResource(path = "roles")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
