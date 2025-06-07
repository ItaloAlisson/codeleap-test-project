package uk.co.codeleap.careers.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.codeleap.careers.dtos.EditPostDTO;
import uk.co.codeleap.careers.dtos.NetworkPostDTO;
import uk.co.codeleap.careers.models.NetworkPost;
import uk.co.codeleap.careers.services.NetworkPostService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/careers")
public class NetworkPostController {

    private final NetworkPostService postService;

    public NetworkPostController(NetworkPostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<NetworkPost> createPost(@RequestBody @Valid NetworkPostDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postDTO));

    }

    @GetMapping()
    public ResponseEntity<Page<NetworkPost>> findAllPosts(@PageableDefault(page = 0, size = 10,
            sort = "createdDatetime", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.findAllPosts(page));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> editPost(@PathVariable(value = "id") Integer id, @RequestBody @Valid EditPostDTO postDTO){
        postService.editPost(id,postDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
