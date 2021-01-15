package CSP.repositories;

import CSP.models.Car;
import CSP.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public Optional<Comment> findAllById(Long id);

//    @Query(value = "select * from comments where comment_id in ?1", nativeQuery = true)
//    public List<Comment> getAllCarsById(List<Long> ids);
}
