package com.example.springdemo1.controller;

import com.example.springdemo1.model.User;
import com.example.springdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@ComponentScan({"com.example.springdemo1.service"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public @ResponseBody Page<User> getAllUsers(
            @RequestParam(name = "page",defaultValue = "0",required = false) int page,
            @RequestParam(name = "size",defaultValue = "15",required = false)int pageSize){
        return userService.getAllUsers(page, pageSize);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody User getUserById(@PathVariable(value = "id",required = true)String id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users",consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User saveNewUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id",required = true)String id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("success");
    }

    @PutMapping(value = "/users/{id}",consumes ={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable(value = "id")String id,@Valid @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        Map<String,String> errorResponse=new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return errorResponse;
    }



}
