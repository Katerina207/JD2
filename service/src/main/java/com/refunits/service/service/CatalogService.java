package com.refunits.service.service;

import com.refunits.database.configuration.DatabaseConfiguration;
import com.refunits.database.enumeration.BoilingPoint;
import com.refunits.database.enumeration.UnitRange;
import com.refunits.database.repository.UnitRepository;
import com.refunits.service.dto.SearchUnitDto;
import com.refunits.service.dto.ViewBasicUnitInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@ContextConfiguration(classes = {DatabaseConfiguration.class})
public class CatalogService {

    @Autowired
    private UnitRepository unitRepository;

    public List<ViewBasicUnitInfoDto> getAll() {
        return unitRepository.findAll().stream()
                .map(it -> new ViewBasicUnitInfoDto(it.getId(), it.getName(), it.getPrice()))
                .collect(Collectors.toList());
    }

    public Set<ViewBasicUnitInfoDto> getFiltered(SearchUnitDto searchUnitDto) {
        List<BoilingPoint> boilingPoints = new ArrayList<>();
        List<UnitRange> ranges = new ArrayList<>();
        Double minRefCapacity;
        Double maxRefCapacity;
        if (searchUnitDto.getBoilingPoint() != null) {
            boilingPoints.add(searchUnitDto.getBoilingPoint());
        } else {
            boilingPoints.addAll(Arrays.asList(BoilingPoint.values()));
        }
        if (searchUnitDto.getRange() != null) {
            ranges.add(searchUnitDto.getRange());
        } else {
            ranges.addAll(Arrays.asList(UnitRange.values()));
        }
        if (searchUnitDto.getMinRefCapacity() != null) {
            minRefCapacity = searchUnitDto.getMinRefCapacity();
        } else {
            minRefCapacity = Double.MIN_VALUE;
        }
        if (searchUnitDto.getMaxRefCapacity() != null) {
            maxRefCapacity = searchUnitDto.getMaxRefCapacity();
        } else {
            maxRefCapacity = Double.MAX_VALUE;
        }
        return unitRepository.findAllByBoilingPointInAndRangeInAndRefCapacityBetween(boilingPoints, ranges, minRefCapacity, maxRefCapacity)
                .stream()
                .map(it -> new ViewBasicUnitInfoDto(it.getId(), it.getName(), it.getPrice()))
                .collect(Collectors.toSet());
    }
}