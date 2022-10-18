package com.example.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "This field can not be empty")
    private String postName;

    @Nullable
    @Lob
    private String postDescription;

    @Nullable
    private String postUrl;

    private Integer postVotingCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant postCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subredditId", referencedColumnName = "subredditId")
    private Subreddit subreddit;

}
