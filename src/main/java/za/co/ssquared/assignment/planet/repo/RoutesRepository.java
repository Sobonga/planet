package za.co.ssquared.assignment.planet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.ssquared.assignment.planet.model.Routes;


@Repository
public interface RoutesRepository extends JpaRepository<Routes,Long> {
}
