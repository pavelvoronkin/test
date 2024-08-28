package com.pwc.test.application;

import com.pwc.test.application.search.SearchService;
import com.pwc.test.domain.Country;
import com.pwc.test.domain.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final CountryRepository countryRepository;
    private final SearchService searchService;

    public Route route(RouteQuery query) {
        Map<String, Country> countryMap = countryRepository.findAllCountries();

        validate(query);

        Country originCountry = Optional.ofNullable(countryMap.get(query.getOrigin()))
                .orElseThrow(
                        () -> new RoutingServiceException(
                            String.format("Invalid origin country %s", query.getOrigin())
                        )
                );

        Country destinationCountry = Optional.ofNullable(countryMap.get(query.getDestination()))
                .orElseThrow(
                        () -> new RoutingServiceException(
                                String.format("Invalid destination country %s", query.getDestination())
                        )
                );

        if (!originCountry.getRegion().connectedWith(destinationCountry.getRegion())) {
            throw new RoutingServiceException(
                    String.format(
                            "No land connection between %s (%s) and %s (%s)",
                            originCountry.getRegion(), query.getOrigin(),
                            destinationCountry.getRegion(), query.getDestination())
            );
        }


        return new Route(searchService.searchPath(countryMap, originCountry, destinationCountry));
    }

    private void validate(RouteQuery query) {
        if (query.getOrigin().equals(query.getDestination())) {
            throw new RoutingServiceException("Origin and destination should be different");
        }
    }
}
