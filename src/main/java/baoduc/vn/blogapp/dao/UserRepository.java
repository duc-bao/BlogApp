package baoduc.vn.blogapp.dao;

import baoduc.vn.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
    public  Optional<User> findByUsernameOrEmail(String email, String username);
    public  Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
