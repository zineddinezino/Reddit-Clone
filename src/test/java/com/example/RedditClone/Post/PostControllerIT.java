package com.example.RedditClone.Post;

import com.example.RedditClone.TestContainersConfiguration;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.PostRepository;
import com.example.RedditClone.repository.SubredditRepository;
import com.example.RedditClone.repository.UserRepository;
import com.example.RedditClone.security.JwtProvider;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerIT extends TestContainersConfiguration {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    void should_return_post_when_get_post_by_id() throws Exception {
        var user = buildUser();
        userRepository.save(user);
        var  token = jwtProvider.generateTokenWithUsername("user");

        var subreddit = buildSubreddit();
        subredditRepository.save(subreddit);

        var post = buildPost(subreddit,user);
        postRepository.save(post);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postName", Matchers.is("Get Post Test")));
    }

    private Subreddit buildSubreddit() {
        return Subreddit.builder()
                .subredditId(1L)
                .subredditName("Nothing")
                .subredditDescription("Nothing")
                .subredditCreatedDate(Instant.now())
                .build();
    }

    private Post buildPost(Subreddit subreddit, User user) {
        return Post.builder()
                .postId(1L)
                .postName("Get Post Test")
                .postDescription("Nothing")
                .postUrl("www.post.com")
                .postVotingCount(0)
                .postCreatedDate(Instant.now())
                .user(user)
                .subreddit(subreddit)
                .build();
    }

    private User buildUser() {
        return User.builder()
                .userId(1L)
                .userName("user")
                .password("1234")
                .userEmail("user@gmail.com")
                .userCreatedDate(Instant.now())
                .accountEnabled(true)
                .build();
    }


}
