package ru.vbage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vbage.entity.RefreshToken;

/**
 * The interface Refresh token repository.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
