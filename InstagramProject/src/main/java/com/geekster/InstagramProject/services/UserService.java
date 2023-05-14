package com.geekster.InstagramProject.services;

import com.geekster.InstagramProject.dto.UserSignInInput;
import com.geekster.InstagramProject.dto.UserSignUpInput;
import com.geekster.InstagramProject.dto.UserSignUpSignInOutput;
import com.geekster.InstagramProject.models.User;
import com.geekster.InstagramProject.models.UserAuthenticationToken;
import com.geekster.InstagramProject.repositories.IUserRepo;
import com.geekster.InstagramProject.repositories.IUserAuthTokenRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    UserAuthTokenService userAuthTokenService;

    public UserSignUpSignInOutput registerUser(UserSignUpInput userSignUpInput) {
        boolean emailIsPresent = iUserRepo.existsByEmail(userSignUpInput.getEmail());

        if(emailIsPresent){
            return new UserSignUpSignInOutput("email already exists ,Kindly login..!",null);
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(userSignUpInput.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
        }

        User user = new User(userSignUpInput.getFirstName(), userSignUpInput.getLastName(),
                            userSignUpInput.getAge(),userSignUpInput.getEmail(),
                            userSignUpInput.getPhoneNumber(),encryptedPassword);

//        save user in database through IUserRepo class
        iUserRepo.save(user);

        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user);
//        save token in database through UserAuthTokenService class
        userAuthTokenService.saveAuthorisedToken(userAuthenticationToken);

        return new UserSignUpSignInOutput("Registered user",userAuthenticationToken.getToken());
    }

    private String encryptPassword(String password) throws Exception{

        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(password.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested).toString();
    }

    public boolean existsByEmail(String email) {
        return iUserRepo.existsByEmail(email);
    }

    public UserSignUpSignInOutput loginUser(UserSignInInput userSignInInput) {

        User user = iUserRepo.findByEmail(userSignInInput.getEmail());

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(userSignInInput.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(encryptedPassword.equals(user.getPassword())) {
            UserAuthenticationToken authToken = userAuthTokenService.getUserAuthenticationTokenByUser(user);
            return new UserSignUpSignInOutput("login successfully..!",authToken.getToken());
        }

        return new UserSignUpSignInOutput("Invalid User..!! try again later",null);
    }

    public boolean tokenIsAuthorised(String email,String token,String newPassword) {
        User user = iUserRepo.findByEmail(email);
        UserAuthenticationToken authenticationToken = userAuthTokenService.getUserAuthenticationTokenByUser(user);

        if(!token.equals(authenticationToken.getToken())){
            return false;
        }

//       firstly encrypt your newPassword
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(newPassword);
        }catch (Exception e) {
            e.printStackTrace();
        }

        user.setPassword(encryptedPassword);
        iUserRepo.save(user);
        return true;
    }

    public User findUserById(Long userId) {
        return iUserRepo.findById(userId).orElse(null);
    }
}
