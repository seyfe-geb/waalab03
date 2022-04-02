package net.seyfe.waalab03.service;

import net.seyfe.waalab03.domain.Comment;
import net.seyfe.waalab03.domain.Post;
import net.seyfe.waalab03.domain.dto.CommentDto;
import net.seyfe.waalab03.domain.dto.PostDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAll();

    CommentDto findById(Long commentId);

    void save(Comment comment);

    public void deleteById(Long commentId);

    public void updateComment(Long commentId, Comment comment);

    List<CommentDto> findCommentsById(Long userId, Long postId);
}
