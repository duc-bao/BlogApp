package baoduc.vn.blogapp.service.post;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import baoduc.vn.blogapp.playload.PostDto;
import baoduc.vn.blogapp.playload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto post);

    PostResponse findAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(int idPost);

    PostDto updatePost(PostDto postDto, int id);

    void deletePost(int id);

    ResponseEntity<PostResponse> search(int pageSize, int pageNo, String sortBy, String[] filter);

//    ResponseEntity<PostResponse> searchSpecification(Pageable pageable, String[] post, String[] comment);
    // PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
