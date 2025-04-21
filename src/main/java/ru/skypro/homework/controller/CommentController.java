package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/{id}/comments")
    public Comments getComments(@PathVariable int id) {
        return new Comments();
    }

    @PostMapping("/{id}/comments")
    public CreateOrUpdateComment addComment(@PathVariable int id,
                                            @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return new CreateOrUpdateComment();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CreateOrUpdateComment patchComment(@PathVariable int adId, @PathVariable int commentId,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return new CreateOrUpdateComment();
    }
}
