package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Avatar;

import java.util.Optional;

@Repository
public interface UserAvatarRepository extends JpaRepository<Avatar, Integer> {
    Optional<Avatar> findByUserId(Integer userId);
    Optional<Avatar> findByAdId(Integer adId);
}
