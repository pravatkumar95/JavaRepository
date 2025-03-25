package com.pks.controller;

import com.pks.dto.RegisterRequest;
import com.pks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private IUserService userService;
    public AuthController(IUserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody RegisterRequest registerRequest){
       String message= userService.userRegister(registerRequest);
       return new ResponseEntity<String>(message, HttpStatus.CREATED);

    }
    @GetMapping("/getMessage")
    public ResponseEntity<String> getMessage(){
      String message="Welcome to Centroxy";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/getStatus")
    public ResponseEntity<String> getStatus(){
        String message="Welcome to pravat";

        String message3="welcome to centroxy";
        
        return new ResponseEntity<>(message,HttpStatus.OK);
    }



}

