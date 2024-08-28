package com.pwc.test.presentation;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class RouteDto {
    private final List<String> route;
}
