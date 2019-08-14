package za.co.ssquared.assignment.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.ssquared.assignment.planet.model.Planet;
import za.co.ssquared.assignment.planet.repo.PlanetRepository;

import java.util.List;

@RestController
public class PlanetController {

    @Autowired
    PlanetRepository planetRepository;

    @RequestMapping("/getAllPlanets")
    @ResponseBody
    public List<Planet> getAllPlanets(){
        return planetRepository.getAllPlanets ();
    }

    @RequestMapping("/getPlanet")
    @ResponseBody
    public Planet getPlanet(@RequestParam("planet_id") long planet_id){
        return planetRepository.getPlanet (planet_id);
    }

    @RequestMapping("/addPlanet")
    @ResponseBody
    public String addPlanet(@RequestParam("planet_id") long planet_id,@RequestParam("planet_node") String planet_node,
                          @RequestParam("planet_name") String planet_name){
        if(planetRepository.addPlanet (planet_id,planet_node,planet_name) >= 1){
            return "Planet Added Successfully";
        }else{
            return "Something went wrong !";
        }
    }
    @RequestMapping("/detetePlanet")
    @ResponseBody
    public String detetePlanet(@RequestParam("planet_id") long planet_id){
        if(planetRepository.deletePlanet(planet_id) >= 1){
            return "Planet Deleted Successfully";
        }else{
            return "Something went wrong !";
        }
    }
}
