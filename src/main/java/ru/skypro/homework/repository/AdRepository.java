package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer>{
    List<Ad> findAllByUser_Id(Integer id);
}