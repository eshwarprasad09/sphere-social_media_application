package com.eshwarprasad.sphere.service.impl;

import com.eshwarprasad.sphere.entity.Comment;
import com.eshwarprasad.sphere.entity.Post;
import com.eshwarprasad.sphere.exception.ResourceNotFoundException;
import com.eshwarprasad.sphere.exception.SphereApiException;
import com.eshwarprasad.sphere.payload.CommentDto;
import com.eshwarprasad.sphere.repository.CommentRepository;
import com.eshwarprasad.sphere.repository.PostRepository;
import com.eshwarprasad.sphere.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    private Post getPost(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","id", postId));
    }
    private Comment getComment(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","id", commentId));
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = getPost(postId);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId).stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
    }
    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrieve post entity by id
        Post post = getPost(postId);
        //retrieve comment entity by id
        Comment comment = getComment(commentId);
        if(Objects.equals(post.getId(), comment.getPost().getId())) return modelMapper.map(comment,CommentDto.class);
        else throw new SphereApiException(HttpStatus.BAD_GATEWAY, "Comment does not belongs to post");
    }
    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        //retrieve post entity by id
        Post post = getPost(postId);
        //retrieve comment entity by id
        Comment comment = getComment(commentId);
        if(Objects.equals(post.getId(), comment.getPost().getId())){
            comment.setName(commentDto.getName());
            comment.setEmail(comment.getEmail());
            comment.setBody(comment.getBody());
            return modelMapper.map(commentRepository.save(comment),CommentDto.class);
        }
        throw new SphereApiException(HttpStatus.BAD_GATEWAY, "Comment does not belongs to post");
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrieve post entity by id
        Post post = getPost(postId);
        //retrieve comment entity by id
        Comment comment = getComment(commentId);
        if(Objects.equals(post.getId(), comment.getPost().getId())) commentRepository.delete(comment);
    }
}
