package  com.THIS.capstonehope.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.THIS.capstonehope.security.models.ERole;
import com.THIS.capstonehope.security.models.Role;



public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
