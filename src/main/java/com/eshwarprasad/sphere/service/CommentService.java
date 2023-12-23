package com.eshwarprasad.sphere.service;

import com.eshwarprasad.sphere.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(long postID,long commentId, CommentDto commentDto);
    void deleteComment(long postId, long commentId);

}
