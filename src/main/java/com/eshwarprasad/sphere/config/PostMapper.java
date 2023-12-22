package com.eshwarprasad.sphere.config;

import com.eshwarprasad.sphere.entity.Post;
import com.eshwarprasad.sphere.payload.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "content", target = "content")
    Post postDtoToPost(PostDto postDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "content", target = "content")
    PostDto postToPostDto(Post post);

}
