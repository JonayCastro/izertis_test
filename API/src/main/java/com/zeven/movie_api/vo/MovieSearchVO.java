package com.zeven.movie_api.vo;

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
public class MovieSearchVO {

    private int page;
    @JsonProperty("results")
    private List<MovieVO> moviesVOs = new ArrayList<>();
    @JsonProperty("total_pages")
    private int totalPage;
    @JsonProperty("total_results")
    private int totalResults;
}
