package ru.mangadash.springbootsecurityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mangadash.springbootsecurityproject.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
