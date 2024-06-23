package baoduc.vn.blogapp.service.comment;

import baoduc.vn.blogapp.dao.CommentRepository;
import baoduc.vn.blogapp.dao.PostRepository;
import baoduc.vn.blogapp.entity.Comment;
import baoduc.vn.blogapp.entity.Post;
import baoduc.vn.blogapp.exception.BlogAPIException;
import baoduc.vn.blogapp.exception.ResourceNotFoundException;
import baoduc.vn.blogapp.playload.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements  CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(int postId, CommentDto commentDto) {
        Comment comment = maptoEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment =  commentRepository.save(comment);
        return maptopDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(int postId) {
        List<Comment> comments  = commentRepository.findByPostId(postId);
        return  comments.stream().map(comment ->  maptopDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByIdComment(int postId, int idComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(idComment).orElseThrow(() -> new ResourceNotFoundException("comment", "id", idComment));
        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment dose not belong to post");
        }
        return maptopDTO(comment);

    }

    @Override
    public CommentDto updateCommentById(int postId, int idComment,CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(idComment).orElseThrow(() -> new ResourceNotFoundException("comment", "id", idComment));
        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment dose not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updateComment = commentRepository.save(comment);
        return  maptopDTO(updateComment);
    }

    @Override
    public void deleteComment(int postId, int idComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(idComment).orElseThrow(() -> new ResourceNotFoundException("comment", "id", idComment));
        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment dose not belong to post");
        }
        commentRepository.delete(comment);
    }

    private CommentDto maptopDTO(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return  commentDto;
    }

    private  Comment maptoEntity(CommentDto commentDto){
       Comment comment = modelMapper.map(commentDto, Comment.class);
        return  comment;
    }
}
