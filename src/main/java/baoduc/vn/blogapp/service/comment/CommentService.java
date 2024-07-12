package baoduc.vn.blogapp.service.comment;

import java.util.List;

import baoduc.vn.blogapp.playload.CommentDto;

public interface CommentService {
    CommentDto createComment(int postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(int postId);

    CommentDto getCommentByIdComment(int postId, int idComment);

    CommentDto updateCommentById(int postId, int idComment, CommentDto commentRequest);

    void deleteComment(int postId, int idComment);
}
