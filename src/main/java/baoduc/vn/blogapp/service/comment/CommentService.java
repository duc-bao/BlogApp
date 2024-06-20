package baoduc.vn.blogapp.service.comment;

import baoduc.vn.blogapp.playload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(int   postId, CommentDto commentDto);
    List<CommentDto> getCommentByPostId(int postId);
    CommentDto getCommentByIdComment(int postId, int idComment);
}
