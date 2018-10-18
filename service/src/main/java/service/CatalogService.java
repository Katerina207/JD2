package service;

import dao.UnitDao;
import dto.SearchUnitDto;
import dto.ViewBasicUnitInfoDto;
import entity.Unit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        List<Unit> units = UnitDao.getInstance().getAll();
        if (searchUnitDto.getBoilingPoint() != null) {
            units.removeIf(it -> !it.getBoilingPoint().equals(searchUnitDto.getBoilingPoint()));
        }
        if (searchUnitDto.getRange() != null) {
            units.removeIf(it -> !it.getRange().equals(searchUnitDto.getRange()));
        }
        if (searchUnitDto.getMinRefCapacity() != null && searchUnitDto.getMaxRefCapacity() != null) {
            units.removeIf(it -> it.getRefCapacity() < searchUnitDto.getMinRefCapacity() ||
                    it.getRefCapacity() > searchUnitDto.getMaxRefCapacity());
        }

        return units.stream().
                map(it -> new ViewBasicUnitInfoDto(it.getId(), it.getName(), it.getPrice()))
                .collect(Collectors.toSet());
    }
}