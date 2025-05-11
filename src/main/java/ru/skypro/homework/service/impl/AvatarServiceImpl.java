package ru.skypro.homework.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.AvatarNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class AvatarServiceImpl {
    private final UserAvatarRepository avatarRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    public AvatarServiceImpl(UserAvatarRepository avatarRepository, UserRepository userRepository, AdRepository adRepository) {
        this.avatarRepository = avatarRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    public void uploadAvatar(String userName, MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        // Удаляем старый аватар, если он есть
        removeExistingAvatar(user);

        // Создаем новый аватар
        Avatar avatar = new Avatar();
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setUser(user);

        Avatar savedAvatar = avatarRepository.save(avatar);

        String uniquePath = ("/images/" + savedAvatar.getId() + "?t=" + System.currentTimeMillis());
        user.setImage(uniquePath);

        logger.info("UserId " + user.getId() + " user image: " + user.getImage());
        userRepository.save(user);
    }

    public Avatar getAvaById(Integer id) {

        Avatar avatar = avatarRepository.findById(id).orElseThrow(() ->
                new AvatarNotFoundException());

        if (avatar != null) {
            logger.info("Ava: " + avatar.getMediaType() + " "
                    + avatar.getFilePath());
        }
        return avatar;
    }

    public void uploadAdAvatar(Integer adId, MultipartFile file) throws IOException {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new UsernameNotFoundException("Объявление не найдено"));

        // Удаляем старый аватар, если он есть
        avatarRepository.findByAdId(adId).ifPresent(avatarRepository::delete);

        // Создаем новый аватар
        Avatar avatar = new Avatar();
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setAd(ad);

        Avatar newAdAvaTAR = avatarRepository.save(avatar);
        String uniquePath = ("/images/" + newAdAvaTAR.getId() + "?t=" + System.currentTimeMillis());

        ad.setImage(uniquePath);

        logger.info("AdId " + ad.getId() + " ad image: " + ad.getImage());
        adRepository.save(ad);
    }

    public Avatar getAdImageById(Integer id) {

        Avatar avatar = avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);

        if (avatar != null) {
            logger.info("Ava of ad: " + avatar.getMediaType() + " "
                    + avatar.getFilePath());
        }
        return avatar;
    }

    private void removeExistingAvatar(User user) {
        avatarRepository.findByUserId(user.getId()).ifPresent(avatar -> {
            avatarRepository.delete(avatar);
            user.setImage(null);
        });
    }
}