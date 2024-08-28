package com.pwc.test.application.search;

import com.pwc.test.application.RoutingServiceException;
import com.pwc.test.domain.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SearchService {

    public List<String> searchPath(Map<String, Country> countries, Country origin, Country destination) {
        Map<String, String> previous = new HashMap<>();

        VisitedQueue queue = new VisitedQueue(origin);

        Country current;
        while ((current = queue.poll()) != null && !destination.equals(current)) {
            for (String neighbour : current.getBorders()) {
                Country neighbourCountry = countries.get(neighbour);

                if(!queue.add(neighbourCountry)) {
                    continue;
                }

                previous.put(neighbour, current.getCca3());

                if (neighbourCountry.equals(destination)) {
                    // reached destination
                    break;
                }
            }
        }

        if (!destination.equals(current)) {
            throw new RoutingServiceException(
                    String.format("Cannot reach the destination %s from %s", destination.getCca3(), origin.getCca3())
            );
        }


        LinkedList<String> path = new LinkedList<>();
        String node = current.getCca3();
        do {
            path.addFirst(node);
        } while ((node = previous.get(node)) != null);

        return Collections.unmodifiableList(path);
    }
}
