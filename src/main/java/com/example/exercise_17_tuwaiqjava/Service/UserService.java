package com.example.exercise_17_tuwaiqjava.Service;

import com.example.exercise_17_tuwaiqjava.Model.User;
import com.example.exercise_17_tuwaiqjava.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean addUser(User user){
//        User user1 = userRepository.findUserById(user.getId());
//        if (user1==null||user1.getUsername().equals(user.getUsername())||user1.getEmail().equals(user.getEmail()))
//            return false;
        if (userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail())==null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean updateUser(Integer id,User user){
//        User userCheck = userRepository.findUserById(user.getId());
//        if (userCheck==null||userCheck.getUsername().equals(user.getUsername())||userCheck.getEmail().equals(user.getEmail()))
//            return false;
        User oldUser = userRepository.findUserById(id);
        if (oldUser==null)
            return false;
        User check = userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (check==null||!check.getEmail().equals(user.getEmail())||!check.getUsername().equals(user.getUsername())) {
            oldUser.setAge(user.getAge());
            oldUser.setRole(user.getRole());
            oldUser.setName(user.getName());
            oldUser.setPassword(user.getPassword());
            oldUser.setUsername(user.getUsername());
            oldUser.setEmail(user.getEmail());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (user==null)
            return false;
        userRepository.delete(user);
        return true;
    }

    //• Check if username and password are correct
    //• Get user by email
    //• Get All users with specific role
    //• Get All users with specific age or above

    public Boolean checkExist(String username, String password){
        User user = userRepository.checkExist(username, password);
        if (user ==null)
            return false;
        return true;
    }

    public User getByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> getAllUsersWithRole(String role){
        return userRepository.findUserByRole(role);
    }

    public List<User> getUserAgeAndAbove(Integer age){
        return userRepository.getUserAgeAndAbove(age);
    }



}
