package com.pwc.test.application.search;

import com.pwc.test.domain.Country;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class VisitedQueue {
    private final Queue<Country> queue = new ArrayDeque<>();
    private final Set<Country> visited = new HashSet<>();

    public VisitedQueue(Country country) {
        add(country);
    }

    public boolean add(Country country) {
        if (visited.contains(country)) {
            return false;
        }

        visited.add(country);
        return queue.add(country);
    }

    public Country poll() {
        return queue.poll();
    }

}
