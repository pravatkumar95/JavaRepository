package com.pks.repo;

import com.pks.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    public boolean  existsByname(String name);
    public Optional<User> findByname(String name);
}
