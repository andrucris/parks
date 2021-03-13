package park.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import park.domain.Park;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {
    public Park findByName(String name);

    public List<Park> findAll();

    public Park findByCode(Long code);
}
