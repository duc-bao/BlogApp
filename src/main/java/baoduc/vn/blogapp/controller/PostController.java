package baoduc.vn.blogapp.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import baoduc.vn.blogapp.playload.PostDto;
import baoduc.vn.blogapp.playload.PostResponse;
import baoduc.vn.blogapp.service.post.PostService;
import baoduc.vn.blogapp.utils.AppContrants;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getById(@PathVariable(value = "postId") int idPost) {
        return new ResponseEntity<>(postService.getPostById(idPost), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppContrants.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppContrants.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppContrants.DEFAULT_SORT_BY, required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppContrants.DEFAULT_SORT_DIRECTION, required = false)
                    String sortDir) {
        return postService.findAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(value = "id") int id, @Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Succes", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PostResponse> searchPost(
            @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(required = false, value = "search") String... filter) {
        return postService.search(pageSize, pageNo, sortBy, filter);
    }
//    @GetMapping("/search-specification")
//    public  ResponseEntity<PostResponse> searchPostSpecification(Pageable pageable,
//                                                                 @RequestParam(required = false) String [] post,
//                                                                 @RequestParam(required = false) String[] comment){
//        return postService.searchSpecification(pageable, post, comment);
//    }
}
