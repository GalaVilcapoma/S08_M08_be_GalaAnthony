package pe.edu.vallegrande.eduSmart.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "games")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String developer;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(precision = 4)
    private Double rating;

    @Column(precision = 8)
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "available")
    private Boolean available;
}
