package com.example.user_service.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean isUserValid(Long userId) {

        if (userId != null && (userId == 1L || userId == 2L || userId == 3L)) {
            return true;
        }
        return false;
    }
}