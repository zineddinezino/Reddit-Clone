package com.example.RedditClone.Post;

import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.model.Post;
import com.example.RedditClone.model.Subreddit;
import com.example.RedditClone.model.User;
import com.example.RedditClone.repository.PostRepository;
import com.example.RedditClone.repository.UserRepository;
import com.example.RedditClone.security.JwtProvider;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc()
class PostControllerIntegrationTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    private Post post;
    private User user;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void should_return_post_when_get_post_by_id() throws Exception {

        var post = buildPost();
        var user = buildUser();
        String token = jwtProvider.generateTokenWithUsername("user");

        Mockito.when(userRepository.findByUserName("user"))
                .thenReturn(Optional.of(user));
        Mockito.when(postRepository.findById(1L))
                .thenReturn(Optional.of(post));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/1")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postName", Matchers.is("Get Post Test")));
        Mockito.verify(postRepository, Mockito.times(1)).findById(1L);

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

    private PostResponseDto buildPostResponseDto() {
        return PostResponseDto.builder()
                .postId(1L)
                .postName("Get Post Test")
                .postDescription("Nothing")
                .postUrl("www.post.com")
                .postVotingCount(0)
                .commentCont(0)
                .subredditName("Nothing")
                .userName("NoOne")
                .duration("Three hours")
                .build();
    }

    private Post buildPost() {
        return post = Post.builder()
                .postId(1L)
                .postName("Get Post Test")
                .postDescription("Nothing")
                .postUrl("www.post.com")
                .postVotingCount(0)
                .postCreatedDate(Instant.now())
                .user(User.builder().build())
                .subreddit(Subreddit.builder().build())
                .build();
    }
}