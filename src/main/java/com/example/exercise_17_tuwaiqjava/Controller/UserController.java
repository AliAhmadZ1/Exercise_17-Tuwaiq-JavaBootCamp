package com.example.exercise_17_tuwaiqjava.Controller;

import com.example.exercise_17_tuwaiqjava.ApiResponse.ApiResponse;
import com.example.exercise_17_tuwaiqjava.Model.User;
import com.example.exercise_17_tuwaiqjava.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-repository")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Endpoints :
        //• Get all the Users
        //• Add new User
        //• Update User
        //• Delete User
        //• Check if username and password are correct
        //• Get user by email
        //• Get All users with specific role
        //• Get All users with specific age or above

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        if (userService.getAllUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there no users"));
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody@Valid User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isAdded = userService.addUser(user);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("new user is added"));
        return ResponseEntity.status(400).body(new ApiResponse("username or email is already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid User user,Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("user is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found or username or email is already exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    //• Check if username and password are correct
    //• Get user by email
    //• Get All users with specific role
    //• Get All users with specific age or above
    @GetMapping("/check-exist/{username},{password}")
    public ResponseEntity checkExist(@PathVariable String username, @PathVariable String password){
        boolean isExist = userService.checkExist(username, password);
        if (isExist)
            return ResponseEntity.status(200).body(new ApiResponse("this username and password are correct"));
        return ResponseEntity.status(400).body(new ApiResponse("this username or password are not found or not related to each other"));
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User userByEmail = userService.getByEmail(email);
        if (userByEmail==null)
            return ResponseEntity.status(400).body(new ApiResponse("this email not found"));
        return ResponseEntity.status(200).body(userByEmail);
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity getByRole(@PathVariable String role){
        List<User> byRole = userService.getAllUsersWithRole(role);
        if (byRole.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("no users with ("+role+") role"));
        return ResponseEntity.status(200).body(byRole);
    }

    @GetMapping("/by-age-and-above/{age}")
    public ResponseEntity getByAgeAndAbove(@PathVariable Integer age){
        List<User> byAgeAndAbove = userService.getUserAgeAndAbove(age);
        if (byAgeAndAbove.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("every users less than ("+age+" age)"));
        return ResponseEntity.status(200).body(byAgeAndAbove);
    }



}
