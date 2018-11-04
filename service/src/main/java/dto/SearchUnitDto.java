package dto;

import enumeration.BoilingPoint;
import enumeration.UnitRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchUnitDto {

    private BoilingPoint boilingPoint;
    private UnitRange range;
    private Double minRefCapacity;
    private Double maxRefCapacity;
}