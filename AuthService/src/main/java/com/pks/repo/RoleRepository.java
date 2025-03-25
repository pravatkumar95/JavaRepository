package com.pks.repo;

import com.pks.model.Role;
import com.pks.model.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    public Optional<Role> findByRole(UserRole userRole) ;
}
