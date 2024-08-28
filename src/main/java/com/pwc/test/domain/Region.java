package com.pwc.test.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Set;

@Getter
public enum Region {
    AFRICA("Africa"),
    AMERICAS("Americas"),
    ANTARCTIC("Antarctic"),
    ASIA("Asia"),
    EUROPE("Europe"),
    OCEANIA("Oceania");

    private static final Set<Region> EURASIA_AND_AFRICA = Set.of(ASIA, EUROPE, AFRICA);

    @JsonValue
    private final String regionStr;

    Region(String regionStr) {
        this.regionStr = regionStr;
    }

    public boolean connectedWith(Region region) {
        if (this == region) {
            return true;
        }

        return EURASIA_AND_AFRICA.contains(this) && EURASIA_AND_AFRICA.contains(region);
    }
}
