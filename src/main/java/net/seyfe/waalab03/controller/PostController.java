package net.seyfe.waalab03.controller;


import net.seyfe.waalab03.domain.Post;
import net.seyfe.waalab03.domain.dto.PostDto;
import net.seyfe.waalab03.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> getPosts(){
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId")Long postId){

        return ResponseEntity.ok(postService.findById(postId));
    }

    @GetMapping("/auth")
    public List<PostDto> printSomething(@RequestParam("author") String author){

        return postService.getPostByAuthor(author);
    }

    @PostMapping
    public void savePost(@RequestBody Post post){
        postService.save(post);
    }

    @DeleteMapping("/{postId}")
    public void deleteById(@PathVariable("postId")Long postId){
        postService.deleteById(postId);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Long postId,
                           @RequestBody Post post){

        postService.updatePost(postId, post);
    }

}
