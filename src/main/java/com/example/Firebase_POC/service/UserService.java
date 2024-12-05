package com.example.Firebase_POC.service;

import com.example.Firebase_POC.dto.FirebaseRegistrationRequestDTO;
import com.example.Firebase_POC.dto.UserRequestDTO;
import com.example.Firebase_POC.entity.User;
import com.example.Firebase_POC.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        userRepository.save(user);
    }

    public void saveToken(FirebaseRegistrationRequestDTO requestDTO) throws BadRequestException {
        User user = getUser(requestDTO.getUserId());
        user.setToken(requestDTO.getFirebaseRegistrationToken());
        userRepository.save(user);
    }

    public User getUser(String userId) throws BadRequestException {
        return userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User does not exist"));
    }
}
