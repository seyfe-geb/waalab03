package net.seyfe.waalab03.controller;

import net.seyfe.waalab03.domain.dto.CommentDto;
import net.seyfe.waalab03.domain.dto.PostDto;
import net.seyfe.waalab03.domain.dto.UserDto;
import net.seyfe.waalab03.service.CommentService;
import net.seyfe.waalab03.service.PostService;
import net.seyfe.waalab03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments(){
        return commentService.findAll();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId")Long commentId){

        return ResponseEntity.ok(commentService.findById(commentId));
    }


}
