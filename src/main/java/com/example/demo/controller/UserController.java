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

@RestController
@RequestMapping(MicroservicesConstants.BASE_URL+"/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.info("creating user started in Controller");
        User newUser = null;
        System.out.println(user);
        newUser =userService.createUser(user);
        if(newUser == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getAll(){
        log.info("Search started in controller");
        List<User> searchUser = userService.search();
        if(searchUser.size() <=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(searchUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable Integer userId){
        log.info("Getting User of userId : " + userId);
        User user =userService.getUserById(userId);
        if(user ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser( @RequestBody User updateUserInfo){
        log.info("Updating user by userId : " + updateUserInfo.getId() );
        User updatedUser= null;
        try{
         updatedUser = userService.updateUser(updateUserInfo);
         return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
