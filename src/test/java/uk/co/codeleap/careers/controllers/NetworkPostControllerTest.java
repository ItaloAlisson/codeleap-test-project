package uk.co.codeleap.careers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uk.co.codeleap.careers.dtos.EditPostDTO;
import uk.co.codeleap.careers.dtos.NetworkPostDTO;
import uk.co.codeleap.careers.exceptions.ResourceNotFoundException;
import uk.co.codeleap.careers.models.NetworkPost;
import uk.co.codeleap.careers.services.NetworkPostService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NetworkPostController.class)
class NetworkPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NetworkPostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("When creating a post, then return created post with HTTP status 201")
    void whenCreatingPost_thenReturnCreatedPostWithHttpStatus201() throws Exception {
        NetworkPostDTO postDTO = new NetworkPostDTO("user1", "Title", "Content");
        NetworkPost post = new NetworkPost(1, "user1", LocalDateTime.now(), "Title", "Content");

        when(postService.createPost(any(NetworkPostDTO.class))).thenReturn(post);

        ResultActions result = mockMvc.perform(post("/careers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDTO)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.userName").value(post.getUserName()))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.content").value(post.getContent()));
    }

    @Test
    @DisplayName("When creating a post with invalid data, then return HTTP status 400")
    void whenCreatingPostWithInvalidData_thenReturnHttpStatus400() throws Exception {
        NetworkPostDTO postDTO = new NetworkPostDTO("", "", "");

        ResultActions result = mockMvc.perform(post("/careers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDTO)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("When editing a post, then return no content with HTTP status 204")
    void whenEditingPost_thenReturnNoContentWithHttpStatus204() throws Exception {
        EditPostDTO editPostDTO = new EditPostDTO("Updated Title", "Updated Content");
        int postId = 1;

        Mockito.doNothing().when(postService).editPost(postId, editPostDTO);

        mockMvc.perform(patch("/careers/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editPostDTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("When editing a non-existent post, then return HTTP status 404")
    void whenEditingNonExistentPost_thenReturnHttpStatus404() throws Exception {
        EditPostDTO editPostDTO = new EditPostDTO("Updated Title", "Updated Content");
        int postId = 999;

        doThrow(new ResourceNotFoundException("Post with ID " + postId + " was not found"))
                .when(postService).editPost(postId, editPostDTO);

        mockMvc.perform(patch("/careers/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editPostDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When deleting a post, then return no content with HTTP status 204")
    void whenDeletingPost_thenReturnNoContentWithHttpStatus204() throws Exception {
        int postId = 1;

        Mockito.doNothing().when(postService).removePost(postId);

        mockMvc.perform(delete("/careers/{id}", postId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("When deleting a non-existent post, then return HTTP status 404")
    void whenDeletingNonExistentPost_thenReturnHttpStatus404() throws Exception {
        int postId = 999;

        doThrow(new ResourceNotFoundException("Post with ID " + postId + " was not found"))
                .when(postService).removePost(postId);

        mockMvc.perform(delete("/careers/{id}", postId))
                .andExpect(status().isNotFound());
    }
}