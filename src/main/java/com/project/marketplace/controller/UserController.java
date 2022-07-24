package com.project.marketplace.controller;

import com.project.marketplace.dto.UserDto;
import com.project.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for working with users
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<UserDto> getAllUsers() {
        return userService.getListOfUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{firstName}/{lastName}")
    public UserDto getUser(@PathVariable String firstName, @PathVariable String lastName) {
        return userService.getUser(firstName, lastName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{firstName}/{lastName}")
    public UserDto updateUser(@PathVariable String firstName, @PathVariable String lastName, @RequestBody UserDto userDto) {
        return userService.updateUser(firstName, lastName, userDto);
    }

    @DeleteMapping(value = "/{firstName}/{lastName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String firstName, @PathVariable String lastName) {
        userService.deleteUser(firstName, lastName);
        return ResponseEntity.noContent().build();
    }

}