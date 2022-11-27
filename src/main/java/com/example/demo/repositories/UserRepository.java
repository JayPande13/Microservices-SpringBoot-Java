package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer>  {

    public User findByEmailAndPassword(String email, String password) throws Exception;;


}
