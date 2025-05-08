package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.model.AdImage;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<AdImage, Integer> {

    Optional<AdImage> findByAdId(Integer id);
}
