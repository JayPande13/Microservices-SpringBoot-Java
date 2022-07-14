package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MicroservicesConstants.BASE_URL+"/user")
public class userController {

    @GetMapping("/all")
    public String createUser( ){
        return "Here is is";


    }
}
