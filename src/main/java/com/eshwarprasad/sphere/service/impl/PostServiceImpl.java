package com.eshwarprasad.sphere.service.impl;

import com.eshwarprasad.sphere.config.PostMapper;
import com.eshwarprasad.sphere.entity.Post;
import com.eshwarprasad.sphere.exception.ResourceNotFoundException;
import com.eshwarprasad.sphere.payload.PostDto;
import com.eshwarprasad.sphere.repository.PostRepository;
import com.eshwarprasad.sphere.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return postRepository.findAll(pageable).getContent().stream().map(p -> modelMapper.map(p,PostDto.class)).toList();
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id",id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post postUpdated = postRepository.save(post);
        return PostMapper.INSTANCE.postToPostDto(postUpdated);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id",id));
        postRepository.delete(post);
    }
}
