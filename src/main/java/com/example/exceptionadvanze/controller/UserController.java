package com.example.exceptionadvanze.controller;

import com.example.exceptionadvanze.dto.User;
import com.example.exceptionadvanze.exception.BusinessException;
import com.example.exceptionadvanze.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        if (user.getEmail().equals("") || user.getEmail() == null) {
            throw new RequestException("P-401", "Email is required");
        }
        if (user.getName().equals("") || user.getName() == null) {
            throw new RequestException("P-402", "User name is required");
        }

        if (user.getEmail().equals("jorge@gmail.com")) {
            throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "Email already exist.");
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
