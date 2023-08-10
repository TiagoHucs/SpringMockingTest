package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public void save(User user){
        System.out.println("Really save");
    }
}
