package com.example.Firebase_POC.controller;

import com.example.Firebase_POC.dto.FirebaseRegistrationRequestDTO;
import com.example.Firebase_POC.dto.Response;
import com.example.Firebase_POC.dto.UserRequestDTO;
import com.example.Firebase_POC.service.UserService;
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

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        logger.info("START - create user");
        userService.createUser(userRequestDTO);
        Response response = new Response();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User created");
        logger.info("END - create user");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<Response> saveFirebaseToken(
            @RequestBody FirebaseRegistrationRequestDTO firebaseRegistrationRequestDTO
    ) throws BadRequestException {
        logger.info("START - save firebase token");
        userService.saveToken(firebaseRegistrationRequestDTO);
        Response response = new Response();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Firebase token saved");
        logger.info("END - save firebase token");
        return ResponseEntity.ok().body(response);
    }
}
