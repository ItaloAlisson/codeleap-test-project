package uk.co.codeleap.careers.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
