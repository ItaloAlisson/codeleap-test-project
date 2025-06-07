package uk.co.codeleap.careers.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.codeleap.careers.dtos.EditPostDTO;
import uk.co.codeleap.careers.dtos.NetworkPostDTO;
import uk.co.codeleap.careers.exceptions.ResourceNotFoundException;
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

    public NetworkPost createPost(NetworkPostDTO postDTO) {
        var post = postMapper.toNetworkPost(postDTO);
        return postRepository.save(post);
    }

    public Page<NetworkPost> findAllPosts(Pageable page) {
        return postRepository.findAll(page);
    }

    public void editPost (Integer id,EditPostDTO postDTO) {
        var post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post with ID " + id
                        + " was not found."));
        BeanUtils.copyProperties(postDTO,post,"id","createdDatetime");
        postRepository.save(post);

    }
}
