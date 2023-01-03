package com.sandsbeach.surfreport.model;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer rank;
    private String title;
    private String body;
}
