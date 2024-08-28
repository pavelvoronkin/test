package com.pwc.test.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.test.application.CountryRepository;
import com.pwc.test.domain.Country;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CountryRepositoryImpl implements CountryRepository {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    @SneakyThrows
    public Map<String, Country> findAllCountries() {
        List<Country> list = objectMapper.readValue(
                CountryRepositoryImpl.class.getResourceAsStream("/countries.json"),
                new TypeReference<>() {}
        );

        return list.stream().collect(Collectors.toMap(Country::getCca3, Function.identity()));
    }
}
