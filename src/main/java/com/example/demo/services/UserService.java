package com.example.demo.services;

import com.example.demo.services.dto.LoginDto;
import com.example.demo.entities.User;

import java.util.List;


public interface UserService {
    public User createUser(User user);
    public List<User> search();
    public User getUserById(Integer id);
    public User loginUser(LoginDto login) throws Exception;
    public User updateUser( User updateInfo);
}
