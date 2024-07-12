package baoduc.vn.blogapp.service.post;

import baoduc.vn.blogapp.playload.PostDto;
import baoduc.vn.blogapp.playload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto post);

    PostResponse findAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(int idPost);

    PostDto updatePost(PostDto postDto, int id);

    void deletePost(int id);
    // PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
