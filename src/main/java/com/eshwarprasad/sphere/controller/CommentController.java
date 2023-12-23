package com.eshwarprasad.sphere.controller;

import com.eshwarprasad.sphere.payload.CommentDto;
import com.eshwarprasad.sphere.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eshwarprasad.sphere.utils.AppConstants.COMMENT_ID;
import static com.eshwarprasad.sphere.utils.AppConstants.ID;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{id}/comments")
    ResponseEntity<CommentDto> createComment(@PathVariable(name = ID) long postId,
                                             @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{id}/comments")
    ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(name = ID) long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
    }
    @GetMapping("/posts/{id}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable(name = ID) long postId, @PathVariable(name = COMMENT_ID) long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId,commentId));
    }

    @PutMapping("/posts/{id}/comments/{commentId}")
    @Valid
    ResponseEntity<CommentDto> updateComment(@PathVariable(name = ID) long postId, @PathVariable(name = COMMENT_ID) long commentId
                                             ,@Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId,commentId,commentDto));
    }
    @DeleteMapping("/posts/{id}/comments/{commentId}")
    ResponseEntity<String> deleteComment(@PathVariable(name = ID) long postId, @PathVariable(name = COMMENT_ID) long commentId){
        commentService.deleteComment(postId,commentId);
        return ResponseEntity.ok("deleted");
    }

}
