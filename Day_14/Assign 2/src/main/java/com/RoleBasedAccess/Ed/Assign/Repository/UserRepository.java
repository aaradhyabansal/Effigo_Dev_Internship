package com.RoleBasedAccess.Ed.Assign.Repository;

import com.RoleBasedAccess.Ed.Assign.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    void deleteById(long id);
}
