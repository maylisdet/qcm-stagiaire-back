package com.ai13qcm.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void getLabel() {
        Role r = new Role("ADMIN");
        assertTrue(r.getLabel().equals("ADMIN"));
    }


}