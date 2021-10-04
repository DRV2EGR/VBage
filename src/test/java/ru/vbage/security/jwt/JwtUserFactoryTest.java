package ru.vbage.security.jwt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import ru.vbage.entity.Role;
import ru.vbage.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUserFactoryTest {
    @Test
    void testCreate() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setActivationCode("Activation Code");
        user.setCreatedActivationCode(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setId(123L);
        user.setFriends(new ArrayList<User>());
        user.setPhoneNumber("4105551212");
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        JwtUser actualCreateResult = JwtUserFactory.create(user);
        Collection<? extends GrantedAuthority> authorities = actualCreateResult.getAuthorities();
        assertEquals(2, authorities.size());
        assertEquals("iloveyou", actualCreateResult.getPassword());
        assertEquals("janedoe", actualCreateResult.getUsername());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(1).toString());
        assertEquals("Name", ((List<? extends GrantedAuthority>) authorities).get(0).toString());
    }
}

