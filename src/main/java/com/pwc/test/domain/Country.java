package com.pwc.test.domain;

import lombok.Data;

import java.util.List;

@Data
public class Country {
    private String cca3;
    private Region region;
    private List<String> borders;
}
