package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
