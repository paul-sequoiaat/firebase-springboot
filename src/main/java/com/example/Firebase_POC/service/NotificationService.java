package com.example.Firebase_POC.service;

import com.example.Firebase_POC.entity.User;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.example.Firebase_POC.dto.NotificationRequestDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UserService userService;

    public void sendNotification(NotificationRequestDTO requestDTO) throws BadRequestException, FirebaseMessagingException {
        User user = userService.getUser(requestDTO.getUserId());
        firebaseService.sendNotification(user.getToken(), requestDTO.getTitle(), requestDTO.getBody());
    }
}
