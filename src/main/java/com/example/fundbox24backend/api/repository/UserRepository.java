package com.example.fundbox24backend.api.repository;

import com.example.fundbox24backend.api.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
