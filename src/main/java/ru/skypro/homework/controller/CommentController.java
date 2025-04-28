package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@Validated
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comments")
    public CommentsDTO getComments(@PathVariable Integer id) {
        return commentService.getComments(id);
    }

    @PostMapping("/{id}/comments")
    public CreateOrUpdateComment addComment(@PathVariable Integer id,
                                            @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentService.addComment(id, createOrUpdateComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        commentService.deleteComment(adId, commentId);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CreateOrUpdateComment patchComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return commentService.updateComment(adId, commentId, createOrUpdateComment);
    }
}
