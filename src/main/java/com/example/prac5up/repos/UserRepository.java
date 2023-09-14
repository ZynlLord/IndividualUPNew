package com.example.prac5up.repos;

import com.example.prac5up.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
