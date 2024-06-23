package baoduc.vn.blogapp.playload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private  int id;
    @NotEmpty(message = "Name Comment should not be null or empty")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Body should have at 10 characters")
    private String body;
}
