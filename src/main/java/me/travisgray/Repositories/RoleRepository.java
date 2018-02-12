package me.travisgray.Repositories;

import me.travisgray.Models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(String role);
}
