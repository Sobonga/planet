package za.co.ssquared.assignment.planet.FileProcessor;

        import za.co.ssquared.assignment.planet.model.Planet;

public class ItemProcessor implements org.springframework.batch.item.ItemProcessor<Planet, Planet> {

    @Override
    public Planet process(Planet planet) throws Exception {
        return planet;
    }
}
