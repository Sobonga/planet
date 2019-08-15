package za.co.ssquared.assignment.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.ssquared.assignment.planet.model.Planet;
import za.co.ssquared.assignment.planet.repo.PlanetRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PlanetController {

    @Autowired
    private PlanetRepository planetRepository;

    //Exposing GET methods on Planet Resource
    @GetMapping("/planets")
    public List<Planet> retrieveAllPlanets() {
        return planetRepository.findAll();
    }

    //method to expose the details of a specific planet
    @GetMapping("/planets/{planet_id}")
    public Planet retrievePlanet(@PathVariable long planet_id) {
        Optional<Planet> planet = planetRepository.findById(planet_id);

        if (!planet.isPresent()) {
            return null;
        }
        return planet.get();
    }

    //Expose DELETE Method on Planet Resource
    @DeleteMapping("/planets/{planet_id}")
    public void deletePlanet(@PathVariable long planet_id) {
        planetRepository.deleteById(planet_id);
    }

    //Method to create a new planet
    @PostMapping("/planets")
    public ResponseEntity<Object> createPlanet(@RequestBody Planet planet) {
        Planet savedPlanet = planetRepository.save(planet);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{planet_id}")
                .buildAndExpand(savedPlanet.getPlanet_id ()).toUri();

        return ResponseEntity.created(location).build();

    }

    //Method to update existing planet
    @PutMapping("/planets/{planet_id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Planet planet, @PathVariable long planet_id) {

        Optional<Planet> planetOptional = planetRepository.findById(planet_id);

        if (!planetOptional.isPresent())
            return ResponseEntity.notFound().build();

        planet.setPlanet_id (planet_id);

        planetRepository.save(planet);

        return ResponseEntity.noContent().build();
    }
}
