package baoduc.vn.blogapp.playload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDTO {
    private int id;
    @NotBlank
    @Size(min = 3, message = "Name Category should have at 2 charracter")
    private String name;
    @NotBlank
    @Size(min = 10, message = "Description should have at 10 character")
    private String description;
    //private List<PostDto> posts;
}
