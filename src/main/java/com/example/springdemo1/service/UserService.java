package com.example.springdemo1.service;

import com.example.springdemo1.model.User;
import com.example.springdemo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan({"com.example.springdemo1.repository"})
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(int page,int sizePage){
        return userRepository.findAllUsers(PageRequest.of(page,sizePage, Sort.by("email")));
    }

    public User getUserById(String userId){
        return userRepository.findUserById(userId);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteUserById(userId);
    }

    public User updateUser(String id,User user){
        User user1=this.getUserById(id);
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return userRepository.save(user1);
    }
}
