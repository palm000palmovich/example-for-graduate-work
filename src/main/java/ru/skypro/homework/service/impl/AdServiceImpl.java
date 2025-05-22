package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.AuthenticationException;
import ru.skypro.homework.exception.ForbiddenAccesException;
import ru.skypro.homework.exception.UnauthorizedAccesException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final AvatarServiceImpl avatarServiceImpl;

    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper,
                         UserRepository userRepository,
                         AvatarServiceImpl avatarServiceImpl) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userRepository = userRepository;
        this.avatarServiceImpl = avatarServiceImpl;
    }

    @Override
    public AdsDto getAllAds() {
        return adMapper.toAdsDto(adRepository.findAll());
    }

    @Override
    public Ad createAdd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();

        Ad ad = adMapper.toAd(createOrUpdateAd);
        ad.setUser(user);
        adRepository.save(ad);
        if (image != null && !image.isEmpty()) {
            avatarServiceImpl.uploadAdAvatar(ad.getId(), image);
        }

        return ad;
    }

    @Override
    public ExtendedAd getAdById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return adMapper.toExtendedAd(ad);
    }

    @Transactional
    @Override
    public void deleteAddById(Integer id) {
        System.out.println("Start deleteAdById with ID: " + id);
        Ad ad = adRepository.findById(id).orElseThrow(() -> {
            System.out.println("Ad not found, throwing EntityNotFoundException");
            return new EntityNotFoundException();
        });
        System.out.println("Ad found: " + ad);

        if (!isUserAuthorized(ad.getUser())) {
            System.out.println("User is not authorized, throwing UnauthorizedAccessException");
            throw new UnauthorizedAccesException();
        }
        if (!hasPermissionToDelete(ad)) {
            System.out.println("User lacks permission, throwing ForbiddenAccessException");
            throw new ForbiddenAccesException();
        }

        System.out.println("Deleting ad: " + ad);
        adRepository.delete(ad);
        System.out.println("Ad deleted successfully");
    }
//    @Transactional
//    @Override
//    public void deleteAddById(Integer id) {
//        Ad ad = adRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        if (!isUserAuthorized(ad.getUser())) {
//            throw new UnauthorizedAccesException();
//        }
//        if (!hasPermissionToDelete(ad)) {
//            throw new ForbiddenAccesException();
//        }
//        adRepository.delete(ad);
//    }

    @Override
    public AdDto updateAdById(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = adRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!hasPermissionToUpdate(ad)) {
            throw new ForbiddenAccesException();
        }
        if (!isUserAuthorized(ad.getUser())) {
            throw new AuthenticationException();
        }
        Ad updatedAd = adMapper.toAd(createOrUpdateAd);
        return adMapper.toAdDto(adRepository.save(updatedAd));
    }

    @Override
    public AdsDto getAdsUsers() {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        List<Ad> ads = adRepository.findAllByUser_Id(user.getId());
        return adMapper.toAdsDto(ads);
    }

    private boolean isUserAuthorized(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getName().equals(user.getUsername());
    }

    private boolean hasPermissionToDelete(Ad ad) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String currentUsername = authentication.getName();
        User adUser = ad.getUser();

        if (adUser == null || adUser.getUsername() == null) {
            return false;
        }
        return adUser.getUsername().equals(currentUsername);
    }

    private boolean hasPermissionToUpdate(Ad ad) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String username = authentication.getName();
        return ad.getUser() != null && username.equals(ad.getUser().getUsername());
    }
}
