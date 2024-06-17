package baoduc.vn.blogapp.controller;

import baoduc.vn.blogapp.entity.Post;
import baoduc.vn.blogapp.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public List<Post> getAllPost(){
        return  postService.findAllPost();
    }

}
