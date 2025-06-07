package uk.co.codeleap.careers.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.co.codeleap.careers.dtos.NetworkPostRecordDTO;
import uk.co.codeleap.careers.mappers.NetworkPostMapper;
import uk.co.codeleap.careers.models.NetworkPost;
import uk.co.codeleap.careers.repositories.NetworkPostRepository;

@Service
public class NetworkPostService {
    private final NetworkPostRepository postRepository;

    private final NetworkPostMapper postMapper;

    public NetworkPostService(NetworkPostRepository postRepository, NetworkPostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public NetworkPost createPost(NetworkPostRecordDTO postDTO) {
        var post = postMapper.toNetworkPost(postDTO);
        return postRepository.save(post);
    }

    public Page<NetworkPost> findAllPosts(Pageable page) {
        return postRepository.findAll(page);
    }
}
