package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.User;
import com.dsac.ecommer_backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser (@RequestBody User user) throws ResourceFoundException {
        return userService.saveUser(user);
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/total-users")
    public Long getTotalUsers() {
        return userService.totalUsers();
    }

    @GetMapping("/all")
    public Page<User> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/last")
    public User lastUser() {
        return userService.lastUser();
    }

    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/register-date/{startDate}/{endDate}")
    public List<User> getUsersByRegisterDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByRegisterDate(startDate, endDate, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByRole(role, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/enabled")
    public List<User> getUsersByEnabled(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByEnabled(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/disabled")
    public List<User> getUsersByUnEnabled(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByUnEnabled(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/top-buyers")
    public List<User> getTopBuyers(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getTopBuyers(pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable UUID id,
                           @RequestPart("user") String userJson,
                           @RequestPart ("image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);

        return userService.updateUser(id, user, image);
    }

    @PutMapping("/update-role/{idUser}/{idRole}")
    public User updateUserRole(@PathVariable UUID idUser, @PathVariable Long idRole) {
        return userService.updateUserRole(idUser, idRole);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
