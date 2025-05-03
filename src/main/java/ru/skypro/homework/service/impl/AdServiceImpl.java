package ru.skypro.homework.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.AuthenticationException;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.exception.ForbiddenAccesException;
import ru.skypro.homework.exception.UnauthorizedAccesException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;


    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper, UserRepository userRepository, FileStorageService fileStorageService) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public AdsDto getAllAds() {
        return adMapper.toAdsDto(adRepository.findAll());
    }

    @Transactional
    @Override
    public Ad createAdd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();

        Ad ad = adMapper.toAd(createOrUpdateAd);
        ad.setUser(user);

        if (image != null && !image.isEmpty()) {
            String fileName = fileStorageService.saveAdImage(image);
            ad.setImage(fileName);
        }
        return adRepository.save(ad);
    }

    @Override
    public ExtendedAd getAddById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        if (!isUserAuthorized(ad.getUser())) {
            throw new AuthenticationException();
        }
        User author = ad.getUser();

        return adMapper.toExtendedAd(ad);
    }

    @Override
    public void deleteAddById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (!isUserAuthorized(ad.getUser())) {
            throw new UnauthorizedAccesException();
        }
        if (!hasPermissionToDelete(ad)) {
            throw new ForbiddenAccesException();
        }
        adRepository.deleteById(id);
    }

    @Override
    public AdDto updateAdById(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
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


    @Override
    public void updateAdImageById(Integer id, MultipartFile image) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Ad ad = adRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        User user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new UnauthorizedAccesException());

        if (!ad.getUser().equals(user)) {
            throw new ForbiddenAccesException();
        }
        String imageName = fileStorageService.saveAdImage(image);
        ad.setImage(imageName);
        adRepository.save(ad);
    }

    private boolean isUserAuthorized(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getName().equals(user.getUsername());
    }

    private boolean hasPermissionToDelete(Ad ad) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null || !authentication.isAuthenticated()) {
            return false;
        }
        String currentUsername = authentication.getName();
        return ad.getUser().getUsername().equals(currentUsername);
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
