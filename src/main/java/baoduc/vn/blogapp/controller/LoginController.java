package baoduc.vn.blogapp.controller;

import baoduc.vn.blogapp.playload.JWTAuthRespionse;
import baoduc.vn.blogapp.playload.LoginDTO;
import baoduc.vn.blogapp.service.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private AuthService authService;
        @PostMapping( value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthRespionse> login(@RequestBody   LoginDTO loginDTO){
        String token =  authService.login(loginDTO);
        JWTAuthRespionse jwtAuthRespionse = new JWTAuthRespionse();
        jwtAuthRespionse.setAccessToken(token);
        return  ResponseEntity.ok(jwtAuthRespionse);
    }
}
