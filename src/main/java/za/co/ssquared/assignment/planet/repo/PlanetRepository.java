package za.co.ssquared.assignment.planet.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import za.co.ssquared.assignment.planet.model.Planet;

import java.util.List;

@Repository
public class PlanetRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Getting all planets from the table
    public List<Planet> getAllPlanets(){
        List<Planet> planets = jdbcTemplate.query ( "select planet_id, planet_node,planet_name from planet",(result,rowNum) -> new Planet ( result.getLong ( "planet_id" ),
                result.getString ( "planet_node" ),result.getString ( "planet_name" ) ));
        return planets;
    }

    //Getting a specific planet by planet id from table
    public Planet getPlanet(long planetId){
        String query = "SELECT * FROM PLANET WHERE ID=?";
        Planet planet = jdbcTemplate.queryForObject(query,new Object[]{planetId},new BeanPropertyRowMapper<> (Planet.class));

        return planet;
    }

    //Adding an planet into database table
    public int addPlanet(long planet_id,String planet_node,String planet_name){
        String query = "INSERT INTO PLANET VALUES(?,?,?)";
        return jdbcTemplate.update(query,planet_id,planet_node,planet_name);
    }

    //delete an Planet from database
    public int deletePlanet(long planet_id){
        String query = "DELETE FROM PLANET WHERE ID =?";
        return jdbcTemplate.update(query,planet_id);
    }
}
