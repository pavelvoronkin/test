package com.pwc.test.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RouteQuery {
    private String origin;
    private String destination;
}
