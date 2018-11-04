package model;

import enumeration.BoilingPoint;
import enumeration.UnitRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "unit", schema = "refunits_storage")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "range")
    private UnitRange range;

    @Enumerated(EnumType.STRING)
    @Column(name = "boiling_point")
    private BoilingPoint boilingPoint;

    @Column(name = "ref_capacity", nullable = false)
    private Double refCapacity;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToMany
    @JoinTable(name = "unit_option", schema = "refunits_storage",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Set<Option> options;

    @OneToMany(mappedBy = "unit")
    private Set<Product> products;

    public Unit(String name, Double refCapacity) {
        this.name = name;
        this.refCapacity = refCapacity;
    }
}
