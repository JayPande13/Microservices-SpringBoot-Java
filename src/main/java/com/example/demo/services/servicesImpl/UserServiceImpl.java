package com.example.demo.services.servicesImpl;

import com.example.demo.entities.Certification;
import com.example.demo.entities.User;
import com.example.demo.repositories.CertificationRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  CertificationRepository certificationRepository;

  ModelMapper modelMapper;

  @Override
  public User createUser(User user) {
    log.info("Creating User in service IMPL");
    User savedUser =null;
    try{
      savedUser = userRepository.save(user);
      return savedUser;
            }catch (Exception e){
      e.printStackTrace();
      return savedUser;
    }
  }
  @Override
  public List<User> search() {
    log.info("Returning User from Service IMPl");
    List<User> allUsers = userRepository.findAll();
    return allUsers;
  }

  @Override
  public User getUserById(Integer id) {
    log.info("Finding user in Service IMPL");
    User Founduser = null;
    try {
      Founduser =
          userRepository.findAll().stream().filter(user -> user.getId() == id).findFirst().get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Founduser;
  }

  @Override
  public User updateUser(User updateInfo) {
    log.info("updating user in Service IMPL");
    User updatedUser = null;
    try {
      if (userRepository.findById(updateInfo.getId()).isPresent()) {
        updatedUser = userRepository.save(updateInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return updatedUser;
  }
}
