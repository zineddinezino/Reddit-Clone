package com.example.RedditClone.Post;

import com.example.RedditClone.controller.PostController;
import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    PostResponseDto postResponseDto;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception  {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();

        postResponseDto = PostResponseDto.builder()
                .postId(1L)
                .postName("Test")
                .postDescription("Nothing")
                .postUrl("www.post.com")
                .postVotingCount(0)
                .commentCont(0)
                .subredditName("Nothing")
                .userName("NoOne")
                .duration("Three hours")
                .build();
    }

    @Test
    void TestGetPost() throws Exception{
        Mockito.when(postService.getPost(1L)).thenReturn(postResponseDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/posts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postName", Matchers.is("Test")));
    }
}