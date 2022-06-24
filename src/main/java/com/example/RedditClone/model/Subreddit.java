package com.example.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subredditId;

    @NotBlank(message = "This field can not be empty")
    private String subredditName;

    @NotBlank(message = "This field can not be empty")
    private String subredditDescription;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    private Instant subredditCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
