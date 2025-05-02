package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "comment.author.id", target = "author")
    @Mapping(source = "comment.author.image", target = "authorImage")
    @Mapping(source = "comment.author.firstName", target = "authorFirstName")
    @Mapping(target = "createdAt", expression = "java(comment.getCreatedAt().toEpochMilli())")
    CommentDTO toCommentDTO(Comment comment);

    default CommentsDTO toCommentsDTO(List<Comment> comments) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setCount(comments.size());
        commentsDTO.setResults(comments.stream()
                .map(this::toCommentDTO).toList());
        return commentsDTO;
    }

    @Mapping(source = "text", target = "text")
    CreateOrUpdateComment toCreateOrUpdateComment(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toComment(CreateOrUpdateComment createOrUpdateComment);
}
