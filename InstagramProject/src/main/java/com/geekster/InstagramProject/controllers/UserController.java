package com.geekster.InstagramProject.controllers;

import com.geekster.InstagramProject.dto.UserSignInInput;
import com.geekster.InstagramProject.dto.UserSignUpInput;
import com.geekster.InstagramProject.dto.UserSignUpSignInOutput;
import com.geekster.InstagramProject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserSignUpSignInOutput> signUp(@Valid @RequestBody UserSignUpInput userSignUpInput) {

        UserSignUpSignInOutput response = userService.registerUser(userSignUpInput);
        HttpStatus status;
        if (response.getToken() == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserSignUpSignInOutput> signIn(@Valid @RequestBody UserSignInInput userSignInInput) {

        boolean isPresentEmail = userService.existsByEmail(userSignInInput.getEmail());
        HttpStatus status;
        if (!isPresentEmail) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(new UserSignUpSignInOutput("User Invalid,kindly sign up", null), status);
        }

        UserSignUpSignInOutput response = userService.loginUser(userSignInInput);
        status = HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/forgotPassword/byEmailId/{email}/{token}/{newPassword}")
    public ResponseEntity<UserSignUpSignInOutput> updatePassword(@Valid @PathVariable String email,@PathVariable String token,@Valid @PathVariable String newPassword) {
        boolean isPresentEmail = userService.existsByEmail(email);

        HttpStatus status = null;
        if(!isPresentEmail){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(new UserSignUpSignInOutput("Email does not exist",token),status);
        }

        if(userService.tokenIsAuthorised(email,token,newPassword)){
            status = HttpStatus.OK;
            return new ResponseEntity<>(new UserSignUpSignInOutput("User's password updated succesfully",token),status);
        }
        status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new UserSignUpSignInOutput("Invalid token...!!",token),status);
    }



}
