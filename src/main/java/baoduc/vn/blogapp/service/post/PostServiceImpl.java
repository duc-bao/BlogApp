package baoduc.vn.blogapp.service.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import baoduc.vn.blogapp.dao.CategoriesRepository;
import baoduc.vn.blogapp.dao.PostRepository;
import baoduc.vn.blogapp.entity.Category;
import baoduc.vn.blogapp.entity.Post;
import baoduc.vn.blogapp.exception.ResourceNotFoundException;
import baoduc.vn.blogapp.playload.PostDto;
import baoduc.vn.blogapp.playload.PostResponse;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public PostServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto post) {
        Category category = categoriesRepository
                .findById(post.getIdcategory())
                .orElseThrow(() -> new ResourceNotFoundException("categori", "id", post.getIdcategory()));
        Post newPost = modelMapper.map(post, Post.class);
        newPost.setCategory(category);
        postRepository.save(newPost);
        PostDto post2 = convertEntityintoDTO(newPost);
        return post2;
    }

    @Override
    public PostResponse findAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postList = postRepository.findAll(pageable);
        System.out.println(postList);
        List<Post> listofPost = postList.getContent();

        List<PostDto> content =
                listofPost.stream().map(post -> convertEntityintoDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postList.getNumber());
        postResponse.setPageSize(postList.getSize());
        postResponse.setTotalElements(postList.getTotalElements());
        postResponse.setLast(postList.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(int idPost) {
        Post post =
                postRepository.findById(idPost).orElseThrow(() -> new ResourceNotFoundException("post", "id", idPost));
        return convertEntityintoDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        post.setCategory(post.getCategory());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(post.getDescription());
        Post updatePost = postRepository.save(post);
        return convertEntityintoDTO(updatePost);
    }

    @Override
    public void deletePost(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
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

    private Post convertDTOtoEntity(PostDto post) {
        Post post1 = modelMapper.map(post, Post.class);
        return post1;
    }
    // convert entity to DTO
    private PostDto convertEntityintoDTO(Post newPost) {
        PostDto postDto = modelMapper.map(newPost, PostDto.class);
        return postDto;
    }
}
