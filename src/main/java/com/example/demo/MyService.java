package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public void register(String name, Integer age){
        User user = new User();
        user.setName(name.toUpperCase());
        user.setAge(age);
        user.setRegisterDate(LocalDate.now());
        myRepository.save(user);
    }
}
