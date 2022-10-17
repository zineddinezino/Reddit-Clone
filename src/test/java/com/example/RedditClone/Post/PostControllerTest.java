package com.example.RedditClone.Post;

import com.example.RedditClone.controller.PostController;
import com.example.RedditClone.dto.post.PostRequestDto;
import com.example.RedditClone.dto.post.PostResponseDto;
import com.example.RedditClone.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private PostController postController;
    @Mock
    private PostService postService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private PostRequestDto postRequestDto;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();


    }

    @Test
    void should_return_post_when_get_post_by_id() throws Exception {

        var postResponseDto = buildPostResponseDto();

        Mockito.when(postService.getPost(1L)).thenReturn(postResponseDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postName", Matchers.is("Get Post Test")));
    }

    @Test
    void should_return_created_status_when_create_new_post() throws Exception {
        var postRequestDto = buildPostRequestDto();
        Mockito.doNothing().when(postService).createPost(postRequestDto);

        var content = toJson(postRequestDto);

        var mockPostRequest = MockMvcRequestBuilders
                .post("/api/posts/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockPostRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
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

    private PostRequestDto buildPostRequestDto() {
        return postRequestDto = PostRequestDto.builder()
                .postId(2L)
                .postName("Create Post Test")
                .postUrl("www.post.com")
                .postDescription("Nothing")
                .subredditName("Nothing")
                .build();
    }

    private String toJson(PostRequestDto postRequestDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(postRequestDto);
    }
}