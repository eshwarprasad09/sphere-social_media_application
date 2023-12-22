package com.eshwarprasad.sphere.service;

import com.eshwarprasad.sphere.payload.PostDto;
import com.eshwarprasad.sphere.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto post);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(Long id);

}
