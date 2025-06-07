package uk.co.codeleap.careers.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.codeleap.careers.dtos.NetworkPostRecordDTO;
import uk.co.codeleap.careers.models.NetworkPost;
import uk.co.codeleap.careers.services.NetworkPostService;

@RestController
@RequestMapping(value = "/careers")
public class NetworkPostController {

    private final NetworkPostService postService;

    public NetworkPostController(NetworkPostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<NetworkPost> createPost(@RequestBody @Valid NetworkPostRecordDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postDTO));

    }

    @GetMapping()
    public ResponseEntity<Page<NetworkPost>> findAllPosts(@PageableDefault(page = 0, size = 10,
            sort = "createdDatetime", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.findAllPosts(page));
    }
}
