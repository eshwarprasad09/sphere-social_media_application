package com.eshwarprasad.sphere.controller;

import com.eshwarprasad.sphere.payload.PostDto;
import com.eshwarprasad.sphere.payload.PostResponse;
import com.eshwarprasad.sphere.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eshwarprasad.sphere.utils.AppConstants.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping()
    public  ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNo", defaultValue = PAGE_NUMBER, required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = PAGE_SIZE, required = false) int pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = SORT_BY, required = false) String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = SORT_DIR, required = false) String sortDir){
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = ID) Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = ID) Long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = ID) long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("deleted");
    }

}
