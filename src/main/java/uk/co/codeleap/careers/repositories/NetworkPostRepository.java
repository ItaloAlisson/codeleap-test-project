package uk.co.codeleap.careers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.codeleap.careers.models.NetworkPost;

public interface NetworkPostRepository extends JpaRepository<NetworkPost, Integer> {
}
