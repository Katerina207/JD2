package dto;

import com.refunits.enumeration.BoilingPoint;
import com.refunits.enumeration.UnitRange;
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
    private Integer limit;
    private Integer offset;
}