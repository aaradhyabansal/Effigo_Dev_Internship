package com.RoleBasedAccess.Ed.Assign.Repository;

import com.RoleBasedAccess.Ed.Assign.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findRoleByName(String role);
}
