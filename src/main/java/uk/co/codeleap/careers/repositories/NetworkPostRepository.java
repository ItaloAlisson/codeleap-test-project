package uk.co.codeleap.careers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.codeleap.careers.models.NetworkPost;
@Repository
public interface NetworkPostRepository extends JpaRepository<NetworkPost, Integer> {
}
