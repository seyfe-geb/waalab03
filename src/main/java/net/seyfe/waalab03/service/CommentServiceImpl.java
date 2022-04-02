package net.seyfe.waalab03.service;

import net.seyfe.waalab03.domain.Comment;
import net.seyfe.waalab03.domain.Post;
import net.seyfe.waalab03.domain.User;
import net.seyfe.waalab03.domain.dto.CommentDto;
import net.seyfe.waalab03.domain.dto.PostDto;
import net.seyfe.waalab03.domain.dto.UserDto;
import net.seyfe.waalab03.helper.ListMapper;
import net.seyfe.waalab03.repo.CommentRepo;
import net.seyfe.waalab03.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper<Comment, CommentDto> listMapperCommentToCommentDto;

    @Override
    public List<CommentDto> findAll() {

        return (List<CommentDto>)listMapperCommentToCommentDto.mapList(commentRepo.findAll(), new CommentDto());
    }

    @Override
    public CommentDto findById(Long commentId) {

        return modelMapper.map(commentRepo.findById(commentId).orElse(null), CommentDto.class);
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void deleteById(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    @Override
    public void updateComment(Long commentId, Comment comment) {
        Comment oldComment = commentRepo.findById(commentId).orElse(null);
        oldComment.setName(comment.getName());
    }

    @Override
    public List<CommentDto> findCommentsById(Long userId, Long postId) {
        List<Post> posts = userRepo.findById(userId).get().getPosts();
        List<Comment> comments = posts.stream()
                                    .filter(p -> p.getId() == postId)
                                    .flatMap(p -> p.getComments().stream())
                                    .collect(Collectors.toList());
        return (List<CommentDto>)listMapperCommentToCommentDto.mapList(comments, new CommentDto());

    }
}
