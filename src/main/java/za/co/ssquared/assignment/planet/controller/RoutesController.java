package za.co.ssquared.assignment.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.ssquared.assignment.planet.model.Routes;
import za.co.ssquared.assignment.planet.repo.RoutesRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RoutesController {

    @Autowired
    private RoutesRepository routesRepository;

    //Exposing GET methods on Routes Resource
    @GetMapping("/routes")
    public List<Routes> retrieveAllRoutes() {
        return routesRepository.findAll();
    }

    //method to expose the details of a specific route
    @GetMapping("/routes/{routes_id}")
    public Routes retrieveRoutes(@PathVariable long routes_id) {
        Optional<Routes> routes = routesRepository.findById(routes_id);

        if (!routes.isPresent()) {
            return null;
        }
        return routes.get();
    }

    //Expose DELETE Method on routes Resource
    @DeleteMapping("/routes/{routes_id}")
    public void deleteRoutes(@PathVariable long routes_id) {
        routesRepository.deleteById(routes_id);
    }

    //Method to create a new route
    @PostMapping("/routes")
    public ResponseEntity<Object> createRoute(@RequestBody Routes routes) {
        Routes savedRoute = routesRepository.save(routes);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{routes_id}")
                .buildAndExpand(savedRoute.getRoutes_id ()).toUri();

        return ResponseEntity.created(location).build();

    }

    //Method to update existing routes
    @PutMapping("/routes/{routes_id}")
    public ResponseEntity<Object> updateRoutes(@RequestBody Routes routes, @PathVariable long routes_id) {

        Optional<Routes> routeOptional = routesRepository.findById(routes_id);

        if (!routeOptional.isPresent())
            return ResponseEntity.notFound().build();

        routes.setRoutes_id (routes_id);

        routesRepository.save(routes);

        return ResponseEntity.noContent().build();
    }
}
