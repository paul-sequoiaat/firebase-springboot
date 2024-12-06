package com.example.Firebase_POC.controller;

import com.example.Firebase_POC.dto.Response;
import com.example.Firebase_POC.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.example.Firebase_POC.dto.NotificationRequestDTO;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notification")
@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<Response> sendNotificationToDevice(
            @RequestBody NotificationRequestDTO notificationRequestDTO
            ) throws BadRequestException, FirebaseMessagingException {
        logger.info("START - send notification to user - {}", notificationRequestDTO.getUserId());
        notificationService.sendNotification(notificationRequestDTO);
        Response response = new Response();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Notification sent");
        logger.info("END - send notification to user - {}", notificationRequestDTO.getUserId());
        return ResponseEntity.ok().body(response);
    }
}
