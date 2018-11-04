package model;

import enumeration.OrderStatus;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pre_order", schema = "refunits_storage")
public class PreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "preOrder")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "registered_user_id", nullable = false)
    private RegisteredUser registeredUser;

    public PreOrder(LocalDate date, OrderStatus status, Set<Product> products, RegisteredUser registeredUser) {
        this.date = date;
        this.status = status;
        this.products = products;
        this.registeredUser = registeredUser;
    }
}
