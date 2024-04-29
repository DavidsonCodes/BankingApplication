package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.AccountResource;
import com.example.BankingApplication.model.AccountUser;
import com.example.BankingApplication.model.LoginRequest;
import com.example.BankingApplication.model.LoginResponse;
import com.example.BankingApplication.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class AccountUserController {

    @Autowired
    private AccountUserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return userService.getAllAccountUsers();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id){
        return userService.getAccountUserById(id);
    }

    @GetMapping("/name")
    public ResponseEntity<AccountUser> getAccountUserByUsername(@RequestParam String username){
        return userService.getAccountUserByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser user){
        return userService.postAccountUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest){
        return userService.authenticate(loginRequest);
    }
    @PutMapping("/all/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser user){
        return userService.updateAccountUser(id, user);
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        return userService.deleteAccountUser(id);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountResource> getUserAccountResource(@PathVariable int id){
        AccountResource resource = new AccountResource();
        AccountUser user = userService.getAccountUserById(id).getBody();
        resource.setAccountUser(user);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAccountUserById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccountUser(id, user)).withRel("updateUser");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccountUser(id)).withRel("deleteUser");
        Link getAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllAccountUsers()).withRel("getAll");

        resource.add(selfLink, updateLink, deleteLink, getAll);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



}
