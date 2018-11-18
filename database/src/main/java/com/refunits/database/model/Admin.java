package com.refunits.database.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "admin", schema = "refunits_storage")
@PrimaryKeyJoinColumn(name = "registered_user_id")
public class Admin extends RegisteredUser {

    public Admin(String login, String password, LocalDate registrationDate) {
        super(login, password, registrationDate);
    }
}
