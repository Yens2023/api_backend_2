package api.java.backend.repository.security;
import api.java.backend.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>  {
    Role findRoleByName(String name);
    Boolean existsByName(String name);
}
