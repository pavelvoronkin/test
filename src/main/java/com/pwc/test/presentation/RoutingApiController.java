package com.pwc.test.presentation;

import com.pwc.test.application.RouteQuery;
import com.pwc.test.application.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutingApiController {
    private final RouteMapper routeMapper;
    private final RoutingService routingService;

    @GetMapping("/routing/{origin}/{destination}")
    public RouteDto getRouting(
            @PathVariable(name = "origin") String origin,
            @PathVariable(name = "destination") String destination
    ) {
        return routeMapper.map(routingService.route(new RouteQuery(origin, destination)));
    }
}
