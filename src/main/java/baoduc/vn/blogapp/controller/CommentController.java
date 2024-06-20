package baoduc.vn.blogapp.controller;

import baoduc.vn.blogapp.playload.CommentDto;
import baoduc.vn.blogapp.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") int postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") int id){
        return commentService.getCommentByPostId(id);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") int postId,@PathVariable(value = "commentId") int commentId){
        CommentDto commentDto = commentService.getCommentByIdComment(postId,commentId);
        return new ResponseEntity<>( commentDto, HttpStatus.OK);
    }

}
