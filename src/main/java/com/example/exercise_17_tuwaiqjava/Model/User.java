package com.example.exercise_17_tuwaiqjava.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    //User class: ID , name , username , password, email ,role, age
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4,message = "name should be at least 4 letters")
    @Pattern(regexp = "^([A-Z]|[a-z])+$",message = "name should be letters only")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "length(name)>=4")
    private String name;
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4,message = "username should be at least 4 characters")
    @Pattern(regexp = "^([A-Z]|[a-z]|[0-9]|_)+$",message = "username should be letters, numbers or underscore('_' sign)")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "length(name)>=4")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "email like '%_@__%.__%'")
    private String email;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(user|admin)$",message = "role must be user or admin")
    @Column(columnDefinition = "varchar(5) not null")
    @Check(constraints = "role='user' or role='admin'")
    private String role;
    @NotNull(message = "age cannot be null")
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    @Check(constraints = "age>=0")
    private Integer age;

    //Validation :
    //ID :
    //Cannot be null
    //name :
    //Cannot be null    //Length more than4
    //username :
    //Cannot be null     //Length more than4   //must be unique
    //password :
    //Cannot be null
    //email :
    //Cannot be null     //must be valid email     //must be unique
    //role :
    //Cannot be null     //must be user or admin only
    //age :
    //Cannot be null     //must be integer
}
