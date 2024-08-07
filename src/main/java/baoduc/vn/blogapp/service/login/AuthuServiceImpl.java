package baoduc.vn.blogapp.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import baoduc.vn.blogapp.dao.RoleRepository;
import baoduc.vn.blogapp.dao.UserRepository;
import baoduc.vn.blogapp.entity.Role;
import baoduc.vn.blogapp.entity.User;
import baoduc.vn.blogapp.exception.BlogAPIException;
import baoduc.vn.blogapp.playload.LoginDTO;
import baoduc.vn.blogapp.playload.RegisterDTO;
import baoduc.vn.blogapp.security.JwtTokenProvider;

@Service
public class AuthuServiceImpl implements AuthService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User resgisted succesfully";
    }
}
