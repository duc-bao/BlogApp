package baoduc.vn.blogapp.controller;

import baoduc.vn.blogapp.playload.RegisterDTO;
import baoduc.vn.blogapp.service.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
      String response =  authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
