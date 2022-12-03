package com.htpt.server.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htpt.server.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{
    public List<UserModel> findAll();

    public Optional<UserModel> findByEmailAndPassword(String email, String password);
}
