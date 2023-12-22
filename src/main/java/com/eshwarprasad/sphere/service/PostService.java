package com.eshwarprasad.sphere.service;

import com.eshwarprasad.sphere.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post);
    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(Long id);

}
