package entity;

import enumeratiom.BoilingPoint;
import enumeratiom.UnitRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Unit {

    private Integer id;
    private String name;
    private UnitRange range;
    private BoilingPoint boilingPoint;
    private Double refCapacity;
    private Integer weight;
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer price;
    private List<Option> optionList = new ArrayList<>();
}