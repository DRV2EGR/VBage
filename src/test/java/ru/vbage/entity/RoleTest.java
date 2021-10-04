package ru.vbage.entity;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

class RoleTest {
    @Test
    void testCanEqual() {
        assertFalse((new Role()).canEqual("Other"));
    }

    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setName("Name");
        assertEquals("Name", actualRole.getName());
        assertEquals("Role(name=Name)", actualRole.toString());
    }

    @Test
    void testEquals() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        assertFalse(role.equals(null));
    }

    @Test
    void testEquals2() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        assertFalse(role.equals("Different type to Role"));
    }
}

