package com.example.exercise_17_tuwaiqjava.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-repository")
@RequiredArgsConstructor
public class UserController {

    //Endpoints :
        //• Get all the Users
        //• Add new User
        //• Update User
        //• Delete User
        //• Check if username and password are correct
        //• Get user by email
        //• Get All users with specific role
        //• Get All users with specific age or above
}
