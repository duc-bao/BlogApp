package baoduc.vn.blogapp.service.post;

import baoduc.vn.blogapp.entity.Post;

import java.util.List;


public interface PostService {
  Post createPost(Post post);
  List<Post> findAllPost();
 // PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
