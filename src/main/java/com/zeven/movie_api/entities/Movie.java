package com.zeven.movie_api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies_api_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "is_adult")
    private Boolean isAdultContent;

    @Column(name = "overview")
    private String overview;

}
