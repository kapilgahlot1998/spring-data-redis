package com.thesmartcoders.springdataredis.controller;

import com.thesmartcoders.springdataredis.dto.User;
import com.thesmartcoders.springdataredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //curd api

    @PostMapping(value = "/save")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping(value = "/get")
    public User getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

    @PutMapping(value = "/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUserAge(user);
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }




}
