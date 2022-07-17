package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(MicroservicesConstants.BASE_URL+"/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        log.info("creating user started in Controller");
        return userService.createUser(user);

    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getAll(){
        log.info("Search started in controller");
        List<User> searchUser = userService.search();
        if(searchUser.size() <=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(searchUser));
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable Integer userId){
        log.info("Getting User of userId : " + userId);
        Optional<User> user =userService.getUserById(userId);
        return user;
    }

    @PutMapping("/update")
    public User updateUser( @RequestBody User updateUserInfo){
        log.info("Updating user by userId : " + updateUserInfo.getId() );
        User Updateduser = userService.updateUser(updateUserInfo);
        return Updateduser;
    }
}
