package baoduc.vn.blogapp.service.login;

import baoduc.vn.blogapp.playload.LoginDTO;
import baoduc.vn.blogapp.playload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
