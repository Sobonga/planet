package za.co.ssquared.assignment.planet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.ssquared.assignment.planet.model.Planet;


@Repository
public interface PlanetRepository extends JpaRepository<Planet,Long> {
}
