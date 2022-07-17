package com.example.demo.services.servicesImpl;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public User createUser(User user){
        log.info("Creating User in service IMPL");
         User savedUser = userRepository.save(user);
        System.out.println(savedUser);
        return savedUser;
    }

    @Override
    public List<User> search(){
        log.info("Returning User from Service IMPl");
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

  @Override
  public Optional<User> getUserById(Integer id) {
    log.info("Finding user in Service IMPL");
      Optional<User> Founduser = Optional.of(userRepository.findAll().stream().filter(user -> user.getId() == id).findFirst().get());
//    Optional<User> user = userRepository.findById(id);
    return Founduser;
    }

    @Override
    public User updateUser (User updateInfo){
        log.info("updating user in Service IMPL");
        User updatedUser= null;
      if(userRepository.findById(updateInfo.getId()).isPresent()){
         updatedUser = userRepository.save(updateInfo);
      }
      return updatedUser;
    }



}
