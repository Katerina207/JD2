package com.refunits.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "preOrders")
@Entity
@Table(name = "registered_user", schema = "refunits_storage")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RegisteredUser extends BaseEntity<Integer>{

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "registeredUser")
    private Set<PreOrder> preOrders;

    public RegisteredUser(String login, String password, LocalDate registrationDate) {
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
    }
}
