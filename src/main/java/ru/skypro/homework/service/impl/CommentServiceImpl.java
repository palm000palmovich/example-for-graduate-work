package ru.skypro.homework.service.impl;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

@Service
public class CommentServiceImpl{
    private final CommentRepository commentRepository;

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    //private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
//        this.commentMapper = commentMapper;
    }

}

