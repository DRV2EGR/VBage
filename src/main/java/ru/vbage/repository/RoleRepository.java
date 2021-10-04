package ru.vbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbage.entity.Role;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
