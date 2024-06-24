package baoduc.vn.blogapp.playload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String email;
    private String name;
    private String password;
}
