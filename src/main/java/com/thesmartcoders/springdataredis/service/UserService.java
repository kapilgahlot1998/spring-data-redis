package com.thesmartcoders.springdataredis.service;

import com.thesmartcoders.springdataredis.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

@Service
@Slf4j
public class UserService {

    private static final List<User> DATABASE = new ArrayList<>(Arrays.asList(
            new User("Kapil", "123", 29),
            new User("Bhawani", "1234", 19),
            new User("Nikhil", "12345", 49),
            new User("Devansh", "123456", 39)
    ));


    @Cacheable(cacheNames = "User", key = "#user.userName")
    public User createUser(User user) {
        out.println("Saving User in the DB");
        DATABASE.add(user);
        return user;
    }

    @Cacheable(cacheNames = "User", key = "#name")
    public User getUser(String name) {
        out.println("Fetching User from the DB");
        return DATABASE.stream().filter(user -> user.getUserName().equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException("NO User found with user name"));
    }


    @CachePut(cacheNames = "User", key = "#user.userName")
    public User updateUserAge(User user) {
        User user1 = DATABASE.stream().filter(u -> u.getUserName().equals(user.getUserName()))
                .findFirst().orElseThrow(() -> new RuntimeException("NO User found with user name"));
        user1.setAge(user.getAge());
        return user1;
    }

    @CacheEvict(cacheNames = "User")
    public void deleteUser(String name) {
        DATABASE.removeIf(user -> user.getUserName().equals(name));
    }

}
