package com.pwc.test.application;

import com.pwc.test.domain.Country;

import java.util.Map;

public interface CountryRepository {
    Map<String, Country> findAllCountries();
}
