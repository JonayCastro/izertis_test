package com.zeven.movie_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchDTO {

    private int page;
    @JsonProperty("results")
    private List<MovieDTO> moviesVOs = new ArrayList<>();
    @JsonProperty("total_pages")
    private int totalPage;
    @JsonProperty("total_results")
    private int totalResults;
}
