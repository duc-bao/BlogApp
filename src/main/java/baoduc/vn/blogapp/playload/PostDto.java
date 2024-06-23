package baoduc.vn.blogapp.playload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;

    // title not null or empty
    // title should have at 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at 2 characters")
    private String title;
    // description  show not null or empty
    // have at 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at 10 characters")
    private String description;
    // content should not be null or empty
    @NotEmpty

    private String content;
    private Set<CommentDto> comments;
}
