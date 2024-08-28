package com.pwc.test.presentation;

import com.pwc.test.domain.Route;
import org.mapstruct.Mapper;

@Mapper
public interface RouteMapper {

    RouteDto map(Route route);
}
