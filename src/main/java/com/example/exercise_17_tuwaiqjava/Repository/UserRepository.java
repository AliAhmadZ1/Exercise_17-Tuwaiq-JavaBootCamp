package com.example.exercise_17_tuwaiqjava.Repository;

import com.example.exercise_17_tuwaiqjava.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.username=?1 and u.password=?2")
    User checkExist(String username, String password);

    User findUserByEmail(String email);

    List<User> findUserByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> getUserAgeAndAbove(Integer age);


}
