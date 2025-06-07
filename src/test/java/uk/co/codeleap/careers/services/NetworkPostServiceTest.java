package uk.co.codeleap.careers.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.co.codeleap.careers.dtos.EditPostDTO;
import uk.co.codeleap.careers.dtos.NetworkPostDTO;
import uk.co.codeleap.careers.exceptions.ResourceNotFoundException;
import uk.co.codeleap.careers.mappers.NetworkPostMapper;
import uk.co.codeleap.careers.models.NetworkPost;
import uk.co.codeleap.careers.repositories.NetworkPostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NetworkPostServiceTest {

    @Mock
    private NetworkPostRepository postRepository;

    @Mock
    private NetworkPostMapper postMapper;

    @InjectMocks
    private NetworkPostService postService;

    @Test
    @DisplayName("When creating a post, then return the created post")
    void whenCreatingPost_thenReturnCreatedPost() {
        NetworkPostDTO postDTO = new NetworkPostDTO("user1", "Title", "Content");
        NetworkPost post = new NetworkPost(1, "user1", LocalDateTime.now(), "Title", "Content");

        when(postMapper.toNetworkPost(any(NetworkPostDTO.class))).thenReturn(post);
        when(postRepository.save(any(NetworkPost.class))).thenReturn(post);

        NetworkPost createdPost = postService.createPost(postDTO);

        assertNotNull(createdPost);
        assertEquals("user1", createdPost.getUserName());
        assertEquals("Title", createdPost.getTitle());
        assertEquals("Content", createdPost.getContent());
    }

    @Test
    @DisplayName("When retrieving all posts, then return paginated posts")
    void whenRetrievingAllPosts_thenReturnPaginatedPosts() {
        Pageable pageable = Pageable.ofSize(10);
        NetworkPost post1 = new NetworkPost(1, "user1", LocalDateTime.now(), "Title", "Content");
        NetworkPost post2 = new NetworkPost(2, "user2", LocalDateTime.now(), "Title 2", "Content 2");
        Page<NetworkPost> page = new PageImpl<>(List.of(post1, post2));

        when(postRepository.findAll(pageable)).thenReturn(page);

        Page<NetworkPost> result = postService.findAllPosts(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("Title", result.getContent().get(0).getTitle());
        assertEquals("Title 2", result.getContent().get(1).getTitle());
    }

    @Test
    @DisplayName("When editing an existing post, then update it successfully")
    void whenEditingExistingPost_thenUpdateSuccessfully() {
        int postId = 1;
        EditPostDTO editPostDTO = new EditPostDTO("Updated Title", "Updated Content");
        NetworkPost post = new NetworkPost(postId, "user1", LocalDateTime.now(), "Title", "Content");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);

        assertDoesNotThrow(() -> postService.editPost(postId, editPostDTO));
        assertEquals("Updated Title", post.getTitle());
        assertEquals("Updated Content", post.getContent());
    }

    @Test
    @DisplayName("When editing a non-existent post, then throw ResourceNotFoundException")
    void whenEditingNonExistentPost_thenThrowResourceNotFoundException() {
        int postId = 999;
        EditPostDTO editPostDTO = new EditPostDTO("Updated Title", "Updated Content");

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.editPost(postId, editPostDTO));
        verify(postRepository, never()).save(any(NetworkPost.class));
    }

    @Test
    @DisplayName("When deleting an existing post, then remove it successfully")
    void whenDeletingExistingPost_thenRemoveSuccessfully() {
        int postId = 1;
        NetworkPost post = new NetworkPost(postId, "user1", LocalDateTime.now(), "Title", "Content");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        doNothing().when(postRepository).delete(post);

        assertDoesNotThrow(() -> postService.removePost(postId));
        verify(postRepository).delete(post);
    }

    @Test
    @DisplayName("When deleting a non-existent post, then throw ResourceNotFoundException")
    void whenDeletingNonExistentPost_thenThrowResourceNotFoundException() {
        int postId = 999;

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.removePost(postId));
        verify(postRepository, never()).delete(any(NetworkPost.class));
    }
}