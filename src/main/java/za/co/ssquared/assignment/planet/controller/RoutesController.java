package za.co.ssquared.assignment.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.ssquared.assignment.planet.model.Routes;
import za.co.ssquared.assignment.planet.repo.RoutesRepository;

import java.util.List;

@RestController
public class RoutesController {

    @Autowired
    RoutesRepository routesRepository;

    @RequestMapping("/getAllRoutes")
    @ResponseBody
    public List<Routes> getAllRoutes(){
        return routesRepository.getAllRoutes ();
    }

    @RequestMapping("/getRoute")
    @ResponseBody
    public Routes getRoute(@RequestParam("routes_id") long routes_id){
        return routesRepository.getRoutes (routes_id);
    }

    @RequestMapping("/addRoute")
    @ResponseBody
    public String addRoute(@RequestParam("routes_id") long routes_id,@RequestParam("route") int route,
                          @RequestParam("planet_origin") String planet_origin, @RequestParam("planet_destination") String planet_destination,@RequestParam("planet_distance") Double planet_distance){
        if(routesRepository.addRoutes (routes_id,route,planet_origin,planet_destination,planet_distance) >= 1){
            return "Route Added Successfully";
        }else{
            return "Something went wrong !";
        }
    }
    @RequestMapping("/deteteRoute")
    @ResponseBody
    public String deteteRoute(@RequestParam("routes_id") long routes_id){
        if(routesRepository.deleteRoute(routes_id) >= 1){
            return "Route Deleted Successfully";
        }else{
            return "Something went wrong !";
        }
    }
}
