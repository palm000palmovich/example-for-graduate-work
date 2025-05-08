package ru.skypro.homework.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.exception.AvatarNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.UserAvatar;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class AvatarServiceImpl {
    private final UserAvatarRepository avatarRepository;
    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    public AvatarServiceImpl(UserAvatarRepository avatarRepository, UserRepository userRepository) {
        this.avatarRepository = avatarRepository;
        this.userRepository = userRepository;
    }

    public void uploadAvatar(String userName, MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        // Удаляем старый аватар, если он есть
        avatarRepository.findByUserId(user.getId()).ifPresent(avatarRepository::delete);

        // Создаем новый аватар
        UserAvatar avatar = new UserAvatar();
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setUser(user);

        UserAvatar userAvatar = avatarRepository.save(avatar);

        user.setImage("/images/" + userAvatar.getId());

        logger.info("UserId " + user.getId() + " user image: " + user.getImage());
        userRepository.save(user);
    }

    public UserAvatar getAvaById(Integer id){

        UserAvatar userAvatar = avatarRepository.findById(id).orElseThrow(() ->
                new AvatarNotFoundException());

        if (userAvatar != null){
            logger.info("Ava: " + userAvatar.getMediaType() + " "
                    + userAvatar.getFilePath());
        }
        return userAvatar;
    }
}