package za.co.ssquared.assignment.planet.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import za.co.ssquared.assignment.planet.model.Routes;

import java.util.List;

@Repository
public class RoutesRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    //Getting all Routes from the table
    public List<Routes> getAllRoutes(){
        List<Routes> routes = jdbcTemplate.query ( "select routes_id, route,planet_origin,planet_destination,distance from Routes",(result,rowNum) -> new Routes ( result.getLong ( "routes_id" ),
                result.getInt ( "route" ),result.getString ( "planet_origin" ),result.getString ( "planet_destination" ),result.getDouble ( "distance" ) ));
        return routes;
    }

    //Getting a specific Route by Route id from table
    public Routes getRoutes(long routesId){
        String query = "SELECT * FROM Routes WHERE ID=?";
        Routes routes = jdbcTemplate.queryForObject(query,new Object[]{routesId},new BeanPropertyRowMapper<> (Routes.class));

        return routes;
    }

    //Adding a Route into database table
    public int addRoutes(long routes_id,int route,String planet_origin,String planet_destination, Double distance){
        String query = "INSERT INTO Routes VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(query,routes_id,route,planet_origin,planet_destination,distance);
    }

    //delete an Route from database
    public int deleteRoute(long routes_id){
        String query = "DELETE FROM Routes WHERE ID =?";
        return jdbcTemplate.update(query,routes_id);
    }
}
