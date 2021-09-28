package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){ // transfer body/object to convert to json
        boolean result = userService.registerUser(user);
        if(result){
            return new ResponseEntity("User is registered", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("User is already existed");
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user, HttpServletRequest request){ // transfer body/object to convert to json
        boolean result = userService.loginUser(user);
        //String auth = request.authenticate("Authorized");
        if(result){
            return new ResponseEntity("User is logged in", HttpStatus.OK);
        }
        if(result){
           // return new ResponseEntity(auth, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User cannot sign in");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody User user, HttpServletRequest request){ // transfer body/object to convert to json
        boolean result = userService.logoutUser(user);
        String token = request.getHeader("Authorized");
        if(result && "token here".equals(token)){
            return new ResponseEntity("User is logged out", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("User cannot logout");
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody User user, HttpServletRequest request){ // transfer body/object to convert to json
        boolean result = userService.addUser(user);
        String token = request.getHeader("Authorized");
        if(result && "token here".equals(token)){
            return new ResponseEntity("User is created", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("User is already existed");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam User user, HttpServletRequest request){
        boolean result = userService.updateUser(user);
        String token = request.getHeader("Authorized");
        if(result && "token here".equals(token)){
            return  new ResponseEntity("User is updated", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User cannot be updated");
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam User user, HttpServletRequest request){
        boolean result = userService.deleteUser(user);
        String token = request.getHeader("Authorized");
        if(result && "token here".equals(token)){
            return  new ResponseEntity("User is deleted", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User cannot be deleted");
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam int id, HttpServletRequest request){
        String token = request.getHeader("Authorized");
        if("token here".equals(token)) {
            return ResponseEntity.ok(userService.read(id));
        }
        return new ResponseEntity("body credential", HttpStatus.UNAUTHORIZED);
    }
}
