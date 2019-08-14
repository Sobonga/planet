package za.co.ssquared.assignment.planet;

import org.springframework.batch.item.ItemProcessor;
import za.co.ssquared.assignment.planet.model.Planet;

public class PersonItemProcessor implements ItemProcessor<Planet, Planet> {

    @Override
    public Planet process(Planet planet) throws Exception {
        return planet;
    }
}
