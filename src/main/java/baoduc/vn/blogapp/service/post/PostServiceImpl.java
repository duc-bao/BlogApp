package baoduc.vn.blogapp.service.post;

import baoduc.vn.blogapp.dao.PostRepository;
import baoduc.vn.blogapp.entity.Post;
import baoduc.vn.blogapp.playload.PostDto;
import baoduc.vn.blogapp.playload.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements  PostService{
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post createPost(Post post) {
        Post newPost  = postRepository.save(convertDTOtoEntity(post));
        Post post2 = convertEntityintoDTO(newPost);
        return  post2;
    }

    @Override
    public List<Post> findAllPost() {
        List<Post> postList = postRepository.findAll();
        return  postList.stream().map(post -> convertEntityintoDTO(post)).collect(Collectors.toList());
    }

//    @Override
//    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        // create Pageable instance
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//
//        Page<Post> posts = postRepository.findAll(pageable);
//
//        // get content for page object
//        List<Post> listOfPosts = posts.getContent();
//
//        List<PostDto> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//
//        PostResponse postResponse = new PostResponse();
//        postResponse.setContent(content);
//        postResponse.setPageNo(posts.getNumber());
//        postResponse.setPageSize(posts.getSize());
//        postResponse.setTotalElements(posts.getTotalElements());
//        postResponse.setTotalPages(posts.getTotalPages());
//        postResponse.setLast(posts.isLast());
//
//        return postResponse;
//    }

    private Post convertDTOtoEntity(Post post){
        Post post1 = new Post();
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        post1.setContent(post.getContent());
        return post1;
    }
    // convert entity to DTO
        private Post convertEntityintoDTO(Post newPost){
        Post post2 = new Post();
        post2.setId(newPost.getId());
        post2.setTitle(newPost.getTitle());
        post2.setDescription(newPost.getDescription());
        post2.setContent(newPost.getContent());
        return post2;
    }
}
