package baoduc.vn.blogapp.service.post;

import baoduc.vn.blogapp.entity.Post;
import baoduc.vn.blogapp.playload.PostDto;

import java.util.List;


public interface PostService {
  PostDto createPost(PostDto post);
  List<PostDto> findAllPost();
 // PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
