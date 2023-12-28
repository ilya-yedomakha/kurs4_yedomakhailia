package stu.cn.ua.kurs4.model.domain.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stu.cn.ua.kurs4.model.domain.QueueRow;
import stu.cn.ua.kurs4.model.domain.documents.*;
import stu.cn.ua.kurs4.model.domain.people.superclass.Person;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client extends Person {

    @OneToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToOne
    @JoinColumn(name = "birthSertificate_id", referencedColumnName = "id")
    private BirthSertificate birthSertificate;

    @OneToOne
    @JoinColumn(name = "visa_id", referencedColumnName = "id")
    private Visa visa;

    @OneToOne
    @JoinColumn(name = "driverLicense_id", referencedColumnName = "id")
    private DriverLicense driverLicense;

    @OneToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private Registration registration;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<QueueRow> queues;

    private Long migrant_key;
}
