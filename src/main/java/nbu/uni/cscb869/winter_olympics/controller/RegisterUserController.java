package nbu.uni.cscb869.winter_olympics.controller;

import nbu.uni.cscb869.winter_olympics.dto.RegisterUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController("/register")
public class RegisterUserController {

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {


        return ResponseEntity.ok("User is successfully registered in KeyCloak.");
    }
}
