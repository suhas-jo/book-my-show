package com.suhas.bms.service;

import com.suhas.bms.models.User;
import com.suhas.bms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
       return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.getReferenceById(id);
    }
}
