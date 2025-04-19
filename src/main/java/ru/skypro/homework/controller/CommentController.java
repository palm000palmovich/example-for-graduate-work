package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    @GetMapping("/{id}/comments")
    public Comments getComments(@PathVariable int id) {
        return new Comments();
    }

    @PostMapping("/{id}/comments")
    public CreateOrUpdateComment addComment(@PathVariable int id) {
        return new CreateOrUpdateComment();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CreateOrUpdateComment patchComment(@PathVariable int adId, @PathVariable int commentId) {
        return new CreateOrUpdateComment();
    }
}
