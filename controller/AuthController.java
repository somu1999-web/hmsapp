package com.hmsapp_test.controller;

import com.hmsapp_test.entity.User;
import com.hmsapp_test.payload.JwtToken;
import com.hmsapp_test.payload.LoginDto;
import com.hmsapp_test.payload.UserDto;
import com.hmsapp_test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        ResponseEntity<?> dto = userService.createUser(userDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwner(@RequestBody UserDto userDto) {
        ResponseEntity<?> dto = userService.createPropertyOwner(userDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    @PostMapping("/blog/sign-up")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody UserDto userDto) {
        ResponseEntity<?> dto = userService.createBlogManagerAccount(userDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String  token = userService.verifyLogin(loginDto);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setType("JWT");
        if (token != null){
            return new ResponseEntity<>(jwtToken , HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid" , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal User user){
        UserDto dto = userService.getUserProfile(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
