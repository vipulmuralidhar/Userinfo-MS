package com.vipsfoodcourt.userinfo.controller;


import com.vipsfoodcourt.userinfo.service.UserService;

import com.vipsfoodcourt.userinfo.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){

        return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.CREATED);
    }


    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){

        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }




}
