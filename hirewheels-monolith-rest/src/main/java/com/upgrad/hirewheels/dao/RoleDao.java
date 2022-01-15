package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByRoleId(int i);
}
