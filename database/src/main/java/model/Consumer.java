package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "consumer", schema = "refunits_storage")
@PrimaryKeyJoinColumn(name = "registered_user_id")
public class Consumer extends RegisteredUser {

    @Column(name = "mail")
    private String mail;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "company", nullable = false)
    private String company;

    public Consumer(String login, String password, LocalDate registrationDate, String company) {
        super(login, password, registrationDate);
        this.company = company;
    }
}
