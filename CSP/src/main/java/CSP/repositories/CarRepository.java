package CSP.repositories;

import CSP.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    public Optional<Car> findAllById(Long id);
}
