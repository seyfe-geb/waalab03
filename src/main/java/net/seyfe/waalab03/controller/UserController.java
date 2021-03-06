package net.seyfe.waalab03.controller;


import net.seyfe.waalab03.domain.Comment;
import net.seyfe.waalab03.domain.Post;
import net.seyfe.waalab03.domain.User;
import net.seyfe.waalab03.domain.dto.CommentDto;
import net.seyfe.waalab03.domain.dto.PostDto;
import net.seyfe.waalab03.domain.dto.UserDto;
import net.seyfe.waalab03.service.PostService;
import net.seyfe.waalab03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId")Long userId){

        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostDto>> getUserPostsById(@PathVariable("userId")Long userId){

        return ResponseEntity.ok(userService.findPostsByUserId(userId));
    }

    @GetMapping("/user")
    public List<UserDto> getPostsByUserName(@RequestParam("name") String name){

        return userService.getPostByUserName(name);
    }

    @GetMapping("/users-having-more-than-one-posts")
    public List<UserDto> getUsersHavingMoreThanOnePost(){

        return userService.getUsersHavingMoreThanOnePost();
    }

    @GetMapping("/{userId}/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getUserPostCommentsById(@PathVariable("userId")Long userId,
                                                             @PathVariable("postId")Long postId){

        return ResponseEntity.ok(userService.findCommentsById(userId, postId));
    }

    @GetMapping("/{userId}/posts/{postId}")
    public ResponseEntity<List<PostDto>> getUserPostsById(@PathVariable("userId")Long userId,
                                                                    @PathVariable("postId")Long postId){

        return ResponseEntity.ok(userService.findPostsByPostId(userId, postId));
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.save(user);
    }

    @PostMapping("/{userId}/add-post")
    public void savePost(@PathVariable("userId")Long userId,
                         @RequestBody Post post){
        userService.savePost(userId, post);
    }

    @PostMapping("/{userId}/posts/{postId}/add-comment")
    public void saveComment(@PathVariable("userId")Long userId,
                            @PathVariable("postId")Long postId,
                         @RequestBody Comment comment){
        userService.saveComment(userId, postId, comment);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId")Long userId){
        userService.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestBody User user){

        userService.updateUser(userId, user);
    }

    @GetMapping("/posts-by-title")
    public List<PostDto> getPostsByTitle(@RequestParam("title") String title){
        return postService.getPostsByTitle(title);
    }
}
