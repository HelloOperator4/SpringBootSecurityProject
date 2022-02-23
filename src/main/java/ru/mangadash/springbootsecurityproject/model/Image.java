package ru.mangadash.springbootsecurityproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "is_preview_image")
    private boolean isPreviewImage;

    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;
}