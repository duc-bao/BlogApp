package baoduc.vn.blogapp.playload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthRespionse {
    private String accessToken;
    private String tokenType = "Bearer";
}
