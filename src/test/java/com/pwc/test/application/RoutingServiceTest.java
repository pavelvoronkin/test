package com.pwc.test.application;

import com.pwc.test.domain.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
class RoutingServiceTest {
    @Autowired
    RoutingService routingService;

    @Test
    void shouldFindRouteCzeIta() {
        // when
        Route route = routingService.route(new RouteQuery("CZE", "ITA"));

        // then
        assertThat(route.getRoute()).isEqualTo(List.of("CZE", "AUT", "ITA"));
    }

    @Test
    void shouldFindRouteCzePor() {
        // when
        Route route = routingService.route(new RouteQuery("CZE", "PRT"));

        // then
        assertThat(route.getRoute()).isEqualTo(List.of("CZE", "DEU", "FRA", "ESP", "PRT"));
    }

    @Test
    void shouldFindRouteCzeNor() {
        // when
        Route route = routingService.route(new RouteQuery("CZE", "NOR"));

        // then
        assertThat(route.getRoute()).isEqualTo(List.of("CZE", "POL", "RUS", "NOR"));
    }

    @Test
    void shouldFindRouteCzeMar() {
        // when
        Route route = routingService.route(new RouteQuery("CZE", "MAR"));

        // then
        assertThat(route.getRoute()).isEqualTo(List.of("CZE", "DEU", "FRA", "ESP", "MAR"));
    }

    @Test
    void shouldNotFindRouteCzeGbr() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "GBR")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("Cannot reach the destination GBR from CZE");
    }

    @Test
    void shouldNotFindRouteCzeHnd() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "HND")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("No land connection between EUROPE (CZE) and AMERICAS (HND)");
    }

    @Test
    void shouldNotFindRouteCzeAus() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "AUS")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("No land connection between EUROPE (CZE) and OCEANIA (AUS)");
    }

    @Test
    void shouldNotFindRouteCzeSgs() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "SGS")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("No land connection between EUROPE (CZE) and ANTARCTIC (SGS)");
    }

    @Test
    void shouldNotFindRouteCzeVen() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "VEN")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("No land connection between EUROPE (CZE) and AMERICAS (VEN)");
    }

    @Test
    void shouldNotFindRouteWhenOriginAndDestinationMatch() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "CZE")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("Origin and destination should be different");
    }

    @Test
    void shouldNotFindRouteWhenOriginCountryNotFound() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("XYZ", "CZE")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("Invalid origin country XYZ");
    }

    @Test
    void shouldNotFindRouteWhenDestinationCountryNotFound() {
        // when
        assertThatThrownBy(() -> routingService.route(new RouteQuery("CZE", "XYZ")))
                // then
                .isInstanceOf(RoutingServiceException.class)
                .hasMessage("Invalid destination country XYZ");
    }
}