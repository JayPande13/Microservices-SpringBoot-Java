package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import com.example.demo.services.dto.BaseResponseDto;
import com.example.demo.services.dto.LoginDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MicroservicesConstants.BASE_URL + "/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto<User>> createUser(@RequestBody User user) {
        log.info("creating user started in Controller");
        User newUser = null;
        System.out.println(user);
        newUser = userService.createUser(user);
        if (newUser == null) {
            return new ResponseEntity<>(
                    BaseResponseDto.<User>builder().message("User Not Created")
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).content(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else {
            return new ResponseEntity<BaseResponseDto<User>>(
                    BaseResponseDto.<User>builder().message(HttpStatus.OK.toString())
                            .statusCode(HttpStatus.CREATED.value()).content(newUser).build(), HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getAll() {
        log.info("Search started in controller");
        List<User> searchUser = userService.search();
        if (searchUser.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(searchUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable Integer userId) {
        log.info("Getting User of userId : " + userId);
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto<User>> getById(@RequestBody LoginDto login) throws Exception {
        log.info("Getting User of userId : " + login);
        User loginMessage = userService.loginUser(login);
        if (loginMessage != null) {
            return new ResponseEntity<BaseResponseDto<User>>(
                    BaseResponseDto.<User>builder().message(HttpStatus.OK.toString())
                            .statusCode(HttpStatus.FOUND.value()).content(loginMessage).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<BaseResponseDto<User>>(
                    BaseResponseDto.<User>builder().message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .statusCode(HttpStatus.NO_CONTENT.value()).content(null).build(), HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User updateUserInfo) {
        log.info("Updating user by userId : " + updateUserInfo.getId());
        User updatedUser = null;
        try {
            updatedUser = userService.updateUser(updateUserInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
