package com.example.BankingApplication.service;

import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.model.LoginRequest;
import com.example.BankingApplication.model.LoginResponse;
import com.example.BankingApplication.model.Role;
import com.example.BankingApplication.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class AccountUserService {

    @Autowired
    private AccountUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id){
        return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<LoginResponse> authenticate(LoginRequest request){

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if( auth != null ){
            AccountUser user = userRepository.getByUsername(request.getUsername());
            String token = jwtService.createToken(user);
            return new ResponseEntity<>(LoginResponse.builder().user(user).token(token).build(), HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<AccountUser> getAccountUserByUsername(@RequestParam String username){
        return new ResponseEntity<>(userRepository.getByUsername(username), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }


    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser user){
        AccountUser updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setMiddleName(user.getMiddleName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        AccountUser deletedUser = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
