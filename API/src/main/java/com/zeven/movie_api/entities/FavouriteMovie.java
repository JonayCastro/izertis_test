package com.zeven.movie_api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favourite_movies", uniqueConstraints = @UniqueConstraint(columnNames = "title", name = "UK_title"))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavouriteMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "is_adult")
    private Boolean isAdultContent;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

}
