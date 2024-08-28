package com.pwc.test.application;

import com.pwc.test.domain.Country;
import com.pwc.test.domain.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void shouldGetAllCountries() {
        // when
        Map<String, Country> countries = countryRepository.findAllCountries();

        // then
        assertThat(countries.size()).isEqualTo(250);
        assertThat(countries.get("ABW").getCca3()).isEqualTo("ABW");
        assertThat(countries.get("ABW").getRegion()).isEqualTo(Region.AMERICAS);
    }
}