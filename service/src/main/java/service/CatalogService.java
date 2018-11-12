package service;

import com.refunits.dao.UnitDao;
import com.refunits.enumeration.BoilingPoint;
import com.refunits.enumeration.UnitRange;
import dto.SearchUnitDto;
import dto.ViewBasicUnitInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CatalogService {

    private static final CatalogService INSTANCE = new CatalogService();

    public static CatalogService getInstance() {
        return INSTANCE;
    }

    public List<ViewBasicUnitInfoDto> getAll() {
        return UnitDao.getInstance().getAll().stream()
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
        return UnitDao.getInstance()
                .getFiltered(boilingPoints, ranges, minRefCapacity, maxRefCapacity, searchUnitDto.getLimit())
                .stream()
                .map(it -> new ViewBasicUnitInfoDto(it.getId(), it.getName(), it.getPrice()))
                .collect(Collectors.toSet());
    }
}