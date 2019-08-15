package za.co.ssquared.assignment.planet.FileProcessor;

import za.co.ssquared.assignment.planet.model.Routes;

public class ItemRouteProcessor implements org.springframework.batch.item.ItemProcessor<Routes, Routes> {

    @Override
    public Routes process(Routes routes) throws Exception {
        return routes;
    }
}
