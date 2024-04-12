package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.repositories.UserRepository;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        return repository.findById(id).get();
    }

    public User update(User user){
        User userFromData = repository.findByEmail(user.getEmail()).orElseThrow(() -> new RuntimeException("User not Found"));

        userFromData.setName(user.getName());
        userFromData.setEmail(user.getEmail());

        return repository.save(userFromData);

    }

    public void delete(String id){
        User userEntiy = repository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));

        repository.deleteById(userEntiy.getId());
    }


}
